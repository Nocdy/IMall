package com.imall.loginproducer.service.impl;

import com.imall.loginproducer.dto.LoginRequest;
import com.imall.loginproducer.dto.UserRegistry;
import com.imall.loginproducer.service.*;
import com.imall.loginproducer.utils.CurrentAccountUtils;
import dto.Result;
import emums.StatusCode;
import entites.users.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import utils.JwtTokenUtils;

import java.util.List;
import java.util.stream.Collectors;

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

    private final StringRedisTemplate stringRedisTemplate;

    private final CurrentAccountUtils currentUserUtils;

    @Override
    public Result<Object> registry(UserRegistry registryRequest) {
        String type=registryRequest.getType();
        Vendor vendor=new Vendor();
        User user =new User();
        ClientInform clientInform =new ClientInform();
        boolean status=false;
        Integer account,roleId;
        roleId=roleService.getIdByName(type);
        user.setPassword(registryRequest.getPassword());
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
            user.setAccount(account);
            user.setUserName(registryRequest.getShopName());
            user.setPassword(registryRequest.getPassword());
            loginAccountService.save(user);
            vendor.setUserId(user.getId());
            accountRoleService.save(new AccountRole(user.getId(),roleId));
            status=vendorService.updateById(new Vendor(account, user.getId()));
        }
        else if(USER.equals(type)){
            clientInform.setPhone(registryRequest.getPhone());
            clientInform.setUserName(registryRequest.getNickName());
            clientInform.setEmail(registryRequest.getEmail());
            clientInform.setAddress(registryRequest.getAddress());
            clientInform.setName(registryRequest.getName());
            account=userService.savaAndReturnId(clientInform);
            if(account==-1){
                return new Result<>(StatusCode.USER_REGISTRY_FAIL.getCode(),
                        StatusCode.USER_REGISTRY_FAIL.getMessage());
            }
            vendor.setId(account);
            user.setAccount(account);
            user.setUserName(registryRequest.getPhone());
            user.setPassword(registryRequest.getPassword());
            loginAccountService.save(user);
            clientInform.setUserId(user.getId());
            accountRoleService.save(new AccountRole(user.getId(),roleId));
            roleId=roleService.getIdByName(VISITOR);
            accountRoleService.save(new AccountRole(user.getId(),roleId));
            status=userService.updateById(new ClientInform(account, user.getId()));
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
    public Result<Object> login(LoginRequest loginRequest) {
        ClientInform clientInform;
        Vendor vendor;
        List<Role> roles;
        User user =new User();
        String type=loginRequest.getRole();
        Integer account=loginRequest.getId();
        String id;
        String phone=loginRequest.getPhone();
        if(USER.equals(type)) {
            if (account == null && phone != null) {
                id = userService.getuserIdByPhone(phone);
                user = loginAccountService.getById(id);
            }
            else{
                clientInform =userService.getById(account);
                user =loginAccountService.getById(clientInform.getUserId());
            }
        }
        else{

        }
        roles=roleService.getBaseMapper().selectBatchIds(
                accountRoleService.getRolesIdByuserId(user.getId())
                        .stream()
                        .map(AccountRole::getRoleId)
                        .collect(Collectors.toList()));
        user.setAccountRoles(roles);
        return null;
    }

    @Override
    public boolean logout() {
        return false;
    }

    public String createToken(User user, LoginRequest loginRequest){
        if(!loginAccountService.check(loginRequest.getPassword(), user.getPassword())){
            throw new BadCredentialsException("The user name or password is not correct.");
        }
        JwtUser jwtUser=new JwtUser(user);
        List<String> authorities = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String token = JwtTokenUtils.createToken(user.getUserName(), user.getId(), authorities, loginRequest.getRememberMe());
        stringRedisTemplate.opsForValue().set(user.getId(), token);
        return token;
    }

    public void removeToken(){
        JwtUser jwtUser=currentUserUtils.getUser();
    }


}
