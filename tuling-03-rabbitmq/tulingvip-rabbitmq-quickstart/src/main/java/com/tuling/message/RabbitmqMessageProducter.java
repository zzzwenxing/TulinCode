package com.tuling.message;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:rabbitmq消费端
* @author: smlz
* @createDate: 2019/9/28 13:23
* @version: 1.0
*/
public class RabbitmqMessageProducter {

    public static void main(String[] args) throws IOException, TimeoutException {
        //1:创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();

        //2设置连接工厂的属性
        connectionFactory.setHost("192.168.159.8");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("tuling");
        connectionFactory.setUsername("smlz");
        connectionFactory.setPassword("smlz");

        //3:通过连接工厂创建连接对象
        Connection connection = connectionFactory.newConnection();

        //4:通过连接创建channel
        Channel channel = connection.createChannel();

        Map<String,Object> headsMap = new HashMap<>();
        headsMap.put("company","tuling");
        headsMap.put("name","smlz");

        AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder()
                .deliveryMode(2)//2标识持久化消息  1标识 服务重启后 消息不会被持久化
                .expiration("10000")//消息过期 10s
                .contentEncoding("utf-8")
                .correlationId(UUID.randomUUID().toString())
                .headers(headsMap)
                .build();

        //5:通过channel发送消息
        for(int i=0;i<5;i++) {
            String message = "hello--"+i;
            /**
             * 老师以前讲过说我们的消息会发送的exchange上，
             * 但是在这里我们没有指定交换机?那我们的消息发送到哪里了？？？？
             * The default exchange is implicitly bound to every queue, with a routing key equal to the queue name.
             * It is not possible to explicitly bind to, or unbind from the default exchange. It also cannot be deleted.
             * 说明:加入我们消息发送的时候没有指定具体的交换机的话，那么就会发送到rabbimtq指定默认的交换机上，
             * 那么该交换机就会去根据routing_key 查找对应的queueName 然后发送的该队列上.
             *
             */
            channel.basicPublish("tuling.directchange","tuling.directchange.key",basicProperties,message.getBytes());
        }

        //6:关闭连接
        channel.close();
        connection.close();
    }
}
