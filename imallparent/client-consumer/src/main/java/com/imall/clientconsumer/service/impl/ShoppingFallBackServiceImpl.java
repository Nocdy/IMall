package com.imall.clientconsumer.service.impl;

import com.imall.clientconsumer.service.ShoppingService;
import com.imall.clientconsumer.utils.ExceptionResultUtils;
import com.imall.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Service;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/4/14 2:49
 */
@Service
@Slf4j
public class ShoppingFallBackServiceImpl implements FallbackFactory<ShoppingService> {
    @Override
    public ShoppingService create(Throwable cause) {
        return new ShoppingService() {
            @Override
            public Result<Object> getList(int listNum) {
                log.error("调用获取商品信息服务时发生错误");
                return ExceptionResultUtils.returnResult(cause);
            }

            @Override
            public Result<Object> getOne(int cid, int gid) {
                log.error("调用获取单个服务时发生错误");
                return ExceptionResultUtils.returnResult(cause);
            }

            @Override
            public Result<Object> addList(int cid, int gid) {
                log.error("调用添加购物车服务时发生错误");
                return ExceptionResultUtils.returnResult(cause);
            }

            @Override
            public Result<Object> showList(int cid) {
                log.error("调用展示购物车服务时发生错误");
                return ExceptionResultUtils.returnResult(cause);
            }

            @Override
            public Result<Object> purchase(int cid) {
                log.error("调用结账服务时发生错误");
                return ExceptionResultUtils.returnResult(cause);
            }
        };
    }
}
