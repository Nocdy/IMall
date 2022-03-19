package com.imall.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/7 14:47
 */
public class UpdateMessage {

   private Integer vendorId;

   @JsonDeserialize(using = LocalDateTimeDeserializer.class)
   @JsonSerialize(using = LocalDateTimeSerializer.class)
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
   private LocalDateTime updateTime;

   private Integer goodsId;
}
