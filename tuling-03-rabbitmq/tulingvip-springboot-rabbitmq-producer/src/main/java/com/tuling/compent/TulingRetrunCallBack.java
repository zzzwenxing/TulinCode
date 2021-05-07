package com.tuling.compent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuling.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import sun.rmi.runtime.Log;

/**
 * 消息不可达监听
 * Created by smlz on 2019/10/9.
 */
@Slf4j
public class TulingRetrunCallBack implements RabbitTemplate.ReturnCallback {
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        ObjectMapper objectMapper = new ObjectMapper();
        log.warn("correlationId:{}",message.getMessageProperties().getCorrelationId());
        log.warn("replyText:{}",replyText);
        log.warn("replyCode:{}",replyCode);
        log.warn("交换机:{}",exchange);
        log.warn("routingKey:{}",routingKey);
        log.info("需要更新数据库日志表得消息记录为不可达");
    }
}
