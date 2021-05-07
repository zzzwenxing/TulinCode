package com.tuling.rabbitmqwithspring;

import com.tuling.conveter.TulingImageConverter;
import com.tuling.conveter.TulingWordConverter;
import com.tuling.messagedelegate.TulingMsgDelegate;
import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.ConsumerTagStrategy;
import org.springframework.amqp.support.converter.ContentTypeDelegatingMessageConverter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * spring整合rabbitmq的整合版本
 * Created by smlz on 2019/10/4.
 */
@Configuration
public class RabbitmqConfig {


    /**
     * 创建连接工厂
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory () {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setAddresses("192.168.159.8:5672");
        cachingConnectionFactory.setVirtualHost("tuling");
        cachingConnectionFactory.setUsername("smlz");
        cachingConnectionFactory.setPassword("smlz");
        cachingConnectionFactory.setConnectionTimeout(10000);
        cachingConnectionFactory.setCloseTimeout(10000);
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        //spring容器启动加载该类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }

    //=====================================申明三个交换机====================================================================
    @Bean
    public TopicExchange topicExchange() {
        TopicExchange topicExchange = new TopicExchange("tuling.topic.exchange",true,false);
        return topicExchange;
    }

    @Bean
    public DirectExchange directExchange() {
        DirectExchange directExchange = new DirectExchange("tuling.direct.exchange",true,false);
        return directExchange;
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        FanoutExchange fanoutExchange = new FanoutExchange("tuling.faout.exchange",true,false);
        return fanoutExchange;
    }

    //===========================================申明队列===========================================================
    @Bean
    public Queue testTopicQueue() {
        Queue queue = new Queue("testTopicQueue",true,false,false,null);
        return queue;
    }

    @Bean
    public Queue testTopicQueue2() {
        Queue queue = new Queue("testTopicQueue2",true,false,false,null);
        return queue;
    }

    @Bean
    public Queue testDirectQueue() {
        Queue queue = new Queue("testDirectQueue",true,false,false,null);
        return queue;
    }

    @Bean
    public Queue testFaoutQueue() {
        Queue queue = new Queue("testfaoutQueue",true,false,false,null);
        return queue;
    }

    @Bean
    public Queue orderQueue() {
        Queue queue = new Queue("orderQueue",true,false,false,null);
        return queue;
    }

    @Bean
    public Queue addressQueue() {
        Queue queue = new Queue("addressQueue",true,false,false,null);
        return queue;
    }

    @Bean
    public Queue fileQueue() {
        Queue queue = new Queue("fileQueue",true,false,false,null);
        return queue;
    }



    //========================================申明绑定==============================================================
    @Bean
    public Binding topicBingding() {
        return BindingBuilder.bind(testTopicQueue()).to(topicExchange()).with("topic.#");
    }

    @Bean
    public Binding topicBingding2() {
        return BindingBuilder.bind(testTopicQueue2()).to(topicExchange()).with("topic.key.#");
    }

    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(testDirectQueue()).to(directExchange()).with("direct.key");
    }

    @Bean
    public Binding orderQueueBinding() {
        return BindingBuilder.bind(orderQueue()).to(directExchange()).with("rabbitmq.order");
    }

    @Bean
    public Binding addressQueueBinding() {
        return BindingBuilder.bind(addressQueue()).to(directExchange()).with("rabbitmq.address");
    }

    @Bean
    public Binding fileQueueBinding() {
        return BindingBuilder.bind(fileQueue()).to(directExchange()).with("rabbitmq.file");
    }

    @Bean
    public Binding fanoutBinding() {
        return BindingBuilder.bind(testFaoutQueue()).to(fanoutExchange());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setReceiveTimeout(50000);
        return rabbitTemplate;
    }

    /**
     * 简单的消息监听容器
     * @return
     */
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory());
        //监听我们的队列
        simpleMessageListenerContainer.setQueues(testTopicQueue(),testDirectQueue(),testTopicQueue2(),orderQueue(),addressQueue(),fileQueue());
        //消费者的数量
        simpleMessageListenerContainer.setConcurrentConsumers(5);
        //最大消费者数量
        simpleMessageListenerContainer.setMaxConcurrentConsumers(10);
        //签收模式
        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO);
        //设置拒绝重回队列
        simpleMessageListenerContainer.setDefaultRequeueRejected(false);

        /**
         * 设置使用默认的监听方法
         */
/*        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new TulingMsgDelegate());

        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);*/


        /**
         * 指定消费方法
         */
/*        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new TulingMsgDelegate());

        messageListenerAdapter.setDefaultListenerMethod("consumerMsg");

        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);*/



/*        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new TulingMsgDelegate());

        Map<String,String> queueMaps = new HashMap<>();
        queueMaps.put("testTopicQueue","consumerTopicQueue");
        queueMaps.put("testTopicQueue2","consumerTopicQueue2");
        messageListenerAdapter.setQueueOrTagToMethodName(queueMaps);

        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);*/

        /**
         * 处理Json的
         */
/*        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new TulingMsgDelegate());
        messageListenerAdapter.setDefaultListenerMethod("consumerJsonMessage");
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        messageListenerAdapter.setMessageConverter(jackson2JsonMessageConverter);
        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);*/


        /**
         * 处理java对象
         */
/*        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new TulingMsgDelegate());
        messageListenerAdapter.setDefaultListenerMethod("consumerJavaObjMessage");
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();

        DefaultJackson2JavaTypeMapper javaTypeMapper = new DefaultJackson2JavaTypeMapper();
        javaTypeMapper.setTrustedPackages("com.tuling.entity");
        //设置java转json的
        jackson2JsonMessageConverter.setJavaTypeMapper(javaTypeMapper);

        messageListenerAdapter.setMessageConverter(jackson2JsonMessageConverter);

        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);*/


        //处理文件 和图片
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(new TulingMsgDelegate());
        messageListenerAdapter.setDefaultListenerMethod("consumerFileMessage");

        //设置转换器
        ContentTypeDelegatingMessageConverter messageConverter = new ContentTypeDelegatingMessageConverter();
        messageConverter.addDelegate("img/png",new TulingImageConverter());
        messageConverter.addDelegate("img/jpg",new TulingImageConverter());
        messageConverter.addDelegate("application/word",new TulingWordConverter());
        messageConverter.addDelegate("word",new TulingWordConverter());
        messageListenerAdapter.setMessageConverter(messageConverter);

        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);


        return simpleMessageListenerContainer;
    }
}
