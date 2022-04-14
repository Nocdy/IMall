package com.imall.clientconsumer.config;

import com.alibaba.fastjson.JSON;
import com.imall.clientconsumer.dto.FeignFailedResult;
import com.imall.clientconsumer.exception.MyException;
import feign.*;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/24 10:35
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class FeignConfiguration implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes!=null){
            HttpServletRequest request=attributes.getRequest();
            Enumeration<String> headerNames=request.getHeaderNames();
            if(headerNames!=null){
                while(headerNames.hasMoreElements()){
                    String name=headerNames.nextElement();
                    String values=request.getHeader(name);
                    requestTemplate.header(name,values);
                }
            }
        }

    }
    @Bean
    public ErrorDecoder errorDecoder() {
        return new UserErrorDecoder();
    }
    /**
     * 重新实现feign的异常处理，捕捉restful接口返回的json格式的异常信息
     *
     */
    public static class UserErrorDecoder extends ErrorDecoder.Default {

        @Override
        public Exception decode(String methodKey, Response response) {
            Exception exception = super.decode(methodKey, response);
            // 如果是RetryableException，则返回继续重试
            if (exception instanceof RetryableException) {
                return exception;
            }
            try {
                // 如果是FeignException，则对其进行处理，并抛出BusinessException
                if (exception instanceof FeignException && ((FeignException) exception).responseBody().isPresent()) {
                    ByteBuffer responseBody = ((FeignException) exception).responseBody().get();
                    String bodyText = StandardCharsets.UTF_8.newDecoder().decode(responseBody.asReadOnlyBuffer()).toString();
                    // 将异常信息，转换为ExceptionInfo对象
                    FeignFailedResult exceptionInfo = JSON.parseObject(bodyText, FeignFailedResult.class);
                    // 如果excepiton中code不为空，则使用该code，否则使用默认的错误code
                    int code = exceptionInfo.getStatus();
                    // 如果excepiton中message不为空，则使用该message，否则使用默认的错误message
                    String message = exceptionInfo.getMsg();
                    return new MyException(message, code,exceptionInfo.getUri());
                }
            } catch (IOException ex) {
                log.error(ex.getMessage(), ex);
            }
            return exception;
        }
    }

}
