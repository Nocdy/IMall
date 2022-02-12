package emums;

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

    USER_REGISTRY_FAIL(1001,HttpStatus.BAD_REQUEST,"注册失败，用户已存在"),

    USER_NAME_ALREADY_EXIST(1000, HttpStatus.BAD_REQUEST, "注册用户已存在已经存在"),

    REGISTRY_SUCCESS(2000,HttpStatus.OK,"注册成功"),

    VENDOR_REGISTRY_FAIL(1002,HttpStatus.BAD_REQUEST,"该商店已注册"),

    ROLE_NOT_FOUND(3001,HttpStatus.NOT_FOUND,"指定角色未找到角色"),

    ERROR(500,HttpStatus.INTERNAL_SERVER_ERROR,"发生错误，请联系管理员或稍后重试"),

    METHOD_ARGUMENT_NOT_VALID(1003, HttpStatus.BAD_REQUEST, "方法参数验证失败");

    private final int code;
    private final String message;
    private final HttpStatus status;

    StatusCode(int code, HttpStatus status, String message){
        this.message=message;
        this.code=code;
        this.status=status;
    }


}
