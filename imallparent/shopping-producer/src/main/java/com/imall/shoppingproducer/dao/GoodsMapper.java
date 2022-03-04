package com.imall.shoppingproducer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imall.entities.mall.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.ResultHandler;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/24 15:44
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 用流式读取
     * @param handler 获取结果
     *
     */
    void selectAll(ResultHandler<Goods> handler);
}
