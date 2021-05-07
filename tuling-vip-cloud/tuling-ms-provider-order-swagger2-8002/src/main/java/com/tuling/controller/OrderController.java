package com.tuling.controller;

import com.tuling.entity.OrderVo;
import com.tuling.service.OrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by smlz on 2019/3/26.
 */
@Controller
@RequestMapping("/order")
@Slf4j
@Api(value ="订单管理接口",description = "订单接口服务")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    private ServiceInstance serviceInstance;

    @RequestMapping("/queryOrdersByUserId/{userId}")
    @ResponseBody
    @ApiOperation(value ="根据用户ID查询订单信息",notes="根据url的用户ID来获取订单详细信息")
    @ApiImplicitParam(name="userId",value = "用户ID",required = true,dataType ="Integer",paramType = "path")
    public List<OrderVo> queryOrdersByUserId(@PathVariable("userId") Integer userId) throws InterruptedException {
        log.info("测试降级。。。。。。。");

        //超时降级
        //Thread.sleep(3000);
        if(userId==2) {
            //throw new HystrixRuntimeException(HystrixRuntimeException.FailureType.BAD_REQUEST_EXCEPTION,null,"错误信息",null,null);
            throw new RuntimeException("用户不存在");
        }

        List<OrderVo> list = new ArrayList<>();
        OrderVo orderVo = new OrderVo();
        orderVo.setUserId(1);
        orderVo.setOrderId(1);
        orderVo.setOrderMoney(new BigDecimal(200));
        orderVo.setDbSource("tuling_source01");
        list.add(orderVo);
        return list;

        //return orderServiceImpl.queryOrdersByUserId(userId);
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String queryAdmin() {
        return "OK";
    }

    @RequestMapping("/getCookie")
    @ResponseBody
    public String testGetCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies) {
            System.out.println(cookie.getName()+"-"+cookie.getValue());
        }
        System.out.println(cookies);
        return "OK";
    }

    @RequestMapping("/testRedirect")
    public String testRedirect() {
        return "redirect:/order/queryAll";
    }

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<OrderVo> queryAll() throws InterruptedException {

        //超时降级
        //Thread.sleep(4000);
/*        if(true) {
            throw new RuntimeException("不存在的用户");
        }*/

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
    @ResponseBody
    public String info() {
        return serviceInstance.getHost()+":"+serviceInstance.getPort();
    }
}
