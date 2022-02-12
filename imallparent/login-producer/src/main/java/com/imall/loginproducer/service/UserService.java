package com.imall.loginproducer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import entites.users.User;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/8 11:28
 */
public interface UserService extends IService<User> {

    /**
     * 根据手机号码获取用户信息
     * @param phone 手机号码
     * @return 返回用户信息，如果没有则返回null
     */
    User getByPhone(String phone);


    /**
     * 保存数据并返回用户id
     * @param user 保存实体
     * @return 返回id
     */
    Integer savaAndReturnId(User user);

}
