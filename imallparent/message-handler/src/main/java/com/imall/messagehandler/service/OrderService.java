package com.imall.messagehandler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imall.dto.Result;
import com.imall.entities.mall.Order;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/20 1:18
 */
public interface OrderService extends IService<Order> {

    /**
     * 确认订单
     * @param clientId 客户id
     * @param isFlash 是否为闪购订单
     * @return 返回是否确认成功
     */
    boolean confirmOrder(Integer clientId,boolean isFlash);

    /**
     * 展示订单
     * @param clientId 客户id
     * @param isFlash 是否为闪购订单
     * @return 返回结果
     */
    Result<Object> showOrder(Integer clientId, boolean isFlash);

    /**
     * 将订单从redis中删除
     * @param clientId 客户id
     * @param isFlash 是否为闪购订单
     */
    void delOrderInRedis(Integer clientId,boolean isFlash);

    /**
     * 回滚订单
     * @param order 需要回滚的订单
     */
    void rollBackForFlash(Order order);

    /**
     * 获取过期订单id
     * @param clientId 客户id
     * @return 返回订单id
     */
    String getExpiredOrderId(Integer clientId);





}
