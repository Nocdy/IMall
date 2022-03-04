package com.imall.clientconsumer.controller;

import com.imall.clientconsumer.service.LoginService;
import com.imall.dto.LoginRequest;
import com.imall.dto.Result;
import com.imall.dto.UserRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getClientInfo")
    public Result<Object> getClientInfo(@RequestHeader ("Authorization")String token){
        return loginService.getClientInfo(token);
    }



}
