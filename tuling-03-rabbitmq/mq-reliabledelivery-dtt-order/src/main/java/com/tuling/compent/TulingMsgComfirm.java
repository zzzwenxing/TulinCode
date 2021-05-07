package com.tuling.compent;

import com.tuling.entity.MessageContent;
import com.tuling.enumuration.MsgStatusEnum;
import com.tuling.mapper.MsgContentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:消息确认组件
* @author: smlz
* @createDate: 2019/10/11 18:00
* @version: 1.0
*/
@Component
@Slf4j
public class TulingMsgComfirm implements RabbitTemplate.ConfirmCallback{

    @Autowired
    private MsgContentMapper msgContentMapper;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String msgId = correlationData.getId();

        if(ack) {
            log.info("消息Id:{}对应的消息被broker签收成功",msgId);
            updateMsgStatusWithAck(msgId);
        }else{
            log.warn("消息Id:{}对应的消息被broker签收失败:{}",msgId,cause);
            updateMsgStatusWithNack(msgId,cause);
        }
    }

    /**
     * 方法实现说明:更新消息表状态为
     * @author:smlz
     * @param msgId:消息ID
     * @exception:
     * @date:2019/10/11 18:01
     */
    private void updateMsgStatusWithAck(String msgId) {
        MessageContent messageContent = builderUpdateContent(msgId);
        messageContent.setMsgStatus(MsgStatusEnum.SENDING_SUCCESS.getCode());
        msgContentMapper.updateMsgStatus(messageContent);
    }

    private void updateMsgStatusWithNack(String msgId,String cause){

        MessageContent messageContent = builderUpdateContent(msgId);

        messageContent.setMsgStatus(MsgStatusEnum.SENDING_FAIL.getCode());
        messageContent.setErrCause(cause);
        msgContentMapper.updateMsgStatus(messageContent);
    }

    private MessageContent builderUpdateContent(String msgId) {
        MessageContent messageContent = new MessageContent();
        messageContent.setMsgId(msgId);
        messageContent.setUpdateTime(new Date());
        return messageContent;
    }

}
