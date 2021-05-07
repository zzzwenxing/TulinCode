package com.example.seckill;

import com.redisson.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)   
@SpringBootTest(classes={Application.class})// 指定启动类
public class ApplicationTests {

	@Autowired
	StringRedisTemplate stringRedisTemplate; //1
	
	@Test
	public void test() throws Exception {
		//设置一个key，商品的库存数量为10
		stringRedisTemplate.opsForValue().set("stock","10");
		Assert.assertEquals("10", stringRedisTemplate.opsForValue().get("stock"));
	}

}
