package com.imall.messagehandler.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imall.entities.mall.Goods;
import com.imall.entities.mall.Order;
import com.imall.messagehandler.service.GenerateOrderService;
import com.imall.messagehandler.service.GoodsService;
import com.imall.messagehandler.service.OrderService;
import com.imall.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.imall.constants.Constant.*;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/20 13:29
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GenerateOrderServiceImpl implements GenerateOrderService {

    private final ObjectMapper objectMapper;

    private final GoodsService goodsService;

    private final OrderService orderService;

    private final RedisUtils redisUtils;

    @Override
    public void generate(Order order, List<Integer> goodsIdList) {
        log.info("为 {} 用户生成订单",order.getClientId());
        String key=FLASH_ORDER_PREFIX+order.getClientId();
        Order oldOrder=objectMapper.convertValue(redisUtils.get(key),Order.class);
        if(oldOrder==null) {
            saveDate(order,key,new ArrayList<>());
        }
        else {
            oldOrder.setGoodsIdList(goodsIdList);
            oldOrder.setId(order.getId());
            saveDate(oldOrder,key,oldOrder.getGoodsList());
        }
    }

    @Override
    public boolean generate(Order order) {
        log.info("为 {} 用户生成订单",order.getClientId());
        String key=ORDER_PREFIX+order.getClientId();
        if(redisUtils.get(key)==null){
            order.convertListToJson();
            redisUtils.set(key,order,REDIS_EXPIRE_A_QUARTER);
            return orderService.save(order);
        }
        return false;

    }

    private void saveDate(Order order,String key,List<Goods> goodsList){
        order.setDate(LocalDateTime.now());
        order.getGoodsIdList().forEach(id -> goodsList.add(goodsService.getGoodsPessimistic(id)));
        order.setGoodsList(goodsList);
        order.convertListToJson();
        redisUtils.set(key,order,REDIS_EXPIRE_A_QUARTER);
        redisUtils.set(FLASH_UUID_PREFIX+order.getClientId(),order.getId(),REDIS_EXPIRE_AN_HOUR);
        orderService.save(order);
    }

    private String getId(){
        return UUID.randomUUID().toString();
    }

}
