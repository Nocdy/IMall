package com.imall.loginproducer.service;

import com.imall.dto.Result;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/23 10:50
 */
public interface GetInfoService {


    /**
     * 获取当前已登录顾客信息
     * @return 返回用户信息
     */
   Result<Object> getCurrentClientInfo();


    /**
     * 获取客户id
     * @return 返回客户id
     */
    Result<Object> getUserId();

}
