package com.tuling.busi.controller;

import com.tuling.busi.entity.OrderInfo;
import com.tuling.busi.service.IOrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by smlz on 2019/7/25.
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderInfoController {

    @Autowired
    private IOrderInfoService orderInfoService;

    @RequestMapping("/saveOrder")
    public Map saveOrder(OrderInfo orderInfo) {
        Map<String,Object> retMap = new HashMap<>();
        try {
            orderInfoService.saveOrder(orderInfo);
            retMap.put("code","1");
            retMap.put("msg","保存成功");

        }catch (Exception e) {
            log.error("保存订单异常:{}",e);
            retMap.put("code","-1");
            retMap.put("msg","保存失败");
        }
        return retMap;
    }
}
