package com.tuling.controller;

import com.tuling.entity.OrderVo;
import com.tuling.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by smlz on 2019/3/26.
 */
@RestController
@RequestMapping("/order")
@Slf4j
@RefreshScope
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    private ServiceInstance serviceInstance;


    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/queryOrdersByUserId/{userId}")
    public List<OrderVo> queryOrdersByUserId(@PathVariable("userId") Integer userId) throws InterruptedException {
        return orderServiceImpl.queryOrdersByUserId(userId);
    }

    @RequestMapping("/queryAll")
    public List<OrderVo> queryAll() throws InterruptedException {

        //超时降级
        //Thread.sleep(4000);
        if(true) {
            throw new RuntimeException("不存在的用户");
        }

        List<OrderVo> list = new ArrayList<>();
        OrderVo orderVo = new OrderVo();
        orderVo.setUserId(1);
        orderVo.setOrderId(1);
        orderVo.setOrderMoney(new BigDecimal(200));
        orderVo.setDbSource("tuling_source01");
        list.add(orderVo);
        return list;

    }



    @RequestMapping("/getRegisterInfo")
    public String info() {
        return serviceInstance.getHost()+":"+serviceInstance.getPort();
    }



}
