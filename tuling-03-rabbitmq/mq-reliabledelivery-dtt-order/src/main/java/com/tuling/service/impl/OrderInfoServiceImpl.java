package com.tuling.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tuling.bo.MsgTxtBo;
import com.tuling.compent.MsgSender;
import com.tuling.constants.MqConst;
import com.tuling.entity.MessageContent;
import com.tuling.entity.OrderInfo;
import com.tuling.enumuration.MsgStatusEnum;
import com.tuling.mapper.MsgContentMapper;
import com.tuling.mapper.OrderInfoMapper;
import com.tuling.service.IOrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述
* @author: smlz
* @createDate: 2019/10/11 15:29
* @version: 1.0
*/
@Slf4j
@Service
public class OrderInfoServiceImpl implements IOrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private MsgContentMapper msgContentMapper;

    @Autowired
    private MsgSender msgSender;

    @Transactional
    @Override
    public void saveOrderInfo(OrderInfo orderInfo,MessageContent messageContent) {

        try {
            orderInfoMapper.saveOrderInfo(orderInfo);

            //插入消息表
            msgContentMapper.saveMsgContent(messageContent);

        }catch (Exception e) {
            log.error("操作数据库失败:{}",e);
            throw new RuntimeException("操作数据库失败");
        }
    }

    public void saveOrderInfoWithMessage(OrderInfo orderInfo) throws JsonProcessingException {

        //构建消息对象
        MessageContent messageContent = builderMessageContent(orderInfo.getOrderNo(),orderInfo.getProductNo());

        //保存数据库
        saveOrderInfo(orderInfo,messageContent);

        //构建消息发送对象
        MsgTxtBo msgTxtBo = new MsgTxtBo();
        msgTxtBo.setMsgId(messageContent.getMsgId());
        msgTxtBo.setOrderNo(orderInfo.getOrderNo());
        msgTxtBo.setProductNo(orderInfo.getProductNo());

        //发送消息
        msgSender.senderMsg(msgTxtBo);
    }



    /**
     * 方法实现说明:构建消息对象
     * @author:smlz
     * @return:MessageContent 消息实体
     * @exception:
     * @date:2019/10/11 17:24
     */
    private MessageContent builderMessageContent(long orderNo,Integer productNo) {
        MessageContent messageContent = new MessageContent();
        String msgId = UUID.randomUUID().toString();
        messageContent.setMsgId(msgId);
        messageContent.setCreateTime(new Date());
        messageContent.setUpdateTime(new Date());
        messageContent.setExchange(MqConst.ORDER_TO_PRODUCT_EXCHANGE_NAME);
        messageContent.setRoutingKey(MqConst.ORDER_TO_PRODUCT_QUEUE_NAME);
        messageContent.setMsgStatus(MsgStatusEnum.SENDING.getCode());
        messageContent.setOrderNo(orderNo);
        messageContent.setProductNo(productNo);
        messageContent.setMaxRetry(MqConst.MSG_RETRY_COUNT);
        return messageContent;
    }
}
