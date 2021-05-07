package com.redisoper;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 分布式redis
 * Created by Administrator on 2018/8/8.
 */
@ConfigurationProperties("spring.ha.redis")
@Data
public class ShardedRedisConfigProperties {

    private  Integer maxTotal=50;
    /**jedispool中jedis 最大空闲数*/
    private  Integer maxIdel = 20;
    /**jedispool中最小空闲数*/
    private  Integer minIdel = 5;
    /**从连接池中借出的jedis都会经过测试*/
    private  boolean testOnBorrow = true;
    /**返回jedis到池中Jedis 实例都会经过测试*/
    private  boolean testOnRetrun = false;
    /**IP*/
    private  String redis_server_ip1;
    /**port*/
    private  Integer redis_server_port1 ;
    /**连接redis的password*/
    private  String redis_pass1 ;

    /**IP*/
    private  String redis_server_ip2;
    /**port*/
    private  Integer redis_server_port2 ;
    /**连接redis的password*/
    private  String redis_pass2 ;
}
