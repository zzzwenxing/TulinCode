package com.redisoper;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 单机redis配置
 * Created by Administrator on 2018/8/8.
 */
@ConfigurationProperties("spring.redis")
public class RedisConfigProperties {

    /**jedispool中jedis最大的可用实例*/

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
    private  String redis_server_ip;
    /**port*/
    private  Integer redis_server_port ;
    /**连接redis的password*/
    private  String redis_pass ;

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdel() {
        return maxIdel;
    }

    public void setMaxIdel(Integer maxIdel) {
        this.maxIdel = maxIdel;
    }

    public Integer getMinIdel() {
        return minIdel;
    }

    public void setMinIdel(Integer minIdel) {
        this.minIdel = minIdel;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public boolean isTestOnRetrun() {
        return testOnRetrun;
    }

    public void setTestOnRetrun(boolean testOnRetrun) {
        this.testOnRetrun = testOnRetrun;
    }

    public String getRedis_server_ip() {
        return redis_server_ip;
    }

    public void setRedis_server_ip(String redis_server_ip) {
        this.redis_server_ip = redis_server_ip;
    }

    public Integer getRedis_server_port() {
        return redis_server_port;
    }

    public void setRedis_server_port(Integer redis_server_port) {
        this.redis_server_port = redis_server_port;
    }

    public String getRedis_pass() {
        return redis_pass;
    }

    public void setRedis_pass(String redis_pass) {
        this.redis_pass = redis_pass;
    }
}
