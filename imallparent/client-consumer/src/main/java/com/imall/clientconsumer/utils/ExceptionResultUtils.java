package com.imall.clientconsumer.utils;

import com.alibaba.fastjson.JSONObject;
import com.imall.clientconsumer.exception.MyException;
import com.imall.dto.Result;
import com.imall.emums.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/4/13 13:34
 */
@Component
public class ExceptionResultUtils {

    public static Result<Object> returnResult(Throwable throwable){
        throwable.printStackTrace();
        JSONObject result=new JSONObject();
       if(throwable instanceof MyException){
           MyException exception=(MyException) throwable;
           result.put("Exception",exception);
           return new Result<>(result,exception.getCode(),exception.getMessage());
       }
       else{
           result.put("Exception",throwable);
           return new Result<>(result, StatusCode.EXCEPTION.getCode(),StatusCode.EXCEPTION.getMessage());
       }
    }

}
