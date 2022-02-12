package com.imall.loginproducer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imall.loginproducer.dao.UserMapper;
import com.imall.loginproducer.service.UserService;
import entites.users.User;
import org.springframework.stereotype.Service;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/8 16:10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User getByPhone(String phone) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .select("id","name","phone","address","email","nick_name")
                .eq("phone",phone);
        return getOne(queryWrapper);
    }

    @Override
    public Integer savaAndReturnId(User user) {
        if(save(user)){
            return getByPhone(user.getPhone()).getId();
        }
        else{
            return -1;
        }
    }
}
