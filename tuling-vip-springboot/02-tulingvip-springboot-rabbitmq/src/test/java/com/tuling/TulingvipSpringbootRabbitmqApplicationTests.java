package com.tuling;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TulingvipSpringbootRabbitmqApplicationTests {


	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRabbitMq() {
		Map<String,Object> sendContext = new HashMap<>();
		sendContext.put("name","张三");
		sendContext.put("sex","男");
		rabbitTemplate.convertAndSend("tulingDirectExchange","tuling.directkey",sendContext);
	}

}
