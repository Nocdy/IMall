package constants;

/**
 * @author Nocdy
 * @Description、
 * @Date 2022/2/8 14:45
 */
public final class Constant {


    /**
     * 账号的角色分为商家和用户，不同的角色有不同的权限
     */
    public final static String VENDOR="vendor";
    public final static String CLIENT="client";
    public final static String VISITOR="visitor";
    public final static String ADMIN="admin";

    public final static Long REDIS_EXPIRE_DAYS=8L;

    private Constant(){

    }


}
