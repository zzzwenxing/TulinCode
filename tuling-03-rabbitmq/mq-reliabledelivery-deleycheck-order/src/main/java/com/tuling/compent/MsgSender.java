package com.tuling.compent;

import com.tuling.bo.MsgTxtBo;
import com.tuling.constants.MqConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:消息发送组件
* @author: smlz
* @createDate: 2019/10/11 17:28
* @version: 1.0
*/
@Component
@Slf4j
public class MsgSender implements InitializingBean {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TulingMsgComfirmListener tulingMsgComfirmListener;


    /**
     * 方法实现说明:真正的发送消息
     * @author:smlz
     * @param msgTxtBo:发送的消息对象
     * @return:
     * @exception:
     * @date:2019/10/11 20:01
     */
    public  void senderMsg(MsgTxtBo msgTxtBo){


        log.info("发送的消息ID:{}",msgTxtBo.getOrderNo());

        CorrelationData correlationData = new CorrelationData(msgTxtBo.getMsgId()+"_"+msgTxtBo.getOrderNo());

        rabbitTemplate.convertAndSend(MqConst.ORDER_TO_PRODUCT_EXCHANGE_NAME,MqConst.ORDER_TO_PRODUCT_ROUTING_KEY,msgTxtBo,correlationData);
    }

    /**
     * 发送延时消息
     * @param msgTxtBo
     */
    public void senderDelayCheckMsg(MsgTxtBo msgTxtBo) {

        log.info("发送的消息ID:{}",msgTxtBo.getOrderNo());
        //表示为延时消息
        CorrelationData correlationData = new CorrelationData(msgTxtBo.getMsgId()+"_"+msgTxtBo.getOrderNo()+"_delay");
        rabbitTemplate.convertAndSend(MqConst.ORDER_TO_PRODUCT_DELAY_EXCHANGE_NAME, MqConst.ORDER_TO_PRODUCT_DELAY_ROUTING_KEY, msgTxtBo, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", MqConst.DELAY_TIME);//设置延迟时间
                return message;
            }
        },correlationData);

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        rabbitTemplate.setConfirmCallback(tulingMsgComfirmListener);
        //设置消息转换器
        Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
    }
}
