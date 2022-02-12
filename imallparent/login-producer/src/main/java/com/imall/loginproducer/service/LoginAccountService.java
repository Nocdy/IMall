package com.imall.loginproducer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import entites.users.LoginAccount;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/11 22:12
 */
public interface LoginAccountService extends IService<LoginAccount> {

    /**
     * 检查密码是否错误
     * @param currentPassword 输入的密码
     * @param password 账号的密码
     * @return 返回是否正确
     */
    Boolean check(String currentPassword,String password);

}
