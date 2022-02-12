package com.imall.loginproducer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entites.users.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/8 11:20
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
