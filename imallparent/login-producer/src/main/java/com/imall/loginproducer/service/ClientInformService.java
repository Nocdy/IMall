package com.imall.loginproducer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imall.entities.users.ClientInform;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/8 11:28
 */
public interface ClientInformService extends IService<ClientInform> {

    /**
     * 根据手机号码获取用户信息
     * @param phone 手机号码
     * @return 返回用户信息，如果没有则返回null
     */
    ClientInform getInformationByPhone(String phone);

    /**
     * 根据手机号码查询登录id
     * @param phone 手机号码
     * @return 返回id
     */
    String getUserIdByPhone(String phone);

    /**
     * 根据邮箱查询登录id
     * @param email 邮箱
     * @return 返回id
     */
    String getUserIdByEmail(String email);

    /**
     * 通过登录名称获取用户信息
     * @param userName 登录名称
     * @return 返回用户信息
     */
    ClientInform getInformationByUserName(String userName);


}
