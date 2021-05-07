package com.tuling.api;


import com.tuling.entity.OrderVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * Created by smlz on 2019/4/10.
 */
@FeignClient(name = "ms-provider-order-feign",path = "/order")
public interface OrderApi {

    @RequestMapping("/queryOrdersByUserId/{userId}")
    List<OrderVo> queryOrdersByUserId(@PathVariable("userId") Integer userId);

    @RequestMapping("/getRegisterInfo")
    String info();
}
