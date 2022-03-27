package com.imall.messagehandler.service;

import com.imall.entities.mall.Order;

import java.util.List;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/20 13:23
 */
public interface GenerateOrderService {

    /**
     * 生成抢购订单
     * @param order 订单
     * @param goodsIdList 商品号列表
     */
    void generate(Order order, List<Integer> goodsIdList);

    /**
     * 生成普通订单
     * @param order 订单
     * @return 返回生成是否成功
     */
    boolean generate(Order order);

}
