package com.tuling.compent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.tuling.bo.MsgTxtBo;
import com.tuling.constants.MqConst;
import com.tuling.entity.MessageContent;
import com.tuling.enumration.MsgStatusEnum;
import com.tuling.mapper.MsgContentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Date;

/**
 * Created by smlz on 2019/10/13.
 */
@Component
@Slf4j
public class MqConsumer {

    /**队列名称*/

    public static final String PRODUCT_TO_CALLBACK_QUEUE_NAME = "product_to_callback_queue";


    @Autowired
    private MsgContentMapper msgContentMapper;



    /**
     * 没有加分布式锁的版本,可能存在重复消费问题
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = {PRODUCT_TO_CALLBACK_QUEUE_NAME})
    @RabbitHandler
    public void consumerConfirmMsg(Message message, Channel channel) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        MsgTxtBo msgTxtBo = objectMapper.readValue(message.getBody(), MsgTxtBo.class);
        Long deliveryTag = (Long) message.getMessageProperties().getDeliveryTag();

        log.info("消费确认消息:{}",msgTxtBo);
        MessageContent messageContent = new MessageContent();
        messageContent.setMsgId(msgTxtBo.getMsgId());
        messageContent.setOrderNo(msgTxtBo.getOrderNo());
        messageContent.setProductNo(msgTxtBo.getProductNo());
        messageContent.setCreateTime(new Date());
        messageContent.setUpdateTime(new Date());
        messageContent.setMaxRetry(MqConst.MAX_RETRY_COUNT);
        messageContent.setExchange(message.getMessageProperties().getReceivedExchange());
        messageContent.setRoutingKey(message.getMessageProperties().getReceivedRoutingKey());
        messageContent.setMsgStatus(MsgStatusEnum.CONSUMER_SUCCESS.getCode());

        //插入消息
        msgContentMapper.saveMsgContent(messageContent);

        //签收消息
        channel.basicAck(deliveryTag,false);

    }

    @RabbitListener(queues = {MqConst.ORDER_TO_PRODUCT_DELAY_QUEUE_NAME})
    @RabbitHandler
    public void consumerCheckMsg(Message message, Channel channel) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MsgTxtBo msgTxtBo = objectMapper.readValue(message.getBody(), MsgTxtBo.class);
        System.out.println("==================>"+msgTxtBo.toString());
        Long deliveryTag = (Long) message.getMessageProperties().getDeliveryTag();

        //替换延时消息ID
        String msgId = msgTxtBo.getMsgId().replace("_delay","");

        MessageContent messageContent = msgContentMapper.qryMessageContentById(msgId);

        log.info("消费延时消息:{}",msgTxtBo.toString());

        //表示消息消费者没有发送确认消息,需要回调生产者重新发送消息
        if(messageContent == null) {
            //回调订单服务,从新发送消息
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForEntity("http://localhost:8080/retryMsg",msgTxtBo,String.class);
        }

        //签收消息
        channel.basicAck(deliveryTag,false);

    }
}


