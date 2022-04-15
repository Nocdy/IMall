package com.imall.loginproducer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.imall.dto.Result;
import com.imall.emums.StatusCode;
import com.imall.entities.users.ClientInform;
import com.imall.entities.users.Vendor;
import com.imall.loginproducer.service.ClientInformService;
import com.imall.loginproducer.service.GetInfoService;
import com.imall.loginproducer.service.VendorService;
import com.imall.utils.CurrentUserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/23 10:53
 */
@Service
@RequiredArgsConstructor
public class GetInfoServiceImpl implements GetInfoService {


    private final CurrentUserUtils currentUserUtils;

    private final ClientInformService clientInformService;

    private final VendorService vendorService;

    @Override
    public Result<Object> getCurrentClientInfo() {
        JSONObject data=new JSONObject();
        ClientInform clientInform=clientInformService.getInformationByUserName(currentUserUtils.getCurrentUserName());
        data.put("Client",clientInform);
        return new Result<>(data, StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage());
    }

    @Override
    public Result<Object> getUserId() {
        JSONObject data=new JSONObject();
        String name=currentUserUtils.getCurrentUserName();
        Integer id=clientInformService.getClientIdByUserName(name);
        data.put("clientId",id);
        return new Result<>(data,StatusCode.SUCCESS.getCode(),
                StatusCode.SUCCESS.getMessage());
    }

    @Override
    public Result<Object> getVendorInfo(Integer vendorId) {
        JSONObject data=new JSONObject();
        Vendor vendor=vendorService.getById(vendorId);
        data.put("vendor",vendor);
        return new Result<>(data,StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage());
    }

}
