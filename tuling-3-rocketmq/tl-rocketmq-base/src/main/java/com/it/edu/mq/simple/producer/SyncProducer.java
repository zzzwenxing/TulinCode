package com.it.edu.mq.simple.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @author ：图灵-杨过
 * @date：2019/9/24
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class SyncProducer {

    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("tl_msg_student_group");

        producer.setNamesrvAddr("192.168.241.198:9876");
        //producer.setSendMsgTimeout(10000);

        producer.start();
        /*for (int i = 0; i < 1; i++) {
            Message msg = new Message("TopicSync"  ,
                    "TagS"  ,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }*/
        Message msg = new Message("TopicStudent"  ,
                "TagStudent"  ,
                 "tag" ,
                ("Hello tuling RocketMQ ").getBytes(RemotingHelper.DEFAULT_CHARSET)
        );
        SendResult sendResult = producer.send(msg);
        System.out.printf("%s%n", sendResult);

        producer.shutdown();
    }
}
