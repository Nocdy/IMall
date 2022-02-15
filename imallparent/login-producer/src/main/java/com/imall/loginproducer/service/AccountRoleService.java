package com.imall.loginproducer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import entites.users.AccountRole;

import java.util.List;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/12 16:59
 */
public interface AccountRoleService extends IService<AccountRole> {

    /**
     * 查询用户所对应的角色
     * @param userId 登录id
     * @return 角色列表
     */
    List<AccountRole> getRolesIdByUserId(String userId);
}
