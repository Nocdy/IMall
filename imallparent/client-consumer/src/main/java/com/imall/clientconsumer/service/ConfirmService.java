package com.imall.clientconsumer.service;

import com.imall.clientconsumer.config.FeignConfiguration;
import com.imall.dto.OrderFlag;
import com.imall.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/27 20:22
 */
@Component
@FeignClient(value = "flash-producer",configuration = FeignConfiguration.class)
public interface ConfirmService {
    @PostMapping("/confirmOrder")
    Result<Object> confirmOrder(@RequestBody OrderFlag orderFlag);

}
