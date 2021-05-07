package com.tuling.controller;

import com.tuling.entity.OrderVo;
import com.tuling.entity.User;
import com.tuling.entity.UserInfoVo;
import com.tuling.exception.TulingTimeoutException;
import com.tuling.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
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


/*    @RequestMapping("/queryUserInfoById/{userId}")
    public UserInfoVo queryUserInfoById(@PathVariable("userId") Integer userId) {
        User user = userServiceImpl.queryUserById(userId);

        ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:8002/order/queryOrdersByUserId/"+userId,List.class);

        List<OrderVo> orderVoList = responseEntity.getBody();

        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setOrderVoList(orderVoList);
        userInfoVo.setUserName(user.getUserName());
        return userInfoVo;
    }*/

    @RequestMapping("/queryUserInfoById/{userId}")
    public UserInfoVo queryUserInfoById(@PathVariable("userId") Integer userId) {
        User user = userServiceImpl.queryUserById(userId);

        ResponseEntity<List> responseEntity = null;
        try {
            responseEntity = restTemplate.getForEntity("http://localhost:8002/order/queryOrdersByUserId/"+userId,List.class);
        } catch (Exception e) {
            throw new TulingTimeoutException(0,"调用超时");
        }

        List<OrderVo> orderVoList = responseEntity.getBody();

        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setOrderVoList(orderVoList);
        userInfoVo.setUserName(user.getUserName());
        return userInfoVo;
    }


}
