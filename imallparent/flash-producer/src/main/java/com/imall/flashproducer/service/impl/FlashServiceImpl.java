package com.imall.flashproducer.service.impl;

import com.alibaba.fastjson.JSON;
import com.imall.entities.mall.Order;
import com.imall.flashproducer.service.FlashService;
import com.imall.flashproducer.service.IMessage;
import com.imall.flashproducer.utils.FlashUtils;
import com.imall.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

import static com.imall.constants.Constant.FLASH_PREFIX;
import static com.imall.constants.MessageQueueConstants.FLASH_ORDER_0_CHANNEL;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/19 15:16
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FlashServiceImpl implements FlashService {


    private final RedisUtils redisUtils;

    private final FlashUtils flashUtils;

    private final IMessage iMessage;

    @Override
    public Boolean purchase(Integer clientId,Integer goodsId) {
        String key=FLASH_PREFIX+goodsId.toString();
        String clientKey=FLASH_PREFIX+clientId.toString();
        int num=Integer.parseInt(redisUtils.get(clientKey).toString());
        if(num>0) {
            long count = flashUtils.stock(key);
            if(count==1){
                redisUtils.decr(clientKey,1L);
                Order order=new Order();
                order.setId(UUID.randomUUID().toString());
                order.setClientId(clientId);
                order.setIsFinish(false);
                order.setGoodsIdList(Collections.singletonList(goodsId));
                iMessage.sendMessage(JSON.toJSONString(order),FLASH_ORDER_0_CHANNEL);
                log.info("用户 {} 购得 {} 件 {} 商品",clientId,count,goodsId);
                return true;
            }
            else if(count==-1){
                log.info("抢购已结束");
            }
            else {
                log.info("用户 {} 抢不到商品", clientId);
            }
        }
        else {
            log.info("用户 {} 无法再购买", clientId);
        }
        return false;
    }



    @Override
    public void handleExpiredOrder(Integer goodId) {
        String key=FLASH_PREFIX+goodId.toString();
        long count=flashUtils.rollBack(key);
        log.info("回滚 {} 件 {} 商品 ",count,goodId);
    }

    @Override
    public void setFlashEnd(String goodsId) {
        String key=FLASH_PREFIX+goodsId;
        flashUtils.setEnd(key);
    }
}
