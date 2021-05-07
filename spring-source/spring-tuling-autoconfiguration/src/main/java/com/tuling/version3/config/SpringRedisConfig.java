package com.tuling.version3.config;

import com.tuling.version3.support.RedisCfgProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by smlz on 2019/9/3.
 */
@Configuration
@PropertySource(value = {"classpath:rediscfg.properties"})
public class SpringRedisConfig {

	@Bean
	public RedisCfgProperties redisCfgProperties() {
		RedisCfgProperties redisCfgProperties = new RedisCfgProperties();
		return redisCfgProperties;
	}

	@Bean
	public JedisPoolConfig jedisPoolConfig(RedisCfgProperties redisCfgProperties) {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(redisCfgProperties.getMaxTotal());
		jedisPoolConfig.setMaxIdle(redisCfgProperties.getMaxIdle());
		return jedisPoolConfig;
	}

	@Bean
	public JedisConnectionFactory jedisConnectionFactory(RedisCfgProperties redisCfgProperties) {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setPort(redisCfgProperties.getPort());
		jedisConnectionFactory.setHostName(redisCfgProperties.getHost());
		jedisConnectionFactory.setPoolConfig(jedisPoolConfig(redisCfgProperties));
		return jedisConnectionFactory;
	}

	@Bean
	public RedisTemplate redisTemplate() {
		RedisTemplate redisTemplate = new RedisTemplate();
		redisTemplate.setConnectionFactory(jedisConnectionFactory(redisCfgProperties()));
		redisTemplate.setDefaultSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
		return redisTemplate;
	}
}
