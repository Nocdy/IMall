package com.imall.loginproducer.controller;

import com.imall.loginproducer.service.GetInfoService;
import com.imall.loginproducer.service.LoginService;
import dto.LoginRequest;
import dto.Result;
import dto.UserRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    private final GetInfoService getInfoService;

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

    @GetMapping("/getClientInfo")
    @PreAuthorize("hasAnyRole('ROLE_client')")
    public Result<Object> getClientInfo(){
        return getInfoService.getCurrentClientInfo();
    }


}
