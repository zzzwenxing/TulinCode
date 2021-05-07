package com.it.tl.edu.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * StringTransactionalConsumer
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "${tl.rocketmq.transTopic}", consumerGroup = "myTxProducerGrouptl")
public class StringTransactionalConsumer implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        System.out.printf("------- StringTransactionalConsumer received: %s \n", message);
    }

}
