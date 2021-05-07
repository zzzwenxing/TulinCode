package com.tuling.demo;

import com.tuling.mapper.ProductInfoMapper;
import com.tuling.service.IProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

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

}
