package com.tuling.quickstartonclass;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by smlz on 2019/10/25.
 */
public class QuickConsumer {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        //创建一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.159.8");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("tuling");
        connectionFactory.setUsername("smlz");
        connectionFactory.setPassword("smlz");
        connectionFactory.setConnectionTimeout(100000);



        //创建连接
        Connection connection = connectionFactory.newConnection();

        //创建我们的channle
        Channel channel = connection.createChannel();

        //声明队列名称
        String  queueName = "tuling-queue-01";

        channel.queueDeclare(queueName,true,false,false,null);

        //创建我们的消费者
        QueueingConsumer queueingConsumer =new QueueingConsumer(channel);

        channel.basicConsume(queueName,true,queueingConsumer);

        while (true) {
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery();
            System.out.println("消费消息:"+new String(delivery.getBody()));

        }


    }
}
