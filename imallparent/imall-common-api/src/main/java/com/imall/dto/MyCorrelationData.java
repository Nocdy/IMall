package com.imall.dto;


import org.springframework.amqp.rabbit.connection.CorrelationData;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/15 10:56
 */
public class MyCorrelationData extends CorrelationData {

    private final String payload;

    public MyCorrelationData(String id, String payload) {
        super(id);
        this.payload = payload;
    }

    public String getPayload() {
        return this.payload;
    }

}
