package entites.users;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/11 22:00
 */
@Data
@Repository
@TableName("login")
public class LoginAccount {

    @TableField("id")
    private Integer account;

    @TableId("login_id")
    private String id;

    private String password;

    private String userName;

    @TableField(exist = false)
    private List<Role> roles;

}
