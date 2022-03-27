package com.imall.flashproducer.service;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/7 15:15
 */
public interface IMessage {

    /**
     * 发送消息服务
     * @param msg 发送消息
     * @param channel 发送的通道
     */
    void sendMessage(String msg,String channel);

}
