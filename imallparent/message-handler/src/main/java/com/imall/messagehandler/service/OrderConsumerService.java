package com.imall.messagehandler.service;

import com.imall.dto.Result;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/21 16:37
 */
public interface OrderConsumerService {


    /**
     * 确认订单
     * @param clientId 客户id
     * @param isFlash 是否为抢购订单
     * @return
     */
    Result<Object> confirm(Integer clientId, boolean isFlash);

}
