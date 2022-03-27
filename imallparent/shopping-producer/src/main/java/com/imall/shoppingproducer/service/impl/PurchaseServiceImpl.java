package com.imall.shoppingproducer.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imall.entities.mall.Order;
import com.imall.entities.mall.OrderList;
import com.imall.shoppingproducer.service.IMessage;
import com.imall.shoppingproducer.service.PurchaseService;
import com.imall.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.imall.constants.Constant.ORDER_LIST_PREFIX;
import static com.imall.constants.MessageQueueConstants.COMMON_ORDER_0_CHANNEL;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/24 11:31
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final RedisUtils redisUtils;

    private final ObjectMapper objectMapper;

    private final IMessage iMessage;

    @Override
    public void purchase(Integer clientId) {
        String listKey=ORDER_LIST_PREFIX + clientId.toString();
        OrderList orderList=objectMapper.convertValue(redisUtils.get(listKey),OrderList.class);
        if(orderList!=null) {
            Order order = new Order();
            order.setId(UUID.randomUUID().toString());
            order.setClientId(clientId);
            order.setGoodsList(orderList.getGoodsList());
            order.setIsFinish(false);
            iMessage.sendMessage(JSON.toJSONString(order),COMMON_ORDER_0_CHANNEL);
        }
    }
}
