package com.imall.loginproducer.service.impl;

import com.imall.loginproducer.dto.UserRegistry;
import com.imall.loginproducer.service.*;
import dto.Result;
import emums.StatusCode;
import entites.users.AccountRole;
import entites.users.LoginAccount;
import entites.users.User;
import entites.users.Vendor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static constants.Constant.*;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/8 14:45
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserService userService;

    private final VendorService vendorService;

    private final LoginAccountService loginAccountService;

    private final RoleService roleService;

    private final AccountRoleService accountRoleService;

    @Override
    public Result<Object> registry(UserRegistry registryRequest) {
        String type=registryRequest.getType();
        Vendor vendor=new Vendor();
        LoginAccount loginAccount=new LoginAccount();
        User user=new User();
        boolean status=false;
        Integer account,roleId;
        roleId=roleService.getIdByName(type);
        loginAccount.setPassword(registryRequest.getPassword());
        if(VENDOR.equals(type)){
            vendor.setContactNumber(registryRequest.getContactNumber());
            vendor.setShopName(registryRequest.getShopName());
            vendor.setContactName(registryRequest.getContactName());
            account=vendorService.saveAndReturnId(vendor);
            if(account==-1){
                return new Result<>(StatusCode.VENDOR_REGISTRY_FAIL.getCode(),
                        StatusCode.VENDOR_REGISTRY_FAIL.getMessage());
            }
            vendor.setId(account);
            loginAccount.setAccount(account);
            loginAccount.setPassword(registryRequest.getPassword());
            loginAccount.setId(getLoginId());
            loginAccountService.save(loginAccount);
            vendor.setLoginId(loginAccount.getId());
            accountRoleService.save(new AccountRole(loginAccount.getId(),roleId));
            status=vendorService.updateById(new Vendor(account,loginAccount.getId()));
        }
        else if(USER.equals(type)){
            user.setPhone(registryRequest.getPhone());
            user.setNickName(registryRequest.getNickName());
            user.setEmail(registryRequest.getEmail());
            user.setAddress(registryRequest.getAddress());
            user.setName(registryRequest.getName());
            account=userService.savaAndReturnId(user);
            if(account==-1){
                return new Result<>(StatusCode.USER_REGISTRY_FAIL.getCode(),
                        StatusCode.USER_REGISTRY_FAIL.getMessage());
            }
            vendor.setId(account);
            loginAccount.setAccount(account);
            loginAccount.setUserName(registryRequest.getPhone());
            loginAccount.setPassword(registryRequest.getPassword());
            loginAccount.setId(getLoginId());
            loginAccountService.save(loginAccount);
            user.setLoginId(loginAccount.getId());
            accountRoleService.save(new AccountRole(loginAccount.getId(),roleId));
            roleId=roleService.getIdByName(VISITOR);
            accountRoleService.save(new AccountRole(loginAccount.getId(),roleId));
            status=userService.updateById(new User(account,loginAccount.getId()));
        }
        if(status){
            return new Result<>(StatusCode.REGISTRY_SUCCESS.getCode(),
                    StatusCode.REGISTRY_SUCCESS.getMessage());
        }
        else{
            return new Result<>(StatusCode.ERROR.getCode(),
                    StatusCode.ERROR.getMessage());
        }
    }

    @Override
    public Result<Object> login() {
        return null;
    }

    @Override
    public boolean logout() {
        return false;
    }


    public static String getLoginId(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}
