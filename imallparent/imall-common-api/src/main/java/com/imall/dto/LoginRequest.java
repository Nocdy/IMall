package com.imall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/11 9:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    private String password;

    private String loginAccount;

    private Boolean rememberMe;

    private String role;


}
