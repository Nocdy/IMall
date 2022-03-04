package com.imall.shoppingproducer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imall.entities.mall.Goods;

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








}
