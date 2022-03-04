package com.imall.shoppingproducer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imall.entities.mall.Goods;
import com.imall.shoppingproducer.dao.GoodsMapper;
import com.imall.shoppingproducer.service.GoodsService;
import com.imall.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.imall.constants.Constant.REDIS_ALL_GOODS_KEY;
import static com.imall.constants.Constant.REDIS_EXPIRE_HALF_HOUR;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/24 20:42
 */
@Service
@RequiredArgsConstructor
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    private final RedisUtils redisUtils;

    private final ObjectMapper objectMapper;

    Logger logger= LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Override
    public void getAll() {
        Goods goodsIndex=new Goods();
        goodsIndex.setId(0);
        boolean status=redisUtils.lSet(REDIS_ALL_GOODS_KEY,goodsIndex,REDIS_EXPIRE_HALF_HOUR);
        if(status) {
            getBaseMapper().selectAll(resultContext -> {
                Goods goods = resultContext.getResultObject();
                if(redisUtils.lSet(REDIS_ALL_GOODS_KEY, goods)){
                    logger.info("将商品id: {} 插入key: {} 的list中失败",goods.getId(),REDIS_ALL_GOODS_KEY);
                }
            });
        }

    }

    @Override
    public List<Goods> getNewList(int listNum) {
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




}
