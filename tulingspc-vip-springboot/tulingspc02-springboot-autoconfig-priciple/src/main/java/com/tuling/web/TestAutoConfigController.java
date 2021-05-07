package com.tuling.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by smlz on 2019/5/22.
 */
@RestController
public class TestAutoConfigController {

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping("/testRedisByAutoConfig")
    public String testRedisByAutoConfig() {
        redisTemplate.opsForValue().set("sm-lz","司马不是老贼");
        return redisTemplate.opsForValue().get("sm-lz").toString();
    }
}
