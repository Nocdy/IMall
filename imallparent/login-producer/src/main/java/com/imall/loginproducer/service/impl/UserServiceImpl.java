package com.imall.loginproducer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imall.loginproducer.dao.UserMapper;
import com.imall.loginproducer.service.UserService;
import entites.users.ClientInform;
import org.springframework.stereotype.Service;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/8 16:10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, ClientInform> implements UserService {

    @Override
    public ClientInform getInformationByPhone(String phone) {
        QueryWrapper<ClientInform> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .select("id","name","phone","address","email","nick_name")
                .eq("phone",phone);
        return getOne(queryWrapper);
    }

    @Override
    public String getuserIdByPhone(String phone) {
        QueryWrapper<ClientInform> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .select("login_id")
                .eq("phone",phone);
        return getOne(queryWrapper).getUserId();
    }

    @Override
    public Integer savaAndReturnId(ClientInform clientInform) {
        if(save(clientInform)){
            return getInformationByPhone(clientInform.getPhone()).getId();
        }
        else{
            return -1;
        }
    }
}
