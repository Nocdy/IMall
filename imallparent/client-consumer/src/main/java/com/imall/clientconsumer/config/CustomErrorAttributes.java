package com.imall.clientconsumer.config;

import com.imall.clientconsumer.exception.MyException;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/4/12 1:56
 */
@Component
@Primary
public class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest,ErrorAttributeOptions  includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        Throwable error = this.getError(webRequest);
        if (error instanceof MyException) {
            errorAttributes.put("code", ((MyException) error).getCode());
        }
        return errorAttributes;
    }
}