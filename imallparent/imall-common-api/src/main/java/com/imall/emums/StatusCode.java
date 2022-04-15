package com.imall.emums;

import lombok.Getter;
import org.springframework.http.HttpStatus ;

import java.io.Serializable;

/**
 * @author Nocdy
 * @Description TODO 描述业务码
 * @Date 2022/2/7 20:56
 */
@Getter
public enum StatusCode implements Serializable {

    //每个枚举字段由状态码和该码的描述信息组成

    USER_REGISTRY_FAIL(2003,HttpStatus.BAD_REQUEST,"注册失败，用户已存在"),

    USER_NAME_ALREADY_EXIST(2004, HttpStatus.BAD_REQUEST, "注册用户已存在已经存在"),

    VENDOR_REGISTRY_FAIL(2005,HttpStatus.BAD_REQUEST,"该商店已注册"),

    LOGIN_FAIL(2006,HttpStatus.BAD_REQUEST,"登陆失败,用户不存在或密码错误"),

    SUCCESS(2000,HttpStatus.OK,"业务成功"),

    REGISTRY_SUCCESS(2001,HttpStatus.CREATED,"注册成功"),

    LOGIN_SUCCESS(2002,HttpStatus.OK,"登陆成功"),

    ROLE_NOT_FOUND(2007,HttpStatus.NOT_FOUND,"指定角色未找到角色"),


    ERROR(5000,HttpStatus.INTERNAL_SERVER_ERROR,"发生错误，请联系管理员或稍后重试"),

    EXCEPTION(5001,HttpStatus.INTERNAL_SERVER_ERROR,"发生异常"),

    FALLBACK(5002,HttpStatus.SERVICE_UNAVAILABLE,"服务器不可用"),

    BLOCK(5003,HttpStatus.TOO_MANY_REQUESTS,"当人访问人数过多，请稍后尝试！");




    private final int code;
    private final String message;
    private final HttpStatus status;

    StatusCode(int code, HttpStatus status, String message){
        this.message=message;
        this.code=code;
        this.status=status;
    }


}
