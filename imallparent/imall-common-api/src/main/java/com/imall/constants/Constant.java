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
    public final static Long REDIS_EXPIRE_AN_HOUR=60*60L;
    public final static Long REDIS_EXPIRE_HALF_HOUR=60*30L;
    public final static Long REDIS_EXPIRE_ONE_DAY=60*60*24L;
    public final static Long REDIS_EXPIRE_A_QUARTER=60*15L;

    public final static String REDIS_ALL_GOODS_KEY="AllGoods";

    public final static String FLASH_PREFIX="FLASH_";
    public final static String ORDER_LIST_PREFIX="List_";
    public final static String FLASH_ORDER_PREFIX="FLASH_Order_";
    public final static String ORDER_PREFIX="Order_";
    public final static String FLASH_UUID_PREFIX="FLASH_ID_";




    /**
     * 库存不足
     */
    public static final Long LOW_STOCK = 0L;



    private Constant(){

    }


}
