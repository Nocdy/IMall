package com.imall.loginproducer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imall.loginproducer.dao.VendorMapper;
import com.imall.loginproducer.service.VendorService;
import entites.users.Vendor;
import org.springframework.stereotype.Service;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/10 15:04
 */
@Service
public class VendorServiceImpl extends ServiceImpl<VendorMapper, Vendor> implements VendorService {

    @Override
    public Vendor getByShopName(String shopName) {
        QueryWrapper<Vendor> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .select("vendor_id","vendor_name","vendor_contact_number","vendor_contact_name")
                .eq("vendor_name",shopName);
        return getOne(queryWrapper);
    }

}
