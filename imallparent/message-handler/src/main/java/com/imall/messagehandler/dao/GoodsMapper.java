package com.imall.messagehandler.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imall.entities.mall.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/20 0:29
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 根据id加锁读取最新数据
     * @param id 商品id
     * @return 返回商品信息
     */
    Goods findByGoodsIdForUpdate(@Param("id") Integer id);


}
