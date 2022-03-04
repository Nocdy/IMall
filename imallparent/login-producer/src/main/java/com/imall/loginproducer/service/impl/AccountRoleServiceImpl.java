package com.imall.loginproducer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imall.loginproducer.dao.AccountRoleMapper;
import com.imall.loginproducer.service.AccountRoleService;
import com.imall.entities.users.AccountRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/12 17:01
 */
@Service
@RequiredArgsConstructor
public class AccountRoleServiceImpl extends ServiceImpl<AccountRoleMapper, AccountRole> implements AccountRoleService {

    @Override
    public List<AccountRole> getRolesIdByUserId(String userId) {
        QueryWrapper<AccountRole> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("user_id",userId);
        return getBaseMapper().selectList(queryWrapper);
    }
}
