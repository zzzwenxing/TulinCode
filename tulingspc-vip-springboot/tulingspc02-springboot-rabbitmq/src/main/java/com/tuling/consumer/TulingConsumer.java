package com.tuling.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


/**
 * Created by smlz on 2019/3/24.
 */
@Component
public class TulingConsumer {

    @RabbitListener(queues ="tulingDirectQueue")
    public void consumerMsg(Message message) {

        System.out.println("消费消息:"+message.getPayload().toString());
    }

    @RabbitListener(queues ="tulingFanoutQueue1")
    public void consumerFanoutMsg(Message message) {
        System.out.println("消费消息:"+message.getPayload().toString());
    }

    @RabbitListener(queues ="tulingFanoutQueue2")
    public void consumerFanoutMsg2(Message message) {
        System.out.println("消费消息:"+message.getPayload());
    }

    @RabbitListener(queues ="tulingTopicQueue")
    public void consumerTopicMsg(Message message) {
        System.out.println("消费消息:"+message.getPayload());
    }

    @RabbitListener(queues ="tulingTopicQueue2")
    public void consumerTopicMsg2(Message message) {
        System.out.println("消费消息:"+message.getPayload());
    }

}
