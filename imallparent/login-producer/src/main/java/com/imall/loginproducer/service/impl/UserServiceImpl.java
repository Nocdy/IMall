package com.imall.loginproducer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imall.loginproducer.dao.UserMapper;
import com.imall.loginproducer.service.UserService;
import entites.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/11 22:14
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public boolean save(User entity) {
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        return super.save(entity);
    }

    @Override
    public Boolean check(String currentPassword, String password) {
        return this.bCryptPasswordEncoder.matches(currentPassword,password);
    }

    @Override
    public User getByUserName(String userName) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("user_name",userName);
        return getOne(queryWrapper);
    }
}