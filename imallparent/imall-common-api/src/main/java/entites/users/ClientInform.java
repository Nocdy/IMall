package entites.users;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/7 20:44
 */
@Data
@Repository
@NoArgsConstructor
public class ClientInform {

    @TableId(value = "client_id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "client_name")
    private String name;

    @TableField(value = "client_gender")
    private String gender;

    @TableField(value = "client_address")
    private String address;

    @TableField(value = "client_email")
    private String email;

    @TableField(value = "client_phone")
    private String phone;

    private String userName;

    private String userId;

    public ClientInform(Integer id, String userId){
        this.id=id;
        this.userId=userId;
    }


}
