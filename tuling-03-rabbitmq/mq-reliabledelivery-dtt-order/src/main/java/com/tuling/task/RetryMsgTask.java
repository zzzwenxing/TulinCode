package com.tuling.task;

import com.tuling.bo.MsgTxtBo;
import com.tuling.compent.MsgSender;
import com.tuling.constants.MqConst;
import com.tuling.entity.MessageContent;
import com.tuling.enumuration.MsgStatusEnum;
import com.tuling.mapper.MsgContentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by smlz on 2019/10/11.
 */
@Component
@Slf4j
public class RetryMsgTask {

    @Autowired
    private MsgSender msgSender;

    @Autowired
    private MsgContentMapper msgContentMapper;

    /**
     * 延时5s启动
     * 周期10S一次
     */
    @Scheduled(initialDelay = 10000,fixedDelay = 10000)
    public void retrySend() {
        System.out.println("-----------------------------");
        //查询五分钟消息状态还没有完结的消息
        List<MessageContent> messageContentList = msgContentMapper.qryNeedRetryMsg(MsgStatusEnum.CONSUMER_SUCCESS.getCode(), MqConst.TIME_DIFF);

        for(MessageContent messageContent:messageContentList) {

            if(messageContent.getMaxRetry()>messageContent.getCurrentRetry()) {
                MsgTxtBo msgTxtBo = new MsgTxtBo();
                msgTxtBo.setMsgId(messageContent.getMsgId());
                msgTxtBo.setProductNo(messageContent.getProductNo());
                msgTxtBo.setOrderNo(messageContent.getOrderNo());
                //更新消息重试次数
                msgContentMapper.updateMsgRetryCount(msgTxtBo.getMsgId());
                msgSender.senderMsg(msgTxtBo);
            }else {
                log.warn("消息:{}以及达到最大重试次数",messageContent);
            }

        }
    }
}
