package com.imall.loginproducer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imall.entities.users.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/11 22:12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
