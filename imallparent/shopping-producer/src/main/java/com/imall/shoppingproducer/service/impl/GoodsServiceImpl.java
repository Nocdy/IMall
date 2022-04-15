package com.imall.shoppingproducer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imall.entities.mall.Goods;
import com.imall.shoppingproducer.dao.GoodsMapper;
import com.imall.shoppingproducer.service.GoodsService;
import com.imall.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.imall.constants.Constant.*;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/24 20:42
 */
@Service
@Slf4j
@RequiredArgsConstructor
@EnableScheduling
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    private final RedisUtils redisUtils;

    private final ObjectMapper objectMapper;

    private final UploadFileServiceImpl uploadFileService;

    @Override
    public void getAll() {
        Goods goodsIndex=new Goods();
        goodsIndex.setId(0);
        boolean status=redisUtils.lSet(REDIS_ALL_GOODS_KEY,goodsIndex,REDIS_EXPIRE_HALF_HOUR);
        if(status) {
            getBaseMapper().selectAll(resultContext -> {
                Goods goods = resultContext.getResultObject();
                if(goods.getEndDate().compareTo(LocalDateTime.now()) > 0) {
                    if (redisUtils.lSet(REDIS_ALL_GOODS_KEY, goods)) {
                        log.info("将商品id: {} 插入key: {} 的list中成功", goods.getId(), REDIS_ALL_GOODS_KEY);
                    }
                }
            });
        }

    }

    @Override
    public List<Goods> getNewList(int listNum) {
        log.info("call getNewList Service");
        if(!redisUtils.hasKey(REDIS_ALL_GOODS_KEY)){
            getAll();
        }
        int nextPage=2;
        List<Goods> goodsList=new ArrayList<>();
        for(int i=listNum;i<=listNum+nextPage;i++){
            Goods goods=objectMapper.convertValue(redisUtils.lGetIndex(REDIS_ALL_GOODS_KEY,i),Goods.class);
            goodsList.add(goods);
        }
        return goodsList;
    }

    @Override
    public List<Goods> getCarousel() {
        if(!redisUtils.hasKey(REDIS_ALL_GOODS_KEY)){
            getAll();
        }
        List<Goods> goodsList=new ArrayList<>();
        for(int i=0;i<6;i++){
            Goods goods=objectMapper.convertValue(redisUtils.lGetIndex(REDIS_ALL_GOODS_KEY,i),Goods.class);
            goodsList.add(goods);
        }
        return goodsList;
    }

    @Override
    public Goods getOneAndSaveToRedis(Integer goodsId,Integer clientId) {
        String gid=goodsId.toString();
        String cid=clientId.toString();
        Goods result;
        if(redisUtils.hasKey(gid)){
            result= objectMapper.convertValue(redisUtils.get(gid),Goods.class);
        }
        else {
            result=getById(goodsId);
            redisUtils.set(gid,result,REDIS_EXPIRE_HALF_HOUR);
        }
        if(result.getIsPlash()){
            String key=FLASH_PREFIX+result.getId().toString();
            redisUtils.setNx(key,result.getCount(),REDIS_EXPIRE_ONE_DAY);
            redisUtils.set(FLASH_PREFIX+cid,result.getPlashCount(),REDIS_EXPIRE_HALF_HOUR);
        }
        return  result;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void submitUpdate(Goods goods) throws FileNotFoundException, FileUploadException, InterruptedException {
        log.info("call submitUpdate service");
        redisUtils.del(REDIS_ALL_GOODS_KEY);
        goods.setVersion(0);
        getBaseMapper().insert(goods);
        String url=uploadFileService.uploadGoodsImage(goods.getFile(),goods.getId(),goods.getVendorId());
        if(url==null){
            throw new FileUploadException("找不到文件路径/文件储存失败");
        }
        goods.setImagePath(url);
        updateById(goods);
        //延迟双删策略，保持数据库与redis的同步。
        Thread.sleep(500);
        redisUtils.del(REDIS_ALL_GOODS_KEY);
    }

    @Override
   @Scheduled(cron = "0 0/15 * * * ? ")
    public void getFlashToRedisByDate() {
        log.info("开始获取当天闪购商品信息");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime todayEnd=LocalDateTime.of(LocalDate.now(),LocalTime.MAX);
        getBaseMapper().selectTodayFlash(todayStart.format(dateTimeFormatter),
                todayEnd.format(dateTimeFormatter),
                resultContext -> {
            Goods goods=resultContext.getResultObject();
            String key=FLASH_PREFIX+goods.getId().toString();
            redisUtils.setNx(key,goods.getCount(),REDIS_EXPIRE_ONE_DAY);
        });
    }

    @Override
    public List<Goods> searchList(String keyword) {
        String key=SEARCH_PREFIX+keyword;
        List<Goods> result = new ArrayList<>();
        if(redisUtils.hasKey(key)){
            result=objectMapper.convertValue(redisUtils.get(key), new TypeReference<ArrayList<Goods>>(){});
        }
        else {
            QueryWrapper<Goods> wrapper1 = new QueryWrapper<>();
            QueryWrapper<Goods> wrapper2 = new QueryWrapper<>();
            wrapper1.likeLeft("goods_name", keyword);
            wrapper2.likeRight("goods_name", keyword);
            result.addAll(getBaseMapper()
                    .selectList(wrapper1)
                    .stream()
                    .filter(goods -> goods.getEndDate().compareTo(LocalDateTime.now()) > 0)
                    .collect(Collectors.toList()));
            result.addAll(getBaseMapper()
                    .selectList(wrapper2)
                    .stream().filter(goods -> goods.getEndDate().compareTo(LocalDateTime.now()) > 0)
                    .collect(Collectors.toList()));
            redisUtils.set(key,result);
        }
        return  result;
    }


}
