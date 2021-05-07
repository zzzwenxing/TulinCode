package com.tuling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by smlz on 2019/3/22.
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/save")
    public String save2Redis(@RequestParam("key") String key, @RequestParam("value") String value) {
        redisTemplate.opsForValue().set(key,value);
        return "OK";
    }

    @RequestMapping("/get/{key}")
    public String get4Redis(@PathVariable("key")String key) {
        return redisTemplate.opsForValue().get(key).toString();
    }

    @RequestMapping("/save2Hash")
    public String save2RedisHash(@RequestParam("key") String key,
                                 @RequestParam("field") String field,
                                 @RequestParam("value") String value) {
         redisTemplate.opsForHash().put(key,field,value);
         return "ok";
    }

    @RequestMapping("/get4Hash")
    public String get4RedisHash(@RequestParam("key") String key, @RequestParam("field") String field) {
        return redisTemplate.opsForHash().get(key,field).toString();
    }
}
