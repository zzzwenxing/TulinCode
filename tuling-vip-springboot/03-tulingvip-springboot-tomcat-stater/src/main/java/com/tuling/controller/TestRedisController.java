package com.tuling.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by smlz on 2019/4/3.
 */
@RestController
@Slf4j
public class TestRedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/testRedis/{key}/{value}")
    public String testRedisTemplate(@PathVariable("key")String key,@PathVariable("value")String value) {
        log.info("key:{},value:{}",key,value);
        redisTemplate.opsForValue().set(key,value);
        String valueInRedis =redisTemplate.opsForValue().get(key).toString();
        return valueInRedis;
    }




}
