package com.imall.messagehandler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imall.entities.mall.OrderList;
import com.imall.messagehandler.dao.OrderListMapper;
import com.imall.messagehandler.service.OrderListService;
import org.springframework.stereotype.Service;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/19 14:12
 */
@Service
public class OrderListServiceImpl extends ServiceImpl<OrderListMapper, OrderList> implements OrderListService {
}
