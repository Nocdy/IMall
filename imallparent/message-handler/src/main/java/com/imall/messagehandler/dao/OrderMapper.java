package com.imall.messagehandler.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imall.entities.mall.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/20 1:18
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
