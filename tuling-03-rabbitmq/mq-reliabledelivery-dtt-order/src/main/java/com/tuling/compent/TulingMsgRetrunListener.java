package com.tuling.compent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuling.bo.MsgTxtBo;
import com.tuling.entity.MessageContent;
import com.tuling.enumuration.MsgStatusEnum;
import com.tuling.mapper.MsgContentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:消息不可达监听
* @author: smlz
* @createDate: 2019/10/11 21:18
* @version: 1.0
*/
@Component
@Slf4j
public class TulingMsgRetrunListener implements RabbitTemplate.ReturnCallback {

    @Autowired
    private MsgContentMapper msgContentMapper;

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            MsgTxtBo msgTxtBo = objectMapper.readValue(message.getBody(),MsgTxtBo.class);
            log.info("无法路由消息内容:{},cause:{}",msgTxtBo,replyText);

            //构建消息对象
            MessageContent messageContent = new MessageContent();
            messageContent.setErrCause(replyText);
            messageContent.setUpdateTime(new Date());
            messageContent.setMsgStatus(MsgStatusEnum.SENDING_FAIL.getCode());
            messageContent.setMsgId(msgTxtBo.getMsgId());
            //更新消息表
            msgContentMapper.updateMsgStatus(messageContent);
        }catch (Exception e) {
            log.error("更新消息表异常:{}",e);
        }
    }
}
