package com.tuling.quickstartonclass;


import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by smlz on 2019/10/25.
 */
public class QuickProducter {

    public static void main(String[] args) throws IOException, TimeoutException {

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

        for(int i=0;i<5;i++) {
            String message = "helo--"+i;
           channel.basicPublish("","tuling-queue-01",null,message.getBytes());
        }
        //关闭连接
        channel.close();
        connection.close();

    }
}
