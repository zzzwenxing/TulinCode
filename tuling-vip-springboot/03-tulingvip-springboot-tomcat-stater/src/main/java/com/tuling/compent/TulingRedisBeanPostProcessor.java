package com.tuling.compent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * 自定义的后置处理器
 * Created by smlz on 2019/4/3.
 */
@Component
@Slf4j
public class TulingRedisBeanPostProcessor implements BeanPostProcessor{

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof RedisTemplate) {
            RedisTemplate redisTemplate = (RedisTemplate) bean;
            redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer(Object.class));
            return redisTemplate;
        }
        return bean;
    }



    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof RedisTemplate) {
            RedisTemplate redisTemplate = (RedisTemplate) bean;
            log.info("redisTemplate的序列化器:{}",redisTemplate.getDefaultSerializer().getClass().getSimpleName());
        }
        return bean;
    }

}
