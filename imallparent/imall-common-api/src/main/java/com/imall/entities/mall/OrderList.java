package com.imall.entities.mall;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/14 18:57
 */
@Data
@Repository
public class OrderList implements Serializable {

    @TableId
    Integer clientId;

    @JsonIgnore
    String shoppingList;

    @TableField(exist = false)
    List<Goods> goodsList;

}
