package com.tuling;

import com.tuling.entity.MessageContent;
import com.tuling.entity.OrderInfo;
import com.tuling.mapper.MsgContentMapper;
import com.tuling.mapper.OrderInfoMapper;
import com.tuling.service.IOrderInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MqReliabledeliveryDttOrderApplicationTests {



	@Test
	public void contextLoads() {
	}

	@Autowired
	private IOrderInfoService orderInfoService;

	@Autowired
	private OrderInfoMapper orderInfoMapper;

	@Autowired
	private MsgContentMapper msgContentMapper;

	@Test
	public void testOrderSave() {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setOrderNo(System.currentTimeMillis());
		orderInfo.setCreateTime(new Date());
		orderInfo.setUpdateTime(new Date());
		orderInfo.setUserName("smlz");
		orderInfo.setMoney(10000);
		orderInfo.setProductNo(1);
		orderInfoMapper.saveOrderInfo(orderInfo);
	}

	@Test
	public void testMsgContent() {
		MessageContent messageContent = new MessageContent();
		messageContent.setMsgId(UUID.randomUUID().toString());
		messageContent.setCreateTime(new Date());
		messageContent.setUpdateTime(new Date());
		messageContent.setExchange("testexchange");
		messageContent.setRoutingKey("testKey");
		messageContent.setMsgStatus(0);
		msgContentMapper.saveMsgContent(messageContent);
	}

	@Test
	public void testMessageMapper() {
		msgContentMapper.updateMsgRetryCount("3d3ea583-94bb-48dc-b406-a39e9e0b7e4e");
		System.out.println(1/0);
	}




}
