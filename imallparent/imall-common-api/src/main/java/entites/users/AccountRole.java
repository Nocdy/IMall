package entites.users;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/12 16:23
 */

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@TableName("role_relationship")
@Repository
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRole {

    private String loginId;

    private Integer roleId;


}
