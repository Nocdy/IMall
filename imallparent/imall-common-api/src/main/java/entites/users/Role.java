package entites.users;

import com.baomidou.mybatisplus.annotation.IdType;
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

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String description;
}
