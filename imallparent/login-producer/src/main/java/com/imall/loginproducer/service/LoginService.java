package com.imall.loginproducer.service;

import dto.LoginRequest;
import dto.Result;
import dto.UserRegistry;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/8 11:24
 */
public interface LoginService {

    /**
     * 注册服务
     * @param userRegistry 前端返回的注册请求
     * @return 返回是否注册结果
     */
    Result<Object> registry(UserRegistry userRegistry);


    /**
     * 登录服务
     * @param loginRequest 前端登录请求
     * @return 返回登录结果
     */
    Result<Object> login(LoginRequest loginRequest);

    Result<Object> logout();

}
