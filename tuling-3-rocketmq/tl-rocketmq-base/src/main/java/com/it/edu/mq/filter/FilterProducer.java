package com.it.edu.mq.filter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author ：图灵-杨过
 * @date：2019/10/8
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class FilterProducer {


    /***
     * TAG-FILTER-1000 ---> 布隆过滤器
     * 过滤掉的那些消息。直接就跳过了么。下次就不会继续过滤这些了。是么。
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("filter_sample_group");
        producer.setNamesrvAddr("192.168.241.198:9876");
        producer.start();

        for (int i = 0; i < 3; i++) {
            Message msg = new Message("TopicFilter",
                    "TAG-FILTER",
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            msg.putUserProperty("a",String.valueOf(i));
            if(i % 2 == 0){
                msg.putUserProperty("b","yangguo");
            }else{
                msg.putUserProperty("b","xiaolong girl");
            }
            producer.send(msg);
        }

        producer.shutdown();
    }

}
