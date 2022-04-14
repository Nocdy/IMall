package com.imall.clientconsumer.dto;

import lombok.Data;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/4/12 1:03
 */
@Data
public class FeignFailedResult {
    private String msg;
    private int status;
    private String uri;
}
