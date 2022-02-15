package com.imall.loginproducer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/11 9:52
 */
@Data
@AllArgsConstructor
public class LoginRequest {

    private String password;

    private String loginAccount;

    private Boolean rememberMe;

    private String role;


}
