package com.imall.loginproducer.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imall.entities.users.Vendor;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/8 11:29
 */
public interface VendorService extends IService<Vendor> {

    /**
     * 根据商店名字查询商家信息
     * @param shopName 商店名
     * @return 返回商家信息，如果没有则返回null
     */
    Vendor getByShopName(String shopName);

}
