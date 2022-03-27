package com.imall.shoppingproducer.service;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/24 11:29
 */
public interface PurchaseService {

    /**
     * 从购物车中购买商品
     * @param clientId 用户id
     */
    void purchase(Integer clientId);

}
