package com.it.edu.mq.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author ：图灵-杨过
 * @date：2019/10/8
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class FilterConsumer {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("filter_sample_group");
        /**
         * 注册中心
         */
        consumer.setNamesrvAddr("192.168.241.198:9876;192.168.241.199:9876");
        /**
         * 订阅主题
         * 一种资源去换取另外一种资源
         */
        consumer.subscribe("TopicFilter", MessageSelector.bySql("a between 0 and 3 and b = 'yangguo'"));
        /**
         * 注册监听器，监听主题消息
         */
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs){
                    try {
                        System.out.println("consumeThread=" + Thread.currentThread().getName()
                                + ", queueId=" + msg.getQueueId() + ", content:"
                                + new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();

        System.out.printf("Filter Consumer Started.%n");
    }
}
