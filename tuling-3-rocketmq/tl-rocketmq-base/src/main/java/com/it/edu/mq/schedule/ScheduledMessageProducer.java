package com.it.edu.mq.schedule;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author ：图灵-杨过
 * @date：2019/9/29
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class ScheduledMessageProducer {
    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("ExampleConsumer");

        //;192.168.241.199:9876
        producer.setNamesrvAddr("192.168.241.198:9876;192.168.241.199:9876");

        producer.start();
        int totalMessagesToSend = 3;
        for (int i = 0; i < totalMessagesToSend; i++) {
            Message message = new Message("TestTopic", ("Hello scheduled message " + i).getBytes());
            //延时消费
            message.setDelayTimeLevel(6);
            // Send the message
            producer.send(message);
        }

        System.out.printf("message send is completed .%n");
        producer.shutdown();
    }
}
