package com.imall.shoppingproducer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import entities.mall.Goods;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/24 15:44
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    Goods selectAll();
}
