package com.imall.loginproducer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imall.entities.users.User;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/11 22:12
 */
public interface UserService extends IService<User> {

    /**
     * 检查密码是否错误
     * @param currentPassword 输入的密码
     * @param password 账号的密码
     * @return 返回是否正确
     */
    Boolean check(String currentPassword,String password);


    /**
     * 根据用户名查询
     * @param userName 用户名
     * @return 返回实体
     */
    User getByUserName(String userName);

}
