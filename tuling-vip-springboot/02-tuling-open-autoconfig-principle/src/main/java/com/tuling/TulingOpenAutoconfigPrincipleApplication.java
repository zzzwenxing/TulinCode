package com.tuling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@ImportResource(locations = "classpath:beans.xml")
@RestController
public class TulingOpenAutoconfigPrincipleApplication {

	@Autowired
	private RedisTemplate redisTemplate;

	public static void main(String[] args) {
		SpringApplication.run(TulingOpenAutoconfigPrincipleApplication.class, args);
	}

	@RequestMapping("/testRedis")
	public String testRedis() {
		redisTemplate.opsForValue().set("smlz1","123456");
		return "OK";
	}


/*	@Bean
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)  {
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setDefaultSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}*/



}
