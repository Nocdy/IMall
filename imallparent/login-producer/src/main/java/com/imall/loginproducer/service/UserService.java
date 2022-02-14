package com.imall.loginproducer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import entites.users.ClientInform;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/8 11:28
 */
public interface UserService extends IService<ClientInform> {

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
    String getuserIdByPhone(String phone);

    /**
     * 保存数据并返回用户id
     * @param clientInform 保存实体
     * @return 返回id
     */
    Integer savaAndReturnId(ClientInform clientInform);

}
