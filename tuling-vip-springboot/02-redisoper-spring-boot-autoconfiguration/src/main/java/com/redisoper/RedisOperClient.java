package com.redisoper;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Collections;

/**
 * redis操作类
 * Created by Administrator on 2018/8/8.
 */
@Slf4j
public class RedisOperClient {

    private JedisPool jedisPool;

    public RedisOperClient(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * set 操作
     *
     * @param key   键
     * @param value 值
     * @return result
     */
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

    /**
     * list接口存储用作消息队列
     *
     * @param queueName 队列名称
     * @param value     消息
     * @return 成功
     */
    public long lpush(String queueName, String value) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.lpush(queueName, value);
        } catch (Exception e) {
            log.error("lpush key:{},value:{},异常:{}", queueName, value, e);
            jedisPool.returnResource(jedis);
            return result;
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 判断是否为空
     *
     * @param queueName 队列名称
     * @return true|false
     */
    public boolean isEmptyQueue(String queueName) {

        Jedis jedis = null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.llen(queueName).intValue() == 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("isEmptyQueue key:{},异常:{}", queueName, e);
            jedisPool.returnResource(jedis);
            return false;
        } finally {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 出队操作
     *
     * @param queueName 队列名称
     * @return 队首消息
     */
    public String rpop(String queueName) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.rpop(queueName);
        } catch (Exception e) {
            log.error("rpop key:{},异常:{}", queueName, e);
            jedisPool.returnResource(jedis);
            return result;
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    public String set(String key, String value, String set_if_not_exits, String set_with_expire_time, int expire) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.set(key, value, set_if_not_exits, set_with_expire_time, expire);
        } catch (Exception e) {
            log.error("set key:{},value:{},set_if_not_exits:{},set_with_expire_time:{},expire:{},error:{}", key, value, set_if_not_exits, set_with_expire_time, expire, e);
            jedisPool.returnResource(jedis);
            return result;
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * setex 操作
     *
     * @param key        key 键
     * @param expireTime 过期时间(m)
     * @param value      值
     * @return String
     */
    public String setex(String key, int expireTime, String value) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.setex(key, expireTime, value);
        } catch (Exception e) {
            log.error("setex key:{},expireTime:{},value:{},error:{}", key, expireTime, value, e);
            jedisPool.returnResource(jedis);
            return result;
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * get 操作
     *
     * @param key 键
     * @return value
     */
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

    /**
     * 让key失效
     *
     * @param key        键
     * @param expireTime 失效时间
     * @return Long
     */
    public Long expire(String key, int expireTime) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.expire(key, expireTime);
        } catch (Exception e) {
            log.error("expire key:{},expireTime:{},error:{}", key, expireTime, e);
            jedisPool.returnResource(jedis);
            return result;
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return boolean
     */
    public boolean isExists(String key) {
        Jedis jedis = null;
        boolean result = false;
        try {
            jedis = jedisPool.getResource();
            result = jedis.exists(key);
        } catch (Exception e) {
            log.error("isExists key:{},error:{}", key, e);
            jedisPool.returnResource(jedis);
            return result;
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }


    /**
     * 自增
     *
     * @param key key
     * @return Long
     */
    public Long incr(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.incr(key);
        } catch (Exception e) {
            log.error("incr key:{},error:{}", key, e);
            jedisPool.returnResource(jedis);
            return result;
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 指定步长增加
     *
     * @param key  键
     * @param step 步长
     * @return Long
     */
    public Long incrBy(String key, Integer step) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.incrBy(key, step);
        } catch (Exception e) {
            log.error("incrBy key:{},step:{},error:{}", key, step, e);
            jedisPool.returnResource(jedis);
            return result;
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 递减
     *
     * @param key key
     * @return Long
     */
    public Long decr(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.decr(key);
        } catch (Exception e) {
            log.error("decr key:{},error:{}", key, e);
            jedisPool.returnResource(jedis);
            return result;
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 指定步长递减
     *
     * @param key  键
     * @param step 步长
     * @return Long
     */
    public Long decrBy(String key, Integer step) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.incrBy(key, step);
        } catch (Exception e) {
            log.error("decrBy key:{},step:{},error:{}", key, step, e);
            jedisPool.returnResource(jedis);
            return result;
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }

    public Long del(String key) {
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{},error:{}", key, e);
            jedisPool.returnResource(jedis);
            return result;
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;
    }


    public long eval(String script, String lock, String lockValue) {
        Jedis jedis = null;
        //删除锁失败
        long result = 0;
        try {
            jedis = jedisPool.getResource();
            result = (Long) jedis.eval(script, Collections.singletonList(lock), Collections.singletonList(lockValue));
            return result;
        } catch (Exception e) {
            log.error("eval script:{},lock:{},lockValue:{},error:{}", script, lock, lockValue, e);
            jedisPool.returnResource(jedis);
        } finally {
            jedisPool.returnResource(jedis);
        }
        return result;

    }
}
