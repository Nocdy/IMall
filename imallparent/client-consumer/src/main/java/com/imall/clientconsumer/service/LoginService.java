package com.imall.clientconsumer.service;

import dto.LoginRequest;
import dto.Result;
import dto.UserRegistry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/15 16:19
 */
@Component
@FeignClient(value = "login-service-producer")
public interface LoginService {

    /**
     * 调用注册功能
     * @param userRegistry
     * @return
     */
    @PostMapping("/registry")
    Result<Object> registry(@RequestBody UserRegistry userRegistry);

    /**
     * 调用登录功能
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    Result<Object> login(@RequestBody LoginRequest loginRequest);

    /**
     * 调用注册功能
     * @return
     */
    @PostMapping("/logout")
    Result<Object> logout();
}
