package com.imall.flashproducer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.imall.dto.Result;
import com.imall.emums.StatusCode;
import com.imall.entities.mall.Order;
import com.imall.flashproducer.service.FlashService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/19 15:19
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class FlashController {

    private final FlashService flashService;



    @Bean
    Consumer<Message<String>> rollBack(){
        return stringMessage -> {
           String message=stringMessage.getPayload();
           Order order= JSON.toJavaObject(JSON.parseObject(message), Order.class);
           order.getGoodsList().forEach(goods -> {
               flashService.handleExpiredOrder(goods.getId());
           });
        };
    }


    @Bean
    Consumer<Message<String>> endSell(){
        return stringMessage -> {
            String gid=stringMessage.getPayload();
            flashService.setFlashEnd(gid);
        };
    }




    @RequestMapping("/testFlash/{cid}/{gid}")
    public Result<Object> flash(@PathVariable("cid") Integer cid,
                        @PathVariable("gid") Integer gid){
        JSONObject result=new JSONObject();
        if(flashService.purchase(cid,gid)){
            result.put("message","抢购成功");
        }
        else {
            result.put("message","抢购失败，商品已被抢光或者抢购数量已达上限");
        }
        return new Result<>(result,
                StatusCode.SUCCESS.getCode(),
                StatusCode.SUCCESS.getMessage());
    }


}
