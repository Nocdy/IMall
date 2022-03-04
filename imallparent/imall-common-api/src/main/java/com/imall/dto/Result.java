package com.imall.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/8 11:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Result<T> {
    private T data;
    private int code;
    private String message;

    public Result(int code,String message){
        this(null,code,message);
    }

}
