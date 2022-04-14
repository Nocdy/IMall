package com.imall.clientconsumer.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/4/12 1:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MyException extends RuntimeException {
    // 自定义异常代码

    private int code;
    private String uri;
    // 构造方法

    public MyException(String message, int code,String uri) {
        super(message);
        this.code = code;
        this.uri=uri;
    }
}
