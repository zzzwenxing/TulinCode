package com.tuling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuling.entity.*;
import com.tuling.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TulingvipRabbitmqSpringwithrabbitmqApplicationTests {


	@Autowired
	private RabbitAdmin rabbitAdmin;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testTopicExchange() {
		//声明一个交换机
		TopicExchange topicExchange = new TopicExchange("rabbitadmin.topic.exchange",true,false);
		rabbitAdmin.declareExchange(topicExchange);

		//申明一个队列
		Queue queue = new Queue("rabbitadmin.topic.queue",true);
		rabbitAdmin.declareQueue(queue);

		//申明一个绑定
		Binding binding = new Binding("rabbitadmin.topic.queue",Binding.DestinationType.QUEUE,
				"rabbitadmin.topic.exchange","rabbitadmin.#",null);
		rabbitAdmin.declareBinding(binding);
	}

	@Test
	public void testDirectExchange() {
		DirectExchange directExchange = new DirectExchange("rabbitadmin.direct.exchange",true,false);
		rabbitAdmin.declareExchange(directExchange);
		Queue queue = new Queue("rabbitadmin.direct.queue",true);
		rabbitAdmin.declareQueue(queue);
		rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with("rabbitadmin.key.#"));

	}

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Test
	public void testRabbitmqTemplate() {

		MessageProperties messageProperties = new MessageProperties();
		messageProperties.getHeaders().put("company","tuling");
		messageProperties.getHeaders().put("name","smlz");

		String msgBody = "hello tuling";
		Message message = new Message(msgBody.getBytes(),messageProperties);


		//不需要message对象发送
		rabbitTemplate.convertAndSend("tuling.direct.exchange","direct.key","smlz");
	}

	@Test
	public void simpleMessageListenerContainerTest() {
		rabbitTemplate.convertAndSend("tuling.topic.exchange","topic.xixi","你好 图灵");
	}


	@Test
	public void messageListenerAdaperQueueOrTagToMethodName(){
		rabbitTemplate.convertAndSend("tuling.topic.exchange","topic.xixi","你好 图灵");
		rabbitTemplate.convertAndSend("tuling.topic.exchange","topic.key.xixi","你好 smlz");
	}

	@Test
	public void sendJson() throws JsonProcessingException {

		Order order = new Order();
		order.setOrderNo(UUID.randomUUID().toString());
		order.setCreateDt(new Date());
		order.setPayMoney(10000.00);
		order.setUserName("smlz");

		ObjectMapper objectMapper = new ObjectMapper();
		String orderJson = objectMapper.writeValueAsString(order);

		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setContentType("application/json");
		Message orderMsg = new Message(orderJson.getBytes(),messageProperties);
		rabbitTemplate.convertAndSend("tuling.direct.exchange","rabbitmq.order",orderMsg);

	}

	@Test
	public void sendJavaObj() throws JsonProcessingException {

		Order order = new Order();
		order.setOrderNo(UUID.randomUUID().toString());
		order.setCreateDt(new Date());
		order.setPayMoney(10000.00);
		order.setUserName("smlz");

		ObjectMapper objectMapper = new ObjectMapper();
		String orderJson = objectMapper.writeValueAsString(order);

		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setContentType("application/json");
		messageProperties.getHeaders().put("__TypeId__","com.tuling.entity.Order");
		Message orderMsg = new Message(orderJson.getBytes(),messageProperties);
		rabbitTemplate.convertAndSend("tuling.direct.exchange","rabbitmq.order",orderMsg);

	}



	@Test
	public void sendImage() throws IOException {
		byte[] imgBody = Files.readAllBytes(Paths.get("D:/smlz/file01","smlz.png"));
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setContentType("img/png");
		Message message = new Message(imgBody, messageProperties);
		rabbitTemplate.send("tuling.direct.exchange","rabbitmq.file",message);

	}

	@Test
	public void sendWord() throws IOException {
		byte[] imgBody = Files.readAllBytes(Paths.get("D:/smlz/file01","spring.docx"));
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setContentType("application/word");
		Message message = new Message(imgBody, messageProperties);
		rabbitTemplate.send("tuling.direct.exchange","rabbitmq.file",message);

	}
}
