package com.tuling.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuling.bo.MsgTxtBo;
import com.tuling.compent.MsgSender;
import com.tuling.entity.OrderInfo;
import com.tuling.service.IOrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by smlz on 2019/10/11.
 */
@RestController
@Slf4j
public class OrderController {

    @Autowired
    private IOrderInfoService orderInfoService;

    @Autowired
    private MsgSender msgSender;

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

    /**
     * 订单重试生成接口
     * @return
     */
    @RequestMapping("/retryMsg")
    public String retryMsg( @RequestBody MsgTxtBo msgTxtBo) {

        log.info("消息重新发送:{}",msgTxtBo);

        //第一次发送消息
        msgSender.senderMsg(msgTxtBo);

        msgSender.senderDelayCheckMsg(msgTxtBo);

        return "ok";
    }
}
