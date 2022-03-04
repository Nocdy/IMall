package com.imall.entities.users;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/11 22:00
 */
@Data
@Repository
public class User {

    @TableId(value = "user_id",type = IdType.ASSIGN_ID)
    private String id;

    private String password;

    private String userName;


    @TableField(exist = false)
    private List<Role> accountRoles=new ArrayList<>();

    public List<SimpleGrantedAuthority> getRoles(){
        List<SimpleGrantedAuthority> authorities=new ArrayList<>();
        accountRoles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }


}
