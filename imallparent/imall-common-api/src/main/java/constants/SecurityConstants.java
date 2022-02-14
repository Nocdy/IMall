package constants;

/**
 * @author shuang.kou
 * @description Spring Security相关配置常量
 */
public final class SecurityConstants {

    /**
     * 角色的key
     **/
    public static final String ROLE_CLAIMS = "rol";

    /**
     * rememberMe 为 false 的时候过期时间是1个小时
     */
    public static final long EXPIRATION = 60 * 60L;

    /**
     * rememberMe 为 true 的时候过期时间是7天
     */
    public static final long EXPIRATION_REMEMBER = 60 * 60 * 24 * 7L;

    /**
     * JWT签名密钥硬编码到应用程序代码中，存放在.properties文件中。
     */
    public static final String JWT_SECRET_KEY = "JWT_SECRET_KEY";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

    // Swagger WHITELIST
    public static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui.html",
            "/swagger-ui/*",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/webjars/**",
    };


    // System WHITELIST
    public static final String[] SYSTEM_WHITELIST = {
            "/login",
            "/registry",
    };

    private SecurityConstants() {
    }

}
