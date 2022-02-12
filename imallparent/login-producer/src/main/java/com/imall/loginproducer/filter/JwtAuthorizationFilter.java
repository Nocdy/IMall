package com.imall.loginproducer.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author Nocdy
 * @Description TODO 自定义Security过滤器
 * @Date 2022/2/11 16:41
 */
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final StringRedisTemplate stringRedisTemplate;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,StringRedisTemplate stringRedisTemplate) {
        super(authenticationManager);
        this.stringRedisTemplate=stringRedisTemplate;
    }


}
