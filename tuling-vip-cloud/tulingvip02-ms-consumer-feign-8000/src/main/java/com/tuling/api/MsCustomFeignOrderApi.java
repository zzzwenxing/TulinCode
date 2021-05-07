package com.tuling.api;

import com.tuling.config.MsProvider8007CustomCfg;
import com.tuling.entity.OrderVo;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

/**
 * Created by smlz on 2019/4/10.
 */
@FeignClient(name = "ms-provider-order-feign-custom01",configuration = MsProvider8007CustomCfg.class,path = "/order")
public interface MsCustomFeignOrderApi {

    @RequestLine("GET /queryOrdersByUserId/{userId}")
    public List<OrderVo> queryOrdersByUserId(@Param("userId") Integer userId);
}
