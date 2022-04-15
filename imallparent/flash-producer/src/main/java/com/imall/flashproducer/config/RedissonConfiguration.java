package com.imall.flashproducer.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/19 17:56
 */
@Configuration
public class RedissonConfiguration {

    @Value("${REDISSON_CONNECT_ADDR}")
    private String connectString;

    @Value("${REDISSON_CONNECT_PASS}")
    private String password;


    @Bean
    public RedissonClient getRedisson(){
        Config config=new Config();
        config.useSingleServer()
                .setDatabase(0)
                .setAddress(connectString)
                .setPassword(password);
        config.setCodec(new StringCodec());
        return Redisson.create(config);
    }
}
