package com.tuling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuling.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TulingvipSpringbootRedisApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSaveObj() throws IOException {
		//User user =new User(1,"zhangsan","123345","aa@qq.com","199107");
		User user = new User();
		redisTemplate.opsForValue().set("zhangsan",user);
		//这里使用的linkedHashmap
		Object obj = redisTemplate.opsForValue().get("zhangsan");

		ObjectMapper MAPPER = new ObjectMapper();
		//转为对象
		User user2 = MAPPER.readValue(MAPPER.writeValueAsString(obj),User.class);
		System.out.println(user2);

	}

	@Test
	public void testSaveString() {
		redisTemplate.opsForValue().set("smls","回首掏11111");
	}

}
