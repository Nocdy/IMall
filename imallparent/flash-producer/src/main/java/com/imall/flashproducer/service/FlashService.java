package com.imall.flashproducer.service;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/19 14:44
 */
public interface FlashService {

    /**
     * 购买闪购商品
     * @param clientId 顾客id
     * @param goodsId 商品id
     * @return  购买是否成功
     */
    Boolean purchase(Integer clientId,Integer goodsId);

    /**
     * 回滚抢购订单
     * @param goodId  商品id
     */
    void handleExpiredOrder(Integer goodId);

    /**
     * 抢购结束
     * @param goodsId 商品id
     */
    void setFlashEnd(String goodsId);


}
