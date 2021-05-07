package com.redisoper;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 分布式操作redis的类
 * Created by Administrator on 2018/8/8.
 */
@Slf4j
public class ShardedRedisOperClient {

    private ShardedJedisPool shardedJedisPool;

    public ShardedRedisOperClient(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

    /**
     * set 操作
     * @param key 键
     * @param value 值
     * @return result
     */
    public  String set(String key,String value) {
        ShardedJedis jedis = null;
        String result = null;
        try {
            jedis = shardedJedisPool.getResource();
            result = jedis.set(key,value);
        } catch (Exception e) {
            log.error("set key:{},value:{},error:{}",key,value,e);
            shardedJedisPool.returnBrokenResource(jedis);
            return result;
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     *setex 操作
     * @param key key 键
     * @param expireTime 过期时间(m)
     * @param value 值
     * @return String
     */
    public  String setex(String key,int expireTime,String value) {
        ShardedJedis jedis = null;
        String result = null;
        try {
            jedis = shardedJedisPool.getResource();
            result = jedis.setex(key,expireTime,value);
        } catch (Exception e) {
            log.error("setex key:{},expireTime:{},value:{},error:{}",key,expireTime,value,e);
            shardedJedisPool.returnBrokenResource(jedis);
            return result;
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    public  Long  setnx(String key,String value) {
        ShardedJedis jedis = null;
        Long result = null;
        try {
            jedis = shardedJedisPool.getResource();
            result = jedis.setnx(key,value);
        } catch (Exception e) {
            log.error("setex key:{},value:{},error:{}",key,value,e);
            shardedJedisPool.returnBrokenResource(jedis);
            return result;
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * get 操作
     * @param key 键
     * @return value
     */
    public  String get(String key) {
        ShardedJedis jedis = null;
        String result = null;
        try {
            jedis = shardedJedisPool.getResource();
            result = jedis.get(key);
        } catch (Exception e) {
            log.error("get key:{}error:{}",key,e);
            shardedJedisPool.returnBrokenResource(jedis);
            return result;
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    public  String getset(String key,String value) {
        ShardedJedis jedis = null;
        String result = null;
        try {
            jedis = shardedJedisPool.getResource();
            result = jedis.getSet(key,value);
        } catch (Exception e) {
            log.error("get key:{}error:{}",key,e);
            shardedJedisPool.returnBrokenResource(jedis);
            return result;
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 让key失效
     * @param key 键
     * @param expireTime 失效时间
     * @return Long
     */
    public  Long expire(String key,int expireTime) {
        ShardedJedis jedis = null;
        Long result = null;
        try {
            jedis = shardedJedisPool.getResource();
            result = jedis.expire(key,expireTime);
        } catch (Exception e) {
            log.error("expire key:{},expireTime:{},error:{}",key,expireTime,e);
            shardedJedisPool.returnBrokenResource(jedis);
            return result;
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return boolean
     */
    public  boolean isExists(String key) {
        ShardedJedis jedis = null;
        boolean result =false;
        try {
            jedis = shardedJedisPool.getResource();
            result = jedis.exists(key);
        } catch (Exception e) {
            log.error("isExists key:{},error:{}",key,e);
            shardedJedisPool.returnBrokenResource(jedis);
            return result;
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }


    /**
     * 自增
     * @param key key
     * @return Long
     */
    public  Long incr(String key) {
        ShardedJedis jedis = null;
        Long result =null;
        try {
            jedis = shardedJedisPool.getResource();
            result = jedis.incr(key);
        } catch (Exception e) {
            log.error("incr key:{},error:{}",key,e);
            shardedJedisPool.returnBrokenResource(jedis);
            return result;
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 指定步长增加
     * @param key 键
     * @param step 步长
     * @return Long
     */
    public  Long incrBy(String key,Integer step) {
        ShardedJedis jedis = null;
        Long result =null;
        try {
            jedis = shardedJedisPool.getResource();
            result = jedis.incrBy(key,step);
        } catch (Exception e) {
            log.error("incrBy key:{},step:{},error:{}",key,step,e);
            shardedJedisPool.returnBrokenResource(jedis);
            return result;
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 递减
     * @param key key
     * @return Long
     */
    public  Long decr(String key) {
        ShardedJedis jedis = null;
        Long result =null;
        try {
            jedis = shardedJedisPool.getResource();
            result = jedis.decr(key);
        } catch (Exception e) {
            log.error("decr key:{},error:{}",key,e);
            shardedJedisPool.returnBrokenResource(jedis);
            return result;
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    /**
     * 指定步长递减
     * @param key 键
     * @param step 步长
     * @return Long
     */
    public  Long decrBy(String key,Integer step) {
        ShardedJedis jedis = null;
        Long result =null;
        try {
            jedis = shardedJedisPool.getResource();
            result = jedis.incrBy(key,step);
        } catch (Exception e) {
            log.error("decrBy key:{},step:{},error:{}",key,step,e);
            shardedJedisPool.returnBrokenResource(jedis);
            return result;
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }

    public  Long del(String key) {
        ShardedJedis jedis = null;
        Long result =null;
        try {
            jedis = shardedJedisPool.getResource();
            result = jedis.del(key);
        } catch (Exception e) {
            log.error("del key:{},error:{}",key,e);
            shardedJedisPool.returnBrokenResource(jedis);
            return result;
        } finally {
            shardedJedisPool.returnResource(jedis);
        }
        return result;
    }
}
