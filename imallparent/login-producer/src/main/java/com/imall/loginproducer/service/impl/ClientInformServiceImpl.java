package com.imall.loginproducer.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imall.loginproducer.dao.ClientInformMapper;
import com.imall.loginproducer.service.ClientInformService;
import com.imall.entities.users.ClientInform;
import org.springframework.stereotype.Service;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/8 16:10
 */
@Service
public class ClientInformServiceImpl extends ServiceImpl<ClientInformMapper, ClientInform> implements ClientInformService {

    @Override
    public ClientInform getInformationByPhone(String phone) {
        QueryWrapper<ClientInform> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .select("user_id","client_name","client_phone","client_address","client_email","user_name","client_gender")
                .eq("client_phone",phone);
        return getOne(queryWrapper);
    }

    @Override
    public String getUserIdByPhone(String phone) {
        QueryWrapper<ClientInform> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .select("user_id")
                .eq("client_phone",phone);
        return getOne(queryWrapper).getUserId();
    }

    @Override
    public String getUserIdByEmail(String email) {
        QueryWrapper<ClientInform> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .select("user_id")
                .eq("client_email",email);
        return getOne(queryWrapper).getUserId();
    }

    @Override
    public ClientInform getInformationByUserName(String userName) {
        QueryWrapper<ClientInform> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .select("user_id","client_name","client_phone","client_address","client_email","user_name","client_gender")
                .eq("user_name",userName);
        return getOne(queryWrapper);
    }

    @Override
    public Integer getClientIdByUserName(String userName) {
        QueryWrapper<ClientInform> queryWrapper=new QueryWrapper<>();
        queryWrapper
                .eq("user_name",userName);
        ClientInform clientInform= getOne(queryWrapper);
        return  clientInform.getId();
    }

}
