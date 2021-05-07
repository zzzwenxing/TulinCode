package com.tuling.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.tuling.compent.TulingHystrixCommand;
import com.tuling.entity.OrderVo;
import com.tuling.entity.User;
import com.tuling.entity.UserInfoVo;
import com.tuling.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by smlz on 2019/4/12.
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * 通过hystrix命令模式 来进行调用
     * @param userId
     */
/*     @RequestMapping("/queryUserInfo/{userId}")
     public UserInfoVo queryUserInfo(@PathVariable("userId") Integer userId) {

     User user = userServiceImpl.queryUserById(userId);

     //构建调用命令模式
     TulingHystrixCommand tulingHystrixCommand = new TulingHystrixCommand("orderGroupKey",restTemplate,userId);
     List<OrderVo> orderVoList =tulingHystrixCommand.execute();

     UserInfoVo userInfoVo = new UserInfoVo();
     userInfoVo.setOrderVoList(orderVoList);
     userInfoVo.setUserName(user.getUserName());

     return userInfoVo;
     }*/



    /**
     * 通过命令来指定降级方法
     * @param userId 用户ID
     * @return
     */

    @HystrixCommand(fallbackMethod ="queryUserInfoFallBack")
    @RequestMapping("/queryUserInfo/{userId}")
    public UserInfoVo queryUserInfo(@PathVariable("userId") Integer userId) {
        log.info("进入queryUserInfo..............");
        //User user = userServiceImpl.queryUserById(userId);

        ResponseEntity<List> responseEntity  =  restTemplate.getForEntity("http://MS-PROVIDER-ORDER/order/queryOrdersByUserId/"+userId, List.class);
        List<OrderVo> orderVoList = responseEntity.getBody();

        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setOrderVoList(orderVoList);
        userInfoVo.setUserName("张三");


        return userInfoVo;
    }

    protected UserInfoVo queryUserInfoFallBack(Integer userId) {
        log.info("触发降级方法=根据用户ID{}查询订单服务异常:{}",userId);
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setOrderVoList(null);
        userInfoVo.setUserName("-1");
        return userInfoVo;
    }

    protected UserInfoVo defautlUserInfoFallBack(String  userName) {
        log.info("触发降级方法=根据用户ID{}查询订单服务异常:{}",userName);
        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setOrderVoList(null);
        userInfoVo.setUserName("-1");
        return userInfoVo;
    }

    @HystrixCommand(fallbackMethod ="defautlUserInfoFallBack",commandKey = "queryUserInfoByName")
    @RequestMapping("/queryUserInfoByName/{userName}")
    public UserInfoVo queryUserInfoByName(@PathVariable("userName") String userName) {
        log.info("queryUserInfoByName..............");
        User user = userServiceImpl.queryUserByName(userName);

        ResponseEntity<List> responseEntity  =  restTemplate.getForEntity("http://MS-PROVIDER-ORDER/order/queryOrdersByUserId/"+user.getUserId(), List.class);
        List<OrderVo> orderVoList = responseEntity.getBody();

        UserInfoVo userInfoVo = new UserInfoVo();
        userInfoVo.setOrderVoList(orderVoList);
        userInfoVo.setUserName(user.getUserName());

        return userInfoVo;
    }


}
