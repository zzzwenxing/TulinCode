package com.tuling.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tuling.entity.OrderVo;
import com.tuling.entity.User;
import com.tuling.entity.UserInfoVo;
import com.tuling.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by smlz on 2019/3/26.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @RequestMapping("/queryUserInfoById/{userId}")
    @HystrixCommand(fallbackMethod = "queryUserInfoByIdFallBack")
    public UserInfoVo queryUserInfoById(@PathVariable("userId") Integer userId) {
        //User user = userServiceImpl.queryUserById(userId);
        ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://MS-PROVIDER-ORDER/order/queryOrdersByUserId/"+userId,List.class);
        List<OrderVo> orderVoList = responseEntity.getBody();

        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setOrderVoList(orderVoList);
        userInfoVo.setUserName("张三");
        return userInfoVo;
    }

    protected UserInfoVo queryUserInfoByIdFallBack(Integer userId) {
        log.info("触发降级方法=根据用户ID{}查询订单服务异常:{}",userId);
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setOrderVoList(null);
        userInfoVo.setUserName("-1");
        return userInfoVo;
    }


}
