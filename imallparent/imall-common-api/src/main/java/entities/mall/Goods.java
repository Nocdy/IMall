package entities.mall;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/2/24 15:11
 */
@Repository
@Data
public class Goods {

    @TableId(type = IdType.AUTO,value = "goods_id")
    Integer id;

    @TableField("goods_name")
    String name;

    LocalDate sellDate;

    @TableField("goods_count")
    Integer count;

    @TableField("goods_description")
    String description;

    @TableField("goods_image")
    String image;

    Boolean isPlash;

    Integer plashCount;



}
