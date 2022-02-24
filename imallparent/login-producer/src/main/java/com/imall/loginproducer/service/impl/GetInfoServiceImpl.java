package com.imall.loginproducer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.imall.loginproducer.service.ClientInformService;
import com.imall.loginproducer.service.GetInfoService;
import com.imall.loginproducer.utils.CurrentUserUtils;
import dto.Result;
import emums.StatusCode;
import entites.users.ClientInform;
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

    @Override
    public Result<Object> getCurrentClientInfo() {
        JSONObject data=new JSONObject();
        ClientInform clientInform=clientInformService.getInformationByUserName(currentUserUtils.getCurrentUserName());
        data.put("Client",clientInform);
        return new Result<>(data, StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage());
    }
}
