package com.imall.messagehandler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imall.entities.mall.Goods;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/20 0:29
 */
public interface GoodsService extends IService<Goods> {


    /**
     * 获取最新的数据
     * @param id 商品id
     * @return 商品信息
     */
    Goods getGoodsPessimistic(Integer id);

    /**
     * 原子减
     * @param entity 更新商品
     * @return 返回购买是否成功
     */
    boolean updatePessimistic(Goods entity);

}
