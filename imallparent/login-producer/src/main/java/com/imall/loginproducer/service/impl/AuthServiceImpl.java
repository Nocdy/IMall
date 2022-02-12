package com.imall.loginproducer.service.impl;

import com.imall.loginproducer.dto.LoginRequest;
import com.imall.loginproducer.service.AuthService;
import com.imall.loginproducer.service.LoginAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.imall.loginproducer.utils.CurrentUserUtils;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/11 16:56
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final LoginAccountService loginAccountService;

    private final StringRedisTemplate stringRedisTemplate;

    private final CurrentUserUtils currentUserUtils;

    @Override
    public String createToken(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public void removeToken() {

    }
}
