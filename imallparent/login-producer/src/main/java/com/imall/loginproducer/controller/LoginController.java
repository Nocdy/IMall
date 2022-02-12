package com.imall.loginproducer.controller;

import com.imall.loginproducer.dto.UserRegistry;
import com.imall.loginproducer.service.LoginService;
import dto.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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


}
