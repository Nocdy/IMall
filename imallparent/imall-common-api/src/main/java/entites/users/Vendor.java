package entites.users;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/7 20:50
 */
@Data
@Repository
@NoArgsConstructor
public class Vendor {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String contactNumber;

    private String contactName;

    private String shopName;

    private String loginId;

    public Vendor(Integer id,String loginId){
        this.id=id;
        this.loginId=loginId;
    }
}