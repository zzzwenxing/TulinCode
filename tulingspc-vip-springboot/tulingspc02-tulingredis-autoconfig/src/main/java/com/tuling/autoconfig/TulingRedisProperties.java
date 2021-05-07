package com.tuling.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * redis的配置类
 * Created by smlz on 2019/3/29.
 */
@ConfigurationProperties(prefix = "spring.tuling.redis")
@Data
public class TulingRedisProperties {

    private String host;

    private Integer port;

    private String password;

    private Integer maxTotal=50;

    private Integer minIdel = 5;

    private Integer maxIdel=20;

    private Integer timeOut=2000;

    /**从连接池中借出的jedis都会经过测试*/
    private  boolean testOnBorrow = true;
    /**返回jedis到池中Jedis 实例都会经过测试*/
    private  boolean testOnRetrun = false;

}
