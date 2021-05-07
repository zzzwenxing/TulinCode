package com.tuling.busi.controller;

import com.tuling.busi.bean.Order;
import com.tuling.busi.service.OrderServiceImpl;
import com.tuling.multidatasource.annotation.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by smlz on 2019/4/17.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @RequestMapping("/save")
    @Router
    public Order insertOrder(Order order) {
        orderService.insertOrder(order);
        return order;
    }
}
