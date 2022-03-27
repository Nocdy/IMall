package com.imall.shoppingproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author  Nocdy
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.imall")
public class ShoppingProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingProducerApplication.class, args);
    }


}
