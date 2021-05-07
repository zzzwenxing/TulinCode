package com.it.edu.mq.batch;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：图灵-杨过
 * @date：2019/9/29
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class BatchProducer {

    public static void main(String[] args) throws Exception {
        /**
         * rocketMq 支持消息批量发送
         * 同一批次的消息应具有：相同的主题，相同的waitStoreMsgOK，并且不支持定时任务。
         * <strong> 同一批次消息建议大小不超过~1M </strong>,消息最大不能超过4M,需要
         * 对msg进行拆分
         */
        DefaultMQProducer producer = new DefaultMQProducer("batch_group");
        producer.setNamesrvAddr("192.168.241.198:9876");
        producer.start();

        String topic = "BatchTest";
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(topic, "TagA", "OrderID001", "Hello world 0".getBytes()));
        messages.add(new Message(topic, "TagA", "OrderID002", "Hello world 1".getBytes()));
        messages.add(new Message(topic, "TagA", "OrderID003", "Hello world 2".getBytes()));

        ListSplitter splitter = new ListSplitter(messages);

        /**
         * 对批量消息进行拆分
         */
        while (splitter.hasNext()) {
            try {
                List<Message>  listItem = splitter.next();
                producer.send(listItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        producer.shutdown();

    }

}
