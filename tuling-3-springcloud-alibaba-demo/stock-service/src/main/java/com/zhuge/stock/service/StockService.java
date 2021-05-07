package com.zhuge.stock.service;

import org.springframework.stereotype.Service;

@Service
public class StockService {

	public String deductStock(Long productId, Integer stockCount) {
		System.out.println("商品productId=" + productId + "：扣减库存" + stockCount);
		return "success";
	}

}