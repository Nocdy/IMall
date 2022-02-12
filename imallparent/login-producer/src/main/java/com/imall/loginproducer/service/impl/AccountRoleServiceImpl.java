package com.imall.loginproducer.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imall.loginproducer.dao.AccountRoleMapper;
import com.imall.loginproducer.service.AccountRoleService;
import entites.users.AccountRole;
import org.springframework.stereotype.Service;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/12 17:01
 */
@Service
public class AccountRoleServiceImpl extends ServiceImpl<AccountRoleMapper, AccountRole> implements AccountRoleService {
}
