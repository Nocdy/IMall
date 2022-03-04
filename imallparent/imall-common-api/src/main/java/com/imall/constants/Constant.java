package com.imall.constants;

/**
 * @author Nocdy
 * @Description
 * @Date 2022/2/8 14:45
 */
public final class Constant {


    /**
     * 账号的角色分为商家和用户，不同的角色有不同的权限
     */
    public final static String VENDOR="vendor";
    public final static String CLIENT="client";
    public final static String VISITOR="visitor";
  //  public final static String ADMIN="admin";

    public final static Long REDIS_EXPIRE_EIGHT_DAYS=60*60*24*8L;
    public final static long REDIS_EXPIRE_HALF_HOUR=60*30;

    public final static String REDIS_ALL_GOODS_KEY="AllGoods";

    private Constant(){

    }


}
