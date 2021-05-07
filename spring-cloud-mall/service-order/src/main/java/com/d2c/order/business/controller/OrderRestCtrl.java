package com.d2c.order.business.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.d2c.order.business.model.Order;
import com.d2c.order.business.service.OrderService;

@RestController
public class OrderRestCtrl {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/api/order", method = RequestMethod.GET)
    public Order findBySn(@RequestParam(value = "sn", required = true) String sn) {
        return orderService.findBySn(sn);
    }

    @RequestMapping(value = "/api/order/cache", method = RequestMethod.GET)
    public Order findCacheBySn(@RequestParam(value = "sn", required = true) String sn) {
        return orderService.findCacheBySn(sn);
    }

    @RequestMapping(value = "/api/order/tx", method = RequestMethod.GET)
    public JSONObject doSomeThing(String sn, Long productId, Long memberId) {
        int result = orderService.doSomeThing(sn, productId, memberId);
        JSONObject obj = new JSONObject();
        obj.put("result", result);
        return obj;
    }

}
