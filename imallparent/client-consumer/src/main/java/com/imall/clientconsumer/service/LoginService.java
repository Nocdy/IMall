package com.imall.clientconsumer.service;

import com.imall.clientconsumer.config.FeignConfiguration;
import com.imall.dto.LoginRequest;
import com.imall.dto.Result;
import com.imall.dto.UserRegistry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/15 16:19
 */
@Component
@FeignClient(value = "login-service-producer",configuration = FeignConfiguration.class)
public interface LoginService {

    /**
     * 调用注册功能
     * @param userRegistry 注册请求
     * @return 返回注册结果
     */
    @PostMapping("/registry")
    Result<Object> registry(@RequestBody UserRegistry userRegistry);

    /**
     * 调用登录功能
     * @param loginRequest 登录请求
     * @return 返回登录结果
     */
    @PostMapping("/login")
    Result<Object> login(@RequestBody LoginRequest loginRequest);

    /**
     * 调用注销功能
     * @return 返回注销结果
     */
    @PostMapping("/logout")
    Result<Object> logout();

    /**
     * 获取当前用户信息
     * @param token 用户登录生成的token
     * @return 返回用户信息
     */
    @GetMapping("/getClientInfo")
    Result<Object> getClientInfo(@RequestHeader("Authorization")String token);
}
