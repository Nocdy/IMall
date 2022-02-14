package com.imall.loginproducer.utils;

import entites.users.JwtUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/11 16:58
 */
@Component
public class CurrentAccountUtils {

    public JwtUser getUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null&&authentication.getPrincipal()!=null){
            return (JwtUser) authentication.getPrincipal();
        }
        return null;
    }


}
