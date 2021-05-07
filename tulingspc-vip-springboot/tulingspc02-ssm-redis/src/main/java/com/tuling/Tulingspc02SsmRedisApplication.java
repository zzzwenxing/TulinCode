package com.tuling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ImportResource(locations = "beans.xml")
public class Tulingspc02SsmRedisApplication {

	@Autowired
	private RedisTemplate redisTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Tulingspc02SsmRedisApplication.class, args);
	}

	/**
	 * 测试Spring 整合redis
	 * @return
	 */
	@RequestMapping("/testRedisByXml")
	public String testRedis() {
		redisTemplate.opsForValue().set("smlz","司马是老贼");
		return redisTemplate.opsForValue().get("smlz").toString();
	}

}
