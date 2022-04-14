package com.imall.messagehandler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imall.entities.mall.Goods;
import com.imall.messagehandler.dao.GoodsMapper;
import com.imall.messagehandler.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/20 0:30
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public Goods getGoodsPessimistic(Integer id){
        return getBaseMapper().findByGoodsIdForUpdate(id);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public  boolean updatePessimistic(Goods entity){
        Goods goods=getBaseMapper().findByGoodsIdForUpdate(entity.getId());
        if(goods!=null){
            if(goods.getCount()>0) {
                goods.setCount(goods.getCount() - 1);
                return updateById(goods);
            }
        }
        return false;
    }


}
