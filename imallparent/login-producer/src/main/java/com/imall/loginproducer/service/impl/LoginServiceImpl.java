package com.imall.loginproducer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.imall.entities.users.*;
import com.imall.loginproducer.service.*;
import com.imall.utils.CurrentUserUtils;
import com.imall.dto.LoginRequest;
import com.imall.dto.Result;
import com.imall.dto.UserRegistry;
import com.imall.emums.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.imall.utils.JwtTokenUtils;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.imall.constants.Constant.*;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/8 14:45
 */
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final ClientInformService clientInformService;

    private final VendorService vendorService;

    private final UserService userService;

    private final RoleService roleService;

    private final AccountRoleService accountRoleService;

    private final StringRedisTemplate stringRedisTemplate;

    private final CurrentUserUtils currentUserUtils;

    private static final ConcurrentHashMap<String, Supplier<Result<Object>>> REGISTRY_FAIL = new ConcurrentHashMap<>();

    private static final char EMAIL_REGEX='@';

    static {
        REGISTRY_FAIL.put(VENDOR, () -> new Result<>(StatusCode.VENDOR_REGISTRY_FAIL.getCode(),
                StatusCode.VENDOR_REGISTRY_FAIL.getMessage()));
        REGISTRY_FAIL.put(CLIENT, () -> new Result<>(StatusCode.USER_REGISTRY_FAIL.getCode(),
                StatusCode.USER_REGISTRY_FAIL.getMessage()));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Result<Object> registry(UserRegistry registryRequest) {
        String type = registryRequest.getType();
        Vendor vendor = new Vendor();
        User user = new User();
        ClientInform clientInform = new ClientInform();
        boolean status = false;
        Integer roleId=roleService.getIdByName(type);
        user.setPassword(registryRequest.getPassword());
        user.setUserName(registryRequest.getNickName());
        if(!userService.save(user)){
            return REGISTRY_FAIL.get(type).get();
        }
        else{
            if (VENDOR.equals(type)) {
                vendor.setUserName(registryRequest.getNickName());
                vendor.setUserId(user.getId());
                accountRoleService.save(new AccountRole(user.getId(), roleId));
                status = vendorService.save(vendor);
            } else if (CLIENT.equals(type)) {
                clientInform.setPhone(registryRequest.getPhone());
                clientInform.setUserName(registryRequest.getNickName());
                clientInform.setName(registryRequest.getName());
                clientInform.setUserId(user.getId());
                accountRoleService.save(new AccountRole(user.getId(), roleId));
                roleId = roleService.getIdByName(VISITOR);
                accountRoleService.save(new AccountRole(user.getId(), roleId));
                status = clientInformService.save(clientInform);
            }
        }
        if (status) {
            return new Result<>(StatusCode.REGISTRY_SUCCESS.getCode(),
                    StatusCode.REGISTRY_SUCCESS.getMessage());
        } else {
            return new Result<>(StatusCode.ERROR.getCode(),
                    StatusCode.ERROR.getMessage());
        }
    }

    @Override
    public Result<Object> login(LoginRequest loginRequest) {
        User user;
        List<Role> roles;
        String id;
        JSONObject data=new JSONObject();
        String loginAccount=loginRequest.getLoginAccount();
        user=userService.getByUserName(loginAccount);
        if(user==null){
            if(loginAccount.indexOf(EMAIL_REGEX)==-1){
                id=clientInformService.getUserIdByPhone(loginAccount);
            }
            else{
               id=clientInformService.getUserIdByEmail(loginAccount);
            }
            user=userService.getById(id);
        }
        if(user!=null&&userService.check(loginRequest.getPassword(),user.getPassword())) {
            roles = roleService.getBaseMapper().selectBatchIds(
                    accountRoleService.getRolesIdByUserId(user.getId())
                            .stream()
                            .map(AccountRole::getRoleId)
                            .collect(Collectors.toList()));
            user.setAccountRoles(roles);
            try {
                data.put("token", createToken(user, loginRequest));
                return new Result<>(data,
                        StatusCode.LOGIN_SUCCESS.getCode(),
                        StatusCode.LOGIN_SUCCESS.getMessage());
            } catch (BadCredentialsException badCredentialsException) {
                return new Result<>(StatusCode.LOGIN_FAIL.getCode(),
                        StatusCode.LOGIN_FAIL.getMessage());
            }
        }
        else{
            return new Result<>(StatusCode.LOGIN_FAIL.getCode(),
                    StatusCode.LOGIN_FAIL.getMessage());
        }


    }

    @Override
    public Result<Object> logout() {
        if(removeToken()){
            return new Result<>(StatusCode.SUCCESS.getCode(),
                    StatusCode.SUCCESS.getMessage());
        }
        else{
            return new Result<>(StatusCode.ERROR.getCode(),
                    StatusCode.ERROR.getMessage());
        }
    }



    private String createToken(User user, LoginRequest loginRequest) {
        if (!userService.check(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("密码错误");
        }
        JwtUser jwtUser = new JwtUser(user);
        List<String> authorities = jwtUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String token = JwtTokenUtils.createToken(user.getUserName(), user.getId(), authorities, loginRequest.getRememberMe());
        stringRedisTemplate.opsForValue().set(user.getId(), token,REDIS_EXPIRE_EIGHT_DAYS, TimeUnit.SECONDS);
        return token;
    }

    private Boolean removeToken() {
        String id=userService.getByUserName(currentUserUtils.getCurrentUserName()).getId();
        return stringRedisTemplate.delete(id);
    }


}
