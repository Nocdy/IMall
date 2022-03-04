package com.imall.loginproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Nocdy
 */
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.imall")
public class LoginProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginProducerApplication.class, args);
    }

}
