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
 * @Date 2022/2/7 20:50
 */
@Data
@Repository
@NoArgsConstructor
public class Vendor {

    @TableId(value = "vendor_id",type = IdType.AUTO)
    private Integer id;

    @TableField(value = "vendor_contact_number")
    private String contactNumber;

    @TableField(value = "vendor_contact_name")
    private String contactName;

    @TableField(value = "vendor_name")
    private String shopName;

    private String userId;

    private String userName;

    public Vendor(Integer id,String userId){
        this.id=id;
        this.userId=userId;
    }
}