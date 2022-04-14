package com.imall.clientconsumer.service.impl;

import com.imall.clientconsumer.service.ConfirmService;
import com.imall.clientconsumer.utils.ExceptionResultUtils;
import com.imall.dto.OrderFlag;
import com.imall.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Service;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/4/13 11:05
 */
@Service
@Slf4j
public class ConfirmFallbackServiceImpl implements FallbackFactory<ConfirmService>{

    @Override
    public ConfirmService create(Throwable cause) {
        return new ConfirmService() {
            @Override
            public Result<Object> confirmOrder(OrderFlag orderFlag) {
                log.error("调用确认订单服务时发生错误");
                return ExceptionResultUtils.returnResult(cause);
            }

            @Override
            public Result<Object> showOrder(OrderFlag orderFlag) {
                log.error("调用展示订单服务时发生错误");
                return ExceptionResultUtils.returnResult(cause);
            }
        };
    }
}
