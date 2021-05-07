package com.it.edu.mq.simple.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author ：图灵-杨过
 * @date：2019/9/24
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class OnewayProducer {

    public static void main(String[] args) throws Exception{

        DefaultMQProducer producer = new DefaultMQProducer("tl_message_group");
        // Specify name server addresses.
        producer.setNamesrvAddr("192.168.241.198:9876;192.168.241.199:9876");

        producer.setSendMsgTimeout(10000);

        producer.start();
        for (int i = 0; i < 1; i++) {
            Message msg = new Message("TopicTest" /* Topic */,
                    "TagSendOne" /* Tag */,
                    "OrderID198",
                    ("Hello RocketMQ test i " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            producer.sendOneway(msg);
        }
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }

}
