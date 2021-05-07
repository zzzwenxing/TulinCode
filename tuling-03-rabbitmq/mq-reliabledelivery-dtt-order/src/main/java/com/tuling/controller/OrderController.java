package com.tuling.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuling.entity.OrderInfo;
import com.tuling.service.IOrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by smlz on 2019/10/11.
 */
@RestController
public class OrderController {

    @Autowired
    private IOrderInfoService orderInfoService;

    @RequestMapping("/saveOrder")
    public String saveOrder() throws JsonProcessingException {

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderNo(System.currentTimeMillis());
        orderInfo.setCreateTime(new Date());
        orderInfo.setUpdateTime(new Date());
        orderInfo.setUserName("smlz");
        orderInfo.setMoney(10000);
        orderInfo.setProductNo(1);

        orderInfoService.saveOrderInfoWithMessage(orderInfo);
        return "ok";
    }
}
