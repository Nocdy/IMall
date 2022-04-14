package com.imall.clientconsumer.service.impl;

import com.imall.clientconsumer.service.LoginService;
import com.imall.clientconsumer.utils.ExceptionResultUtils;
import com.imall.dto.LoginRequest;
import com.imall.dto.Result;
import com.imall.dto.UserRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Service;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/4/13 11:08
 */
@Service
@Slf4j
public class LoginFallbackServiceImpl implements FallbackFactory<LoginService> {

    @Override
    public LoginService create(Throwable cause) {
        return new LoginService() {
            @Override
            public Result<Object> registry(UserRegistry userRegistry) {
                log.error("调用注册服务时发生错误");
                return ExceptionResultUtils.returnResult(cause);
            }

            @Override
            public Result<Object> login(LoginRequest loginRequest) {
                log.error("调用登录服务时发生错误");
                return ExceptionResultUtils.returnResult(cause);
            }

            @Override
            public Result<Object> logout() {
                log.error("调用注销服务时发生错误");
                return ExceptionResultUtils.returnResult(cause);
            }

            @Override
            public Result<Object> getClientInfo() {
                log.error("调用获取用户信息时发生错误");
                return ExceptionResultUtils.returnResult(cause);
            }

            @Override
            public Result<Object> getUserId() {
                log.error("调用获取客户id服务时发生错误");
                return ExceptionResultUtils.returnResult(cause);
            }
        };
    }
}
