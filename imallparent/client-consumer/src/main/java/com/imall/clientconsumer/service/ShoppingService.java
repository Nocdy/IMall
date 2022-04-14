package com.imall.clientconsumer.service;

import com.imall.clientconsumer.config.FeignConfiguration;
import com.imall.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/27 20:22
 */
@Component
@FeignClient(value = "shopping-producer", configuration = FeignConfiguration.class)
public interface ShoppingService {

    @GetMapping("/getList/{listNum}")
    Result<Object> getList(@PathVariable("listNum") int listNum);

    @GetMapping("/getOne/{cid}/{gid}")
    Result<Object> getOne(@PathVariable("cid") int cid,
                          @PathVariable("gid") int gid);


    @GetMapping("/addList/{cid}/{gid}")
    Result<Object> addList(@PathVariable("cid") int cid,
                           @PathVariable("gid") int gid);

    @GetMapping("/showList/{cid}")
    Result<Object> showList(@PathVariable("cid") int cid);


    @GetMapping("/purchase/{cid}")
    Result<Object> purchase(@PathVariable("cid") int cid);


}
