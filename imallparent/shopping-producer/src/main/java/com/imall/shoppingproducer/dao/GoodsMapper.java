package com.imall.shoppingproducer.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imall.entities.mall.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

import java.util.List;

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

    /**
     * 获得当天秒杀的商品信息
     * @param handler 获取结果
     * @param todayStart 当天开始的时间0时
     * @param todayEnd 当天结束的时间
     */
    void selectTodayFlash(@Param("todayStart") String todayStart,
                          @Param("todayEnd") String todayEnd,
                          ResultHandler<Goods> handler);


}
