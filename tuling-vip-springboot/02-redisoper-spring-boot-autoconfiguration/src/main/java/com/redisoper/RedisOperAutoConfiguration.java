package com.redisoper;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.List;

/**
 * redis操作配置类
 * Created by Administrator on 2018/8/8.
 */
@Configuration
@EnableConfigurationProperties(value = {RedisConfigProperties.class,ShardedRedisConfigProperties.class})
@ConditionalOnClass(value = {JedisPoolConfig.class, JedisPool.class})
@Slf4j
public class RedisOperAutoConfiguration {

    @Autowired
    private RedisConfigProperties redisConfigProperties;
    @Autowired
    private ShardedRedisConfigProperties shardedRedisConfigProperties;

    public RedisOperAutoConfiguration(RedisConfigProperties redisConfigProperties,ShardedRedisConfigProperties shardedRedisConfigProperties) {
        this.redisConfigProperties = redisConfigProperties;
        this.shardedRedisConfigProperties = shardedRedisConfigProperties;
    }

    @Bean
    @ConditionalOnMissingBean(ShardedJedisPool.class)
    @ConditionalOnProperty(prefix = "spring.ha.redis",name = "USEHA",havingValue ="false")
    public JedisPool jedisPool() {
        log.info("加载jedisPool");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisConfigProperties.getMaxTotal());
        jedisPoolConfig.setMaxIdle(redisConfigProperties.getMaxIdel());
        jedisPoolConfig.setMinIdle(redisConfigProperties.getMinIdel());
        jedisPoolConfig.setTestOnBorrow(redisConfigProperties.isTestOnBorrow());
        jedisPoolConfig.setTestOnReturn(redisConfigProperties.isTestOnRetrun());
        JedisPool  jedisPool = new JedisPool(jedisPoolConfig,redisConfigProperties.getRedis_server_ip(),redisConfigProperties.getRedis_server_port(),1000*1,redisConfigProperties.getRedis_pass());
        return jedisPool;
    }

    @Bean
    @ConditionalOnMissingBean(JedisPool.class)
    @ConditionalOnProperty(prefix = "spring.ha.redis",name = "USEHA",havingValue ="true")
    public ShardedJedisPool shardedJedisPool() {
        log.info("shardedJedisPool");
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(shardedRedisConfigProperties.getMaxIdel());
        jedisPoolConfig.setMaxTotal(shardedRedisConfigProperties.getMaxTotal());
        jedisPoolConfig.setMinIdle(shardedRedisConfigProperties.getMinIdel());
        jedisPoolConfig.setTestOnReturn(shardedRedisConfigProperties.isTestOnRetrun());
        jedisPoolConfig.setTestOnBorrow(shardedRedisConfigProperties.isTestOnBorrow());
        jedisPoolConfig.setBlockWhenExhausted(true);

        JedisShardInfo jedisShardInfo = new JedisShardInfo(shardedRedisConfigProperties.getRedis_server_ip1(),shardedRedisConfigProperties.getRedis_server_port1());
        jedisShardInfo.setPassword(shardedRedisConfigProperties.getRedis_pass1());
        JedisShardInfo jedisShardInfo2 = new JedisShardInfo(shardedRedisConfigProperties.getRedis_server_ip2(),shardedRedisConfigProperties.getRedis_server_port2());
        jedisShardInfo2.setPassword(shardedRedisConfigProperties.getRedis_pass2());

        List<JedisShardInfo> shardInfos = Lists.newArrayList();
        shardInfos.add(jedisShardInfo);
        shardInfos.add(jedisShardInfo2);

        ShardedJedisPool shardingRedisPool = new ShardedJedisPool(jedisPoolConfig,shardInfos, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
        return shardingRedisPool;
    }

    @Bean
    @ConditionalOnBean(JedisPool.class)
    public RedisOperClient redisOperClient() {
        RedisOperClient redisOperClient = new RedisOperClient(jedisPool());
        return redisOperClient;
    }

    @Bean
    @ConditionalOnBean(ShardedJedisPool.class)
    public ShardedRedisOperClient shardedRedisOperClient() {
        ShardedRedisOperClient shardedRedisOperClient = new ShardedRedisOperClient(shardedJedisPool());
        return shardedRedisOperClient;
    }
}
