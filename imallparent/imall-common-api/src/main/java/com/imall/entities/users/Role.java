package com.imall.entities.users;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Repository;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/11 21:57
 */
@Data
@Repository
public class Role {

    @TableId(value = "role_id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "role_name")
    private String name;

    @TableField(value = "role_description")
    private String description;
}
