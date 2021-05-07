package com.tuling.controller;

import com.tuling.entity.OrderVo;
import com.tuling.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by smlz on 2019/3/26.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    private ServiceInstance serviceInstance;

    @RequestMapping("/queryOrdersByUserId/{userId}")
    public List<OrderVo> queryOrdersByUserId(@PathVariable("userId") Integer userId, @RequestHeader("token") String token) {
        System.out.println("TOken:"+token);
        List<OrderVo> orderVos = new ArrayList<>();
        OrderVo orderVo = new OrderVo();
        orderVo.setOrderId(1);
        orderVo.setUserId(userId);
        orderVo.setDbSource("ds01");
        orderVo.setOrderMoney(new BigDecimal(100));
        orderVos.add(orderVo);
        return orderVos;
    }

    @RequestMapping("/getRegisterInfo")
    public String info() {
        return serviceInstance.getHost()+":"+serviceInstance.getPort();
    }
}
