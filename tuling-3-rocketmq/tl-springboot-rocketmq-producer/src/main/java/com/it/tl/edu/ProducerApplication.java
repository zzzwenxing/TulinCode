package com.it.tl.edu;

import com.it.tl.edu.dto.OrderPaymentDto;
import com.it.tl.edu.trans.TransactionListenerImpl;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：图灵-杨过
 * @date：2019/11/4
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
@SpringBootApplication
public class ProducerApplication implements CommandLineRunner {
    private static final String TX_PGROUP_NAME = "myTxProducerGroup";

    @Resource
    private RocketMQTemplate rocketMQTemplate;
    @Value("${tl.rocketmq.transTopic}")
    private String springTransTopic;
    @Value("${tl.rocketmq.topic}")
    private String springTopic;
    @Value("${tl.rocketmq.orderTopic}")
    private String orderPaymentTopic;
    @Value("${tl.rocketmq.msgExtTopic}")
    private String msgExtTopic;
    @Resource(name = "extRocketMQTemplate")
    private RocketMQTemplate extRocketMQTemplate;

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 发送字符串
        /*SendResult sendResult = rocketMQTemplate.syncSend(springTopic, "Hello, World!");
        System.out.printf("syncSend1 to topic %s sendResult=%s %n", springTopic, sendResult);
        */
        // 使用自定义的templete
        /*sendResult = extRocketMQTemplate.syncSend(springTopic, "自定义的templete发送");
        System.out.printf("extRocketMQTemplate.syncSend1 to topic %s sendResult=%s %n", springTopic, sendResult);
        */
        // 发送 spring Message,本质也是发送字符串
        /*sendResult = rocketMQTemplate.syncSend(springTopic, MessageBuilder.withPayload("tl rocketmq msg test").build());
        System.out.printf("syncSend2 to topic %s sendResult=%s %n", springTopic, sendResult);
        */
        // 发送自定义对象-Dto
        /*rocketMQTemplate.asyncSend(orderPaymentTopic, new OrderPaymentDto(10102303, new BigDecimal("88.00")), new SendCallback() {
            public void onSuccess(SendResult var1) {
                System.out.printf("async onSucess SendResult=%s %n", var1);
            }

            public void onException(Throwable var1) {
                System.out.printf("async onException Throwable=%s %n", var1);
            }

        });*/

        // 发送指定Tag的msg
        /*rocketMQTemplate.convertAndSend(msgExtTopic + ":tag0", "I'm from tag0");
        System.out.printf("syncSend topic %s tag %s %n", msgExtTopic, "tag0");
        rocketMQTemplate.convertAndSend(msgExtTopic + ":tag1", "I'm from tag1");
        System.out.printf("syncSend topic %s tag %s %n", msgExtTopic, "tag1");*/


        // 批量发送字符消息
        //testBatchMessages();

        // 发送事务消息
        testTransaction();
    }

    /**
     * 批量消息
     */
    private void testBatchMessages() {
        List<Message> msgs = new ArrayList<Message>();
        for (int i = 0; i < 10; i++) {
            msgs.add(MessageBuilder.withPayload("Hello RocketMQ Batch Msg#" + i).
                    setHeader(RocketMQHeaders.KEYS, "KEY_" + i).build());
        }
        SendResult sr = rocketMQTemplate.syncSend(springTopic, msgs, 60000);

        System.out.printf("--- Batch messages send result :" + sr);
    }

    /**
     * 发送事务消息
     * @throws MessagingException
     */
    private void testTransaction() throws MessagingException {
        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            try {

                Message msg = MessageBuilder.withPayload("Hello RocketMQ " + i).
                        setHeader(RocketMQHeaders.KEYS, "KEY_" + i).build();
                /**
                 * TX_PGROUP_NAME 必须同 {@link TransactionListenerImpl} 类的注解 txProducerGroup
                 * @RocketMQTransactionListener(txProducerGroup = "myTxProducerGroup")
                 */
                SendResult sendResult = rocketMQTemplate.sendMessageInTransaction(TX_PGROUP_NAME,
                        springTransTopic + ":" + tags[i % tags.length], msg, null);
                System.out.printf("------ send Transactional msg body = %s , sendResult=%s %n",
                        msg.getPayload(), sendResult.getSendStatus());

                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }



}
