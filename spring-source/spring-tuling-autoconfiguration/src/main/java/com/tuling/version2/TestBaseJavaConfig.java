package com.tuling.version2;

import com.tuling.version1.Employee;
import com.tuling.version2.config.SpringRedisonfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by smlz on 2019/8/31.
 */
@Slf4j
public class TestBaseJavaConfig {

	public static void main(String[] args) {


		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringRedisonfig.class);
		RedisTemplate redisTemplate = (RedisTemplate) context.getBean("redisTemplate");

		redisTemplate.opsForValue().set("smlz","司马");

		System.out.println("从redis获取===="+redisTemplate.opsForValue().get("smlz"));
	}
}
