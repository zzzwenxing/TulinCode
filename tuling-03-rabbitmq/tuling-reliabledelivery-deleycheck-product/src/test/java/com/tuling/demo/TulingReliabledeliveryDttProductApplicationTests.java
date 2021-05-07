package com.tuling.demo;

import com.tuling.bo.MsgTxtBo;
import com.tuling.mapper.ProductInfoMapper;
import com.tuling.service.IProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TulingReliabledeliveryDttProductApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private IProductService productService;

	@Autowired
	private ProductInfoMapper productInfoMapper;



	@Test
	public void testUpdateProductStore2() {
		productInfoMapper.updateProductStoreById(1);
	}

	@Test
	public void retryMsg() {
		MsgTxtBo msgTxtBo = new MsgTxtBo();
		msgTxtBo.setMsgId("hhhhhh");
		msgTxtBo.setProductNo(9);
		msgTxtBo.setOrderNo(11111);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/retryMsg",msgTxtBo,String.class);
	}

}
