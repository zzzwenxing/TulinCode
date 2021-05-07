package com.tuling.controller;

import com.tuling.vo.OrderVo;
import com.tuling.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smlz on 2019/5/6.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/queryUserInfoById/{userId}")
    public UserInfoVo queryUserInfoById(@PathVariable("userId") Integer userId) {
        log.info("根据用户ID:{}查询用户信息",userId);
        OrderVo orderVo = restTemplate.getForObject("http://MS-PROVIDER-ORDER-TRACE-QUICKSTART/order/queryOrderInfoById/"+userId,OrderVo.class);
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setUserName("隔壁老王");
        List<OrderVo> orderVoList = new ArrayList<>();
        orderVoList.add(orderVo);
        userInfoVo.setOrderVoList(orderVoList);
        return userInfoVo;
    }
}
