package com.imall.loginproducer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import entites.users.Role;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/12 16:43
 */
public interface RoleService extends IService<Role> {


    /**
     * 根据角色名字查找角色id
     * @param name 角色名字
     * @return 返回id
     */
    Integer getIdByName(String name);

}
