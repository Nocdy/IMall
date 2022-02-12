package entites.users;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String address;

    private String email;

    private String phone;

    private String nickName;

    private String loginId;

    public User(Integer id,String loginId){
        this.id=id;
        this.loginId=loginId;
    }


}
