package com.imall.clientconsumer.controller;

import com.imall.clientconsumer.service.LoginService;
import dto.LoginRequest;
import dto.Result;
import dto.UserRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/15 17:10
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public Result<Object> login(@RequestBody LoginRequest loginRequest){
        return loginService.login(loginRequest);
    }

    @PostMapping("/registry")
    public Result<Object> registry(@RequestBody UserRegistry userRegistry){
        return loginService.registry(userRegistry);
    }

    @PostMapping("/logout")
    public Result<Object> logout(){
        return loginService.logout();
    }



}
