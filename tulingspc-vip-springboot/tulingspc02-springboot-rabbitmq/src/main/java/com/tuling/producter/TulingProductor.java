package com.tuling.producter;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 生产者
 * Created by smlz on 2019/3/24.
 */
@Component
public class TulingProductor {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String msg) {

        Map<String,Object> sendContext = new HashMap<>();
        sendContext.put("name","张三");
        sendContext.put("sex","男");
        sendContext.put("msg",msg);
        rabbitTemplate.convertAndSend("tulingDirectExchange","tuling.directkey",sendContext);
    }

    /**
     * 发送到扇形交换机上
     * @param msg
     */
    public void sendMsg2Fanout(String msg) {

        rabbitTemplate.convertAndSend("tulingFanoutExchange","aaaabbdd",msg);

    }


    /**
     * 发送到扇形交换机上
     * @param msg
     */
    public void sendMsg2Topic(String msg) {
        rabbitTemplate.convertAndSend("tulingTopicExchange","topic.key.aaa",msg);
        rabbitTemplate.convertAndSend("tulingTopicExchange","aa.key",msg);

    }


}
