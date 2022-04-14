package com.imall.shoppingproducer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imall.entities.mall.Goods;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/24 20:33
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 用流式读取所有商品信息并将其存入redis中
     */
    void getAll();

    /**
     * 响应无限滚动页面
     * @param listNum 用户浏览到的index
     * @return 返回新页面的商品信息
     */
    List<Goods> getNewList(int listNum);

    /**
     * 浏览单个商品并将其缓存到redis中
     * @param goodsId 商品id
     * @param  clientId 客户id
     * @return 商品信息
     */
    Goods getOneAndSaveToRedis(Integer goodsId,Integer clientId);

    /**
     * 更新商品信息
     * @param goods 要插入的商品信息
     * @throws FileNotFoundException 找不到上传文件夹
     * @throws FileUploadException 上传失败
     * @throws InterruptedException 中断异常
     */
    void submitUpdate(Goods goods) throws FileNotFoundException, FileUploadException, InterruptedException;

    /**
     * 定时获取当天闪购商品信息
     */
    void getFlashToRedisByDate();

    /**
     * 搜索商品
     * @param keyword 搜索关键次
     * @return 返回搜索结果
     */
    List<Goods> searchList(@Param("keyword") String keyword);


}
