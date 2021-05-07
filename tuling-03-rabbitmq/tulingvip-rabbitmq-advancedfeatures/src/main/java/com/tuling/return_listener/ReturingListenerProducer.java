package com.tuling.return_listener;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.tuling.comfirm_listener.TulingConfirmListener;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * Created by smlz on 2019/9/29.
 */
public class ReturingListenerProducer {

    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.159.8");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("tuling");
        connectionFactory.setUsername("smlz");
        connectionFactory.setPassword("smlz");
        connectionFactory.setConnectionTimeout(100000);

        //创建一个连接
        Connection connection = connectionFactory.newConnection();

        //创建一个channel
        Channel channel = connection.createChannel();

        //准备发送消息
        String exchangeName = "tuling.retrun.direct";
        String okRoutingKey = "tuling.retrun.key.ok";
        String errorRoutingKey = "tuling.retrun.key.error";

        /**
         * 设置监听不可达消息
         */
        channel.addReturnListener(new TulingRetrunListener());

        channel.addConfirmListener(new TulingConfirmListener());


        //设置消息属性
        Map<String,Object> tulingInfo = new HashMap<>();
        tulingInfo.put("company","tuling");
        tulingInfo.put("location","长沙");

        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder()
                .deliveryMode(2)
                .correlationId(UUID.randomUUID().toString())
                .timestamp(new Date())
                .headers(tulingInfo)
                .build();

        String msgContext = "你好 图灵...."+System.currentTimeMillis();

        /**
         * 发送消息
         * mandatory:该属性设置为false,那么不可达消息就会被mq broker给删除掉
         *          :true,那么mq会调用我们得retrunListener 来告诉我们业务系统 说该消息
         *          不能成功发送.
         */
        //channel.basicPublish(exchangeName,okRoutingKey,true,basicProperties,msgContext.getBytes());


        String errorMsg1 = "你好 图灵 mandotory为false...."+System.currentTimeMillis();

        //错误发送   mandotory为false
        //channel.basicPublish(exchangeName+"--adfasdfasfasf----",okRoutingKey,true,basicProperties,errorMsg1.getBytes());

        String errorMsg2 = "你好 图灵 mandotory为true...."+System.currentTimeMillis();

        //错误发送 mandotory 为true
        channel.basicPublish(exchangeName,errorRoutingKey,true,basicProperties,errorMsg2.getBytes());

    }
}
