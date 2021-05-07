package com.tuling;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuling.compent.TulingMsgSender;
import com.tuling.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TulingvipSpringbootRabbitmqProducerApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private TulingMsgSender tulingMsgSender;

	@Test
	public void testMsgSender() throws JsonProcessingException {
		/**
		 * 创建消息属性
		 */
		Map<String,Object> msgProp = new HashMap<>();
		msgProp.put("company","tuling");
		msgProp.put("name","smlz");

		Order order = new Order();
		order.setOrderNo(UUID.randomUUID().toString());
		order.setUserName("smlz");
		order.setPayMoney(10000.00);
		order.setCreateDt(new Date());
		ObjectMapper objectMapper = new ObjectMapper();

		String orderJson = objectMapper.writeValueAsString(order);

		tulingMsgSender.sendMsg(orderJson,msgProp);
	}

	@Test
	public void testSenderOrder() throws Exception {
		Order order = new Order();
		order.setOrderNo(UUID.randomUUID().toString());
		order.setUserName("smlz");
		order.setPayMoney(10000.00);
		order.setCreateDt(new Date());

		//直接发送对象
		tulingMsgSender.sendOrderMsg(order);
	}

	@Test
	public void testSenderDelay() {
		Order order = new Order();
		order.setOrderNo(UUID.randomUUID().toString());
		order.setUserName("smlz");
		order.setPayMoney(10000.00);
		order.setCreateDt(new Date());

		tulingMsgSender.sendDelayMessage(order);
		//tulingMsgSender.sendDelayMessage2(order);
	}

	@Test
	public void sendMsgToCluster() throws JsonProcessingException {
		/**
		 * 创建消息属性
		 */
		Map<String,Object> msgProp = new HashMap<>();
		msgProp.put("company","tuling");
		msgProp.put("name","smlz");

		Order order = new Order();
		order.setOrderNo(UUID.randomUUID().toString());
		order.setUserName("smlz");
		order.setPayMoney(10000.00);
		order.setCreateDt(new Date());
		ObjectMapper objectMapper = new ObjectMapper();

		String orderJson = objectMapper.writeValueAsString(order);

		tulingMsgSender.sendMsgToCluster(orderJson,msgProp);
	}

}
