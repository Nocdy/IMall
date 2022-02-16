package com.imall.clientconsumer.controller;

import dto.LoginRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/15 20:38
 */
public class TestController {

    @Autowired
    LoginController loginController;

    @Test
    public  void  TestUrl(){
        LoginRequest loginRequest=new LoginRequest();
        Assert.notNull(loginRequest);

    }

}
