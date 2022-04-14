package com.imall.messagehandler.contoller;

import com.alibaba.fastjson.JSON;
import com.imall.dto.OrderFlag;
import com.imall.dto.Result;
import com.imall.entities.mall.Goods;
import com.imall.entities.mall.Order;
import com.imall.entities.mall.OrderList;
import com.imall.messagehandler.service.GenerateOrderService;
import com.imall.messagehandler.service.OrderConsumerService;
import com.imall.messagehandler.service.OrderListService;
import com.imall.messagehandler.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/15 11:15
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class MessageController{

    private final  OrderService orderService;

    private final OrderListService orderListService;

    private final GenerateOrderService generateOrderService;

    private final OrderConsumerService orderConsumerService;

    @Bean
    Consumer<Message<String>> input(){
        return stringMessage -> {
            String message=stringMessage.getPayload();
            log.info("接收到用户购物车信息: {}",message);
            OrderList orderList= JSON.toJavaObject(JSON.parseObject(message),OrderList.class);
            List<Goods> showList=new ArrayList<>();
            orderList.getGoodsList().forEach(goods -> {
                Goods shortGoods=new Goods();
                shortGoods.setId(goods.getId());
                showList.add(shortGoods);
            });
            String listJson=JSON.toJSONString(showList);
            orderList.setShoppingList(listJson);
            orderListService.updateById(orderList);

        };
    }

    @Bean
    Consumer<Message<String>> flashOrder(){
        return stringMessage -> {
            log.info("处理闪购订单");
            String msg=stringMessage.getPayload();
            Order order= JSON.toJavaObject(JSON.parseObject(msg),Order.class);
            generateOrderService.generate(order,order.getGoodsIdList());
        };
    }

    @Bean
    Consumer<Message<String>> commonOrder(){
        return stringMessage -> {
            log.info("处理普通订单");
            String msg=stringMessage.getPayload();
            Order order=JSON.toJavaObject(JSON.parseObject(msg),Order.class);
            if(generateOrderService.generate(order)){
                log.info("生成订单成功");
            }
        };
    }

    @PostMapping("/confirmOrder")
    public Result<Object> confirmOrder(@RequestBody OrderFlag orderFlag){
        return orderConsumerService.confirm(orderFlag.getClientId(),orderFlag.getIsFlash());
    }

    @PostMapping("/showOrder")
    public Result<Object> showOrder(@RequestBody OrderFlag orderFlag){
        return orderService.showOrder(orderFlag.getClientId(),orderFlag.getIsFlash());
    }



}
