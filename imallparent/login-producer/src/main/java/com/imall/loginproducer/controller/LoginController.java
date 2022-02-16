package com.imall.loginproducer.controller;

import com.imall.loginproducer.service.LoginService;
import dto.LoginRequest;
import dto.Result;
import dto.UserRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/7 20:09
 */

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/registry")
    public Result<Object> registry(@RequestBody UserRegistry userRegistry){
        return loginService.registry(userRegistry);
    }

    @PostMapping("/login")
    public Result<Object> login(@RequestBody LoginRequest loginRequest){
        return loginService.login(loginRequest);
    }

    @PostMapping("/logout")
    public Result<Object> logout(){
        return loginService.logout();
    }


}
