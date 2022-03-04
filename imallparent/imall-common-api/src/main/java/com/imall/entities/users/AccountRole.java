package com.imall.entities.users;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
/**
 * 用户与角色关系表（不为实体）
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/12 16:23
 */
@TableName("role_relationship")
@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRole {

    private String userId;

    private Integer roleId;


}
