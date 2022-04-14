package com.imall.clientconsumer.service.impl;

import com.imall.clientconsumer.service.FlashService;
import com.imall.clientconsumer.utils.ExceptionResultUtils;
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
public class FlashFallBackServiceImpl implements FallbackFactory<FlashService> {
    @Override
    public FlashService create(Throwable cause) {
        return (cid, gid) -> {
            log.error("调用抢购服务时发生错误");
            return ExceptionResultUtils.returnResult(cause);
        };
    }
}
