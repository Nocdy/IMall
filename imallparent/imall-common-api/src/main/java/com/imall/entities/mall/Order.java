package com.imall.entities.mall;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/19 18:26
 */
@Data
@Repository
@TableName("order_save")
public class Order {

    public void setOrder(Order order){
        this.goodsList=order.getGoodsList();
        this.goodsIdList=order.getGoodsIdList();
        this.clientId=order.getClientId();
        this.shoppingList=order.getShoppingList();
        this.date=order.getDate();
        this.id=order.getId();
        this.isFinish=order.getIsFinish();
    }

    public void convertListToJson(){
        List<Integer> shortList=new ArrayList<>();
        this.goodsList.forEach(goods -> shortList.add(goods.getId()));
        this.shoppingList= JSON.toJSONString(shortList);
    }


    @TableId(value = "order_id",type = IdType.ASSIGN_UUID)
    private String id;

    @TableField("client_id")
    private Integer clientId;

    @JsonIgnore
    @TableField("shopping_list")
    private String shoppingList;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @TableField("date")
    private LocalDateTime date;

    @TableField("is_finish")
    private Boolean isFinish;

    @TableField(exist = false)
    private List<Integer> goodsIdList;

    @TableField(exist = false)
    private List<Goods> goodsList;


}
