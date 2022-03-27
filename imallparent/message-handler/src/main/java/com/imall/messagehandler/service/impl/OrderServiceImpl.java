package com.imall.messagehandler.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imall.entities.mall.Order;
import com.imall.messagehandler.dao.OrderMapper;
import com.imall.messagehandler.service.IMessage;
import com.imall.messagehandler.service.OrderService;
import com.imall.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.function.Function;

import static com.imall.constants.Constant.*;
import static com.imall.constants.MessageQueueConstants.ROLL_BACK_0_CHANNEL;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/20 1:19
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final ObjectMapper objectMapper;

    private final RedisUtils redisUtils;

    private final static HashMap<Boolean, Function<String,String>> GENERATE_KEY=new HashMap<>();

    private final IMessage iMessage;

    static {
        GENERATE_KEY.put(true,s -> FLASH_ORDER_PREFIX+s);
        GENERATE_KEY.put(false,s -> ORDER_PREFIX+s);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public boolean confirmOrder(Integer clientId, boolean isFlash) {
        String cid=clientId.toString();
        String key=GENERATE_KEY.get(isFlash).apply(cid);
        Order newOrder=new Order();
        boolean result;
        if(redisUtils.get(key)!=null){
            Order confirmOrder=showOrder(clientId,isFlash);
            confirmOrder.setIsFinish(true);
            newOrder.setOrder(confirmOrder);
            log.info("确认订单 {}",newOrder);
            result=updateById(newOrder);
            return result;
        }
        else {
            return false;
        }
    }

    @Override
    public Order showOrder(Integer clientId, boolean isFlash) {
        String key=GENERATE_KEY.get(isFlash).apply(clientId.toString());
        return objectMapper.convertValue(redisUtils.get(key),Order.class);
    }

    @Override
    public void delOrderInRedis(Integer clientId, boolean isFlash) {
        String key=GENERATE_KEY.get(isFlash).apply(clientId.toString());
        redisUtils.del(key);
    }

    @Override
    public void rollBackForFlash( Order order) {
        iMessage.sendMessage(JSON.toJSONString(order),ROLL_BACK_0_CHANNEL);
    }

    @Override
    public String getExpiredOrderId(Integer clientId) {
        String key=FLASH_UUID_PREFIX+clientId.toString();
        String id= (String) redisUtils.get(key);
        redisUtils.del(key);
        return id;
    }
}
