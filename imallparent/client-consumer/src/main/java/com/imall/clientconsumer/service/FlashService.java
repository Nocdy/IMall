package com.imall.clientconsumer.service;

import com.imall.clientconsumer.config.FeignConfiguration;
import com.imall.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/27 20:14
 */
@Component
@FeignClient(value = "flash-producer", configuration = FeignConfiguration.class)
public interface FlashService {

    @RequestMapping("/testFlash/{cid}/{gid}")
    Result<Object> flash(@PathVariable("cid") Integer cid,
                         @PathVariable("gid") Integer gid);

}
