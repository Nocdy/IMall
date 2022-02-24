package com.imall.clientconsumer.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/24 10:35
 */
@Configuration
@RequiredArgsConstructor
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
}
