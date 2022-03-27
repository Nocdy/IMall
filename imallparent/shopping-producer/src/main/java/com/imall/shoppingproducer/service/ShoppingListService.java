package com.imall.shoppingproducer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imall.entities.mall.Goods;
import com.imall.entities.mall.OrderList;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/14 11:45
 */
public interface ShoppingListService extends IService<OrderList> {

    /**
     * 查询购物车信息
     * @param cid 用户账号
     * @return 返回购物车信息
     */
    OrderList showList(Integer cid);


    /**
     * 将商品添加至购物车
     * @param cid 用户账号
     * @param goods 要添加的货物
     */
    void addList(Integer cid, Goods goods);



}
