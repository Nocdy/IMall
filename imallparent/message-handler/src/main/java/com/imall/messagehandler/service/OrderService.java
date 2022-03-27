package com.imall.messagehandler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imall.entities.mall.Order;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/20 1:18
 */
public interface OrderService extends IService<Order> {

    /**
     * 确认订单
     * @param clientId
     * @param isFlash
     * @return
     */
    boolean confirmOrder(Integer clientId,boolean isFlash);

    /**
     * 展示订单
     * @param clientId
     * @param isFlash
     * @return
     */
    Order showOrder(Integer clientId,boolean isFlash);

    /**
     * 将订单从redis中删除
     * @param clientId
     * @param isFlash
     */
    void delOrderInRedis(Integer clientId,boolean isFlash);

    /**
     * 回滚订单
     * @param order
     */
    void rollBackForFlash(Order order);

    /**
     * 获取过期订单id
     * @param clientId
     * @return
     */
    String getExpiredOrderId(Integer clientId);


}
