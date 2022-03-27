package com.imall.flashproducer.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/19 17:56
 */
@Configuration
public class RedissonConfiguration {

    @Bean
    public RedissonClient getRedisson(){
        Config config=new Config();
        config.useSingleServer()
                .setAddress("redis://127.0.0.1:6379?auth=12345")
                .setPassword("12345");
        config.setCodec(new StringCodec());
        return Redisson.create(config);
    }
}
