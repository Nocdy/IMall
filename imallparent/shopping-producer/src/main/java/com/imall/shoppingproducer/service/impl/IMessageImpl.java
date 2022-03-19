package com.imall.shoppingproducer.service.impl;

import com.imall.dto.MyCorrelationData;
import com.imall.shoppingproducer.service.IMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Nocdy
 * @Description TODO
 * @Date 2022/3/7 15:16
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class IMessageImpl implements IMessage {

    private final StreamBridge streamBridge;

    @Override
    public void sendMessage(String msg, String channel) {
        MyCorrelationData correlation = new MyCorrelationData(UUID.randomUUID().toString(), msg);
        Message<String> message = MessageBuilder
                .withPayload(msg)
                .setHeader(AmqpHeaders.PUBLISH_CONFIRM_CORRELATION, correlation)
                .build();
        log.info("向{}发送消息{}", channel, message);
        streamBridge.send(channel, message);
        try {
            CorrelationData.Confirm confirm = correlation.getFuture().get(10, TimeUnit.SECONDS);
            log.info(confirm + " for " + correlation.getPayload());
            if (correlation.getReturned() != null) {
                log.error("消息: " + correlation.getPayload() + " 被返回，发送失败");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        } catch (ExecutionException | TimeoutException e) {
            throw new IllegalStateException(e);
        }
    }
}
