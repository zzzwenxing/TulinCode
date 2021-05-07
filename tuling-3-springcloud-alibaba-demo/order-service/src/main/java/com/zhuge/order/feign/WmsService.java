package com.zhuge.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "wms-service")
public interface WmsService {

    /**
     * http://wms-service/wms/delivery/{userId}/{productId}
     *
     * @param userId
     * @param productId
     * @return
     */
    @PostMapping(value = "/wms/delivery/{userId}/{productId}")
    String delivery(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId);

}