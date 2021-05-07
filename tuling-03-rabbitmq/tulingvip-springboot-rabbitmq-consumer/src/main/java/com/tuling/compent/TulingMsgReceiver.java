package com.tuling.compent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.tuling.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 消息接受组件
 * Created by smlz on 2019/10/10.
 */
@Component
@Slf4j
public class TulingMsgReceiver {


    @RabbitListener(queues = {"tulingBootQueue"})
    public void consumerMsg(Message message, Channel channel) throws IOException {
        System.out.println(Thread.currentThread().getName() + " 接收到来自tulingBootQueue：");
        log.info("监听tulingBootQueue消费消息=======:{}",new String(message.getBody()));
        //手工签收
        Long deliveryTag = (Long) message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag,false);
    }


    /**
     * 消费延时消息
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = {"tulingBootDelayQueue"})
    public void consumerDelayMsg(Message message, Channel channel) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.readValue(message.getBody(),Order.class);
        log.info("在{},签收:{}",sdf.format(new Date()),order);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }


    /**
     * todo:   强烈不推荐这种用法 我们再开发中需要把队列 交换机  绑定配置到我们专门的配置类中
     * @param message
     * @param channel
     */
    @RabbitListener(bindings =
         @QueueBinding(
                 value = @Queue(
                         name = "tulingBootQueue2",
                         durable = "true",
                         autoDelete = "false",
                         exclusive = "false"
                 ),
                 exchange = @Exchange(
                         name = "springboot.direct.exchange",
                         type = "direct",
                         durable = "true",
                         autoDelete = "fasle"),
                 key = {"springboot.key2"}
         )
    )

    public void consumerMsg2(Message message,Channel channel) throws IOException {
        System.out.println(Thread.currentThread().getName() + " 接收到来自tulingBootQueue：");
        log.info("监听tulingBootQueue2消费消息=======:{}",new String(message.getBody()));
        //手工签收
        Long deliveryTag = (Long) message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag,false);
    }




    /**
     * 接受客户端发送的core包下的message
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitListener(queues = {"tulingBootQueue3"})
    public void consumerOrder(Message message,Channel channel) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.readValue(message.getBody(),Order.class);
        log.info("监听tulingBootQueue3消费消息:{}",order.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(queues = {"tulingClusterQueue"})
    public void tulingClusterQueue(Message message,Channel channel) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.readValue(message.getBody(),Order.class);
        log.info("监听tulingBootQueue3消费消息:{}",order.toString());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
