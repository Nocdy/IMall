package com.imall.clientconsumer.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.imall.dto.Result;
import com.imall.emums.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/4/13 10:49
 */
@Component
@Slf4j
public class MyBlockHandler {

    public static Result<Object> flashHandler(Integer clientId,Integer goodsId, BlockException exception){
        JSONObject result=new JSONObject();
        log.info("用户{}在调用商品{}的抢购服务时被限流",clientId.toString(),goodsId.toString());
        log.error("限流异常",exception);
        result.put("Exception",exception);
        return new Result<>(result,StatusCode.BLOCK.getCode(), StatusCode.BLOCK.getMessage());
    }


}
