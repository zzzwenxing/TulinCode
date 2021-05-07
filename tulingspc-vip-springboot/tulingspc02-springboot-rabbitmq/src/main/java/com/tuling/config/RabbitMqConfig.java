package com.tuling.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * rabbitmq配置类
 * Created by smlz on 2019/3/24.
 */
@Configuration
public class RabbitMqConfig {


    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    /**
     * 直接交换机
     * @return
     */
    @Bean
    public DirectExchange tulingDirectExchange() {
        //参数 durable:表示消息是否可持久化
        //autoDelete:表示若没有队列和此交换机绑定 就直接删除该交换机
        return new DirectExchange("tulingDirectExchange",true,false);
    }


    @Bean
    public Queue tulingDirectQueue() {
        return new Queue("tulingDirectQueue",true,false,false);
    }

    @Bean
    public Binding tulingDq2De(){
        return BindingBuilder.bind(tulingDirectQueue()).to(tulingDirectExchange()).with("tuling.directkey");
    }

    /**
     * 扇形交换机
     * @return
     */
    @Bean
    public FanoutExchange tulingFanoutExchange() {
        return new FanoutExchange("tulingFanoutExchange",true,false);
    }

    @Bean
    public Queue tulingfanoutQueue1() {
        return new Queue("tulingFanoutQueue1",true,false,false);
    }

    @Bean
    public Queue tulingfanoutQueue2() {
        return new Queue("tulingFanoutQueue2",true,false,false);
    }

    @Bean
    public Binding tulingBind1() {
        return BindingBuilder.bind(tulingfanoutQueue1()).to(tulingFanoutExchange());

    }

    @Bean
    public Binding tulingBind2() {
        return BindingBuilder.bind(tulingfanoutQueue2()).to(tulingFanoutExchange());
    }

    /**
     * 主题交换机
     */
    @Bean
    public TopicExchange tulingTopicExchange() {
        return new TopicExchange("tulingTopicExchange",true,false);
    }

    @Bean
    public Queue tulingTopicQueue() {
        return new Queue("tulingTopicQueue",true,false,false);
    }

    @Bean
    public Queue tulingTopicQueue2() {
        return new Queue("tulingTopicQueue2",true,false,false);
    }

    @Bean
    public Binding topicBind1(){
        return BindingBuilder.bind(tulingTopicQueue()).to(tulingTopicExchange()).with("topic.key.#");

    }

    @Bean
    public Binding topicBind2(){
        return BindingBuilder.bind(tulingTopicQueue2()).to(tulingTopicExchange()).with("#.key");

    }



}
