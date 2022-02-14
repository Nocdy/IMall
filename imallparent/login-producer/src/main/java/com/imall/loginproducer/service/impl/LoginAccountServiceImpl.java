package com.imall.loginproducer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imall.loginproducer.dao.LoginAccountMapper;
import com.imall.loginproducer.service.LoginAccountService;
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
public class LoginAccountServiceImpl extends ServiceImpl<LoginAccountMapper, User> implements LoginAccountService {


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
}