package com.imall.loginproducer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imall.loginproducer.dao.RoleMapper;
import com.imall.loginproducer.service.RoleService;
import com.imall.entities.users.Role;
import org.springframework.stereotype.Service;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/12 16:53
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public Integer getIdByName(String name) {
        QueryWrapper<Role> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("role_name",name);
        return getOne(queryWrapper).getId();
    }
}
