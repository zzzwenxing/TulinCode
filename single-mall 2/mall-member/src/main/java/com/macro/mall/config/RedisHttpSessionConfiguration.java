package com.macro.mall.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author ：杨过
 * @date ：Created in 2020/2/14
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description: 开启SpringSession接管原Session
 **/
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class RedisHttpSessionConfiguration {
    /**
     * 引入分布式会话体系,会话内容存储在Redis当中,原理请阅读源码
     */

}
