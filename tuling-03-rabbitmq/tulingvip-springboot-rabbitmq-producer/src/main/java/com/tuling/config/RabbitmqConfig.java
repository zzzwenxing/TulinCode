package com.tuling.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by smlz on 2019/10/9.
 */
@Configuration
public class RabbitmqConfig {

    @Bean
    public DirectExchange tulingBootDirectExchange() {
        DirectExchange directExchange = new DirectExchange("springboot.direct.exchange",true,false);
        return directExchange;
    }



    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange("delayExchange", "x-delayed-message",true, false,args);
    }


    @Bean
    public Queue tulingBootQueue() {
        Queue queue = new Queue("tulingBootQueue",true,false,false);
        return queue;
    }

    @Bean
    public Queue tulingClusterQueue() {
        Queue queue = new Queue("tulingClusterQueue",true,false,false);
        return queue;
    }

    @Bean
    public Queue tulingBootDelayQueue() {
        Queue queue = new Queue("tulingBootDelayQueue",true,false,false);
        return queue;
    }

    @Bean
    public Binding tulingBootBinder() {
        return BindingBuilder.bind(tulingBootQueue()).to(tulingBootDirectExchange()).with("springboot.key");
    }

    @Bean
    public Binding tulingClusterBinder() {
        return BindingBuilder.bind(tulingClusterQueue()).to(tulingBootDirectExchange()).with("rabbitmq.cluster.key");
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(tulingBootDelayQueue()).to(delayExchange()).with("springboot.delay.key").noargs();
    }

}