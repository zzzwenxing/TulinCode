package com.zhuge.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "stock-service")
public interface StockService {

    /**
     * http://stock-service/stock/deduct/{productId}/{stockCount}
     *
     * @param productId
     * @param stockCount
     * @return
     */
    @GetMapping(value = "/stock/deduct/{productId}/{stockCount}")
    String deductStock(@PathVariable("productId") Long productId, @PathVariable("stockCount") Integer stockCount);

}