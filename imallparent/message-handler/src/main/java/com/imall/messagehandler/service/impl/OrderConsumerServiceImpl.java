package com.imall.messagehandler.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imall.dto.Result;
import com.imall.emums.StatusCode;
import com.imall.entities.mall.Order;
import com.imall.messagehandler.service.GoodsService;
import com.imall.messagehandler.service.IMessage;
import com.imall.messagehandler.service.OrderConsumerService;
import com.imall.messagehandler.service.OrderService;
import com.imall.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.imall.constants.MessageQueueConstants.END_SELL_0_CHANNEL;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/21 16:39
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderConsumerServiceImpl implements OrderConsumerService {

    private final GoodsService goodsService;

    private final OrderService orderService;

    private final IMessage iMessage;

    private final ObjectMapper objectMapper;

    private final RedisUtils redisUtils;


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Result<Object>  confirm(Integer clientId, boolean isFlash) {
        log.info("进行确认订单业务");
        JSONObject result = new JSONObject();
        String key=OrderServiceImpl.GENERATE_KEY.get(isFlash).apply(clientId.toString());
        AtomicBoolean status= new AtomicBoolean(true);
        if (orderService.confirmOrder(clientId, isFlash)) {
            Order order = objectMapper.convertValue(redisUtils.get(key), Order.class);
            order.getGoodsList().forEach(goods -> {
                redisUtils.del(goods.getId().toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                redisUtils.del(goods.getId().toString());
                if (!goodsService.updatePessimistic(goods)) {
                    if (goods.getIsPlash()) {
                        iMessage.sendMessage(goods.getId().toString(), END_SELL_0_CHANNEL);
                    }
                    try {
                        status.set(false);
                        throw new Exception("库存呢不足");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            orderService.delOrderInRedis(clientId, isFlash);
            if(status.get()){
                result.put("message", "确认订单成功!");
            }
            else {
                result.put("message", "库存不足，订单确认失败!");
            }
        } else {
            result.put("message", "你的订单已过期");
            Order rollBackOrder = orderService.getById(orderService.getExpiredOrderId(clientId));
            if (isFlash) {
                orderService.rollBackForFlash(rollBackOrder);
            }
        }
        return new Result<>(result,
                StatusCode.SUCCESS.getCode(),
                StatusCode.SUCCESS.getMessage());
    }
}
