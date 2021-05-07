package com.tuling.controller;

import com.tuling.entity.OrderVo;
import com.tuling.service.OrderServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

    @Value("${busi.ops}")
    private String isNewLogic;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/queryOrdersByUserId/{userId}")
    public List<OrderVo> queryOrdersByUserId(@PathVariable("userId") Integer userId) throws InterruptedException {
        return orderServiceImpl.queryOrdersByUserId(userId);
    }

    @RequestMapping("/queryAll")
    public List<OrderVo> queryAll() throws InterruptedException {

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

    /**
     * 测试手动刷新
     * @return
     */
    @RequestMapping("/testManualRefresh")
    public String testManualRefresh() {
        if(isNewLogic.equals("Y")) {
            System.out.println("走新逻辑................");
        }else {
            System.out.println("走老的业务逻辑...........");
        }

        return isNewLogic;
    }

}
