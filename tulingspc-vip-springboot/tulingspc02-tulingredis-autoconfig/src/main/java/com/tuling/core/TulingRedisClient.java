package com.tuling.core;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * 单机版
 * Created by smlz on 2019/3/29.
 */
@Slf4j
public class TulingRedisClient implements ITulingRedis {


    private JedisPool jedisPool;

    public TulingRedisClient(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.set(key, value);
        } catch (Exception e) {
            log.error("set key:{},value:{},error:{}", key, value, e);
            jedisPool.returnResource(jedis);
            return result;
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    @Override
    public String get(String key) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{}error:{}", key, e);
            jedisPool.returnResource(jedis);
            return result;
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }
}
