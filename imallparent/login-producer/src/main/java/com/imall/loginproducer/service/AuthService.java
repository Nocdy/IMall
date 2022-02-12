package com.imall.loginproducer.service;

import com.imall.loginproducer.dto.LoginRequest;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/11 16:54
 */
public interface AuthService {

    /**
     * 创建认证token
     * @param loginRequest 登录数据
     * @return 返回jwt token
     */
    String createToken(LoginRequest loginRequest);

    /**
     * 使token失效
     */
    void removeToken();

}
