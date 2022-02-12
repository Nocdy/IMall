package com.imall.loginproducer.service;

import com.imall.loginproducer.dto.UserRegistry;
import dto.Result;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/8 11:24
 */
public interface LoginService {

    /**
     *
     * @param userRegistry 前端返回的注册请求
     * @return 返回是否注册结果
     */
    Result<Object> registry(UserRegistry userRegistry);


    Result<Object> login();

    boolean logout();

}
