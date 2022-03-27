package com.imall.shoppingproducer.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imall.entities.mall.Goods;
import com.imall.entities.mall.OrderList;
import com.imall.shoppingproducer.dao.OrderListMapper;
import com.imall.shoppingproducer.service.IMessage;
import com.imall.shoppingproducer.service.ShoppingListService;
import com.imall.utils.RedisUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.imall.constants.Constant.ORDER_LIST_PREFIX;
import static com.imall.constants.Constant.REDIS_EXPIRE_ONE_DAY;
import static com.imall.constants.MessageQueueConstants.INPUT_0_CHANNEL;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/14 19:05
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingListServiceImpl extends ServiceImpl<OrderListMapper, OrderList> implements ShoppingListService {

    private final RedisUtils redisUtils;

    private final ObjectMapper objectMapper;

    private final IMessage iMessage;

    @Override
    public OrderList showList(Integer cid) {
        String key = ORDER_LIST_PREFIX + cid.toString();
        if (redisUtils.hasKey(key)) {
            return objectMapper.convertValue(redisUtils.get(key), OrderList.class);
        } else {
           OrderList result=getById(cid);
           if(result==null){
               OrderList newList=new OrderList();
               newList.setClientId(cid);
               getBaseMapper().insert(newList);
               result=newList;
           }
           redisUtils.set(key,result,REDIS_EXPIRE_ONE_DAY);
           return result;
        }
    }

    @Override
    public void addList(Integer cid, Goods goods) {
        String key=ORDER_LIST_PREFIX+cid.toString();
        OrderList updateList=showList(cid);
        List<Goods> goodsList=updateList.getGoodsList();
        if(goodsList!=null){
            goodsList.add(goods);
        }
        else {
            goodsList=new ArrayList<>();
            goodsList.add(goods);
        }
        updateList.setGoodsList(goodsList);
        iMessage.sendMessage(JSON.toJSONString(updateList),INPUT_0_CHANNEL);
        redisUtils.set(key,updateList,REDIS_EXPIRE_ONE_DAY);

    }
}
