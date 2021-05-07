package com.tuling.version1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by smlz on 2019/8/31.
 */
@Slf4j
public class TestBaseXml {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("classpath:spring/spring-redis.xml");
		RedisTemplate redisTemplate = (RedisTemplate) context.getBean("redisTemplate");

		redisTemplate.opsForValue().set("smlz","司马");

		System.out.println("从redis获取===="+redisTemplate.opsForValue().get("smlz"));
	}

}
