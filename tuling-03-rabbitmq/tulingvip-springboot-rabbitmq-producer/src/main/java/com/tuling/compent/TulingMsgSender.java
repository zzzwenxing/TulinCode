package com.tuling.compent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuling.entity.Order;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 消息发送组件
 * Created by smlz on 2019/10/9.
 */
@Component
public class TulingMsgSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 测试发送我们的消息
     * @param msg 消息内容
     * @param msgProp 消息属性
     */
    public void sendMsg(String msg,Map<String,Object> msgProp) {


        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().putAll(msgProp);

        //构建消息对象
        Message message = new Message(msg.getBytes(),messageProperties);


        //构建correlationData 用于做可靠性投递得,ID:必须为全局唯一的 根据业务规则
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //开启确认模式
        rabbitTemplate.setConfirmCallback(new TulingConfirmCallBack());

        //开启消息可达监听
        rabbitTemplate.setReturnCallback(new TulingRetrunCallBack());

        //错误的交换机
        //rabbitTemplate.convertAndSend("springboot.direct.exchange.asdfasdfasdf","springboot.key",message,correlationData);
        //错误的队列
        rabbitTemplate.convertAndSend("springboot.direct.exchangeadfasdfsadfsadf","springboot.key",message,correlationData);


        //rabbitTemplate.convertAndSend("springboot.direct.exchange","springboot.key",message,correlationData);
    }

    public void sendOrderMsg(Order order) throws Exception {

        //构建correlationData 用于做可靠性投递得,ID:必须为全局唯一的 根据业务规则
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //开启确认模式
        rabbitTemplate.setConfirmCallback(new TulingConfirmCallBack());

        //开启消息可达监听
        rabbitTemplate.setReturnCallback(new TulingRetrunCallBack());


        /**
         * 使用org.springframework.amqp.core.Message 包装对象发送
         */
        ObjectMapper objectMapper = new ObjectMapper();
        String orderJson = objectMapper.writeValueAsString(order);
        MessageProperties messageProperties = new MessageProperties();
        Message message = new Message(orderJson.getBytes(),messageProperties);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.convertAndSend("springboot.direct.exchange","springboot.key3",message,correlationData);


        //直接发送对象
        rabbitTemplate.convertAndSend("springboot.direct.exchange","springboot.key2",order,correlationData);
    }


    public void sendDelayMessage(Order order) {
        //构建correlationData 用于做可靠性投递得,ID:必须为全局唯一的 根据业务规则
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //开启确认模式
        rabbitTemplate.setConfirmCallback(new TulingConfirmCallBack());

        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        rabbitTemplate.convertAndSend("delayExchange", "springboot.delay.key", order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", 10000);//设置延迟时间
                return message;
            }
        }, correlationData);

        order.setCreateDt(new Date());
        rabbitTemplate.convertAndSend("delayExchange", "springboot.delay.key", order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", 5000);//设置延迟时间
                return message;
            }
        }, correlationData);

    }

    public void sendDelayMessage2(Order order) {
        //构建correlationData 用于做可靠性投递得,ID:必须为全局唯一的 根据业务规则
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //开启确认模式
        rabbitTemplate.setConfirmCallback(new TulingConfirmCallBack());

        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());

        rabbitTemplate.convertAndSend("delayExchange", "springboot.delay.key", order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", 5000);//设置延迟时间
                return message;
            }
        }, correlationData);

    }


    public void sendMsgToCluster(String msg,Map<String,Object> msgProp) {


        MessageProperties messageProperties = new MessageProperties();
        messageProperties.getHeaders().putAll(msgProp);

        //构建消息对象
        Message message = new Message(msg.getBytes(),messageProperties);


        //构建correlationData 用于做可靠性投递得,ID:必须为全局唯一的 根据业务规则
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //开启确认模式
        rabbitTemplate.setConfirmCallback(new TulingConfirmCallBack());

        //开启消息可达监听
        rabbitTemplate.setReturnCallback(new TulingRetrunCallBack());

        rabbitTemplate.convertAndSend("springboot.direct.exchange","rabbitmq.cluster.key",message,correlationData);
    }


}
