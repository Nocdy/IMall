package com.imall.messagehandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Nocdy
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.imall")
public class MessageHandlerApplication {


    public static void main(String[] args) {
        SpringApplication.run(MessageHandlerApplication.class,
                args);

    }




}

