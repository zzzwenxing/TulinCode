package com.tuling.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuling.entity.MessageContent;
import com.tuling.entity.OrderInfo;
import org.springframework.core.annotation.Order;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:订单服务业务逻辑类
* @author: smlz
* @createDate: 2019/10/11 15:05
* @version: 1.0
*/
public interface IOrderInfoService {

    /**
     * 方法实现说明:订单保存
     * @author:smlz
     * @param orderInfo:订单实体
     * @return: int 插入的条数
     * @date:2019/10/11 15:04
     */
    void saveOrderInfo(OrderInfo orderInfo, MessageContent messageContent);

    void saveOrderInfoWithMessage(OrderInfo orderInfo) throws JsonProcessingException;
}
