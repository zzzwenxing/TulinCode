package com.tuling.compent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.tuling.bo.MsgTxtBo;
import com.tuling.entity.MessageContent;
import com.tuling.enumration.MsgStatusEnum;
import com.tuling.exception.BizExp;
import com.tuling.mapper.MsgContentMapper;
import com.tuling.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * Created by smlz on 2019/10/13.
 */
@Component
@Slf4j
public class MqConsumer {

    /**队列名称*/
    public static final String ORDER_TO_PRODUCT_QUEUE_NAME = "order-to-product.queue";

    public static final String LOCK_KEY="LOCK_KEY";

    @Autowired
    private IProductService productService;

    @Autowired
    private MsgContentMapper msgContentMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 没有加分布式锁的版本,可能存在重复消费问题
     * @param message
     * @param channel
     * @throws IOException

    @RabbitListener(queues = {ORDER_TO_PRODUCT_QUEUE_NAME})
    @RabbitHandler
    public void consumerMsg(Message message, Channel channel) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        MsgTxtBo msgTxtBo = objectMapper.readValue(message.getBody(),MsgTxtBo.class);

        log.info("消费消息:{}",msgTxtBo);
        Long deliveryTag = (Long) message.getMessageProperties().getDeliveryTag();

        try {
            //更新消息表也业务表
            productService.updateProductStore(msgTxtBo);

            System.out.println(1/0);
            //消息签收
            channel.basicAck(deliveryTag,false);
        }catch (Exception e) {
            //更新msg表为消费失败
            //更新消息表状态
            MessageContent messageContent = new MessageContent();
            messageContent.setMsgId(msgTxtBo.getMsgId());
            messageContent.setUpdateTime(new Date());
            messageContent.setMsgStatus(MsgStatusEnum.CONSUMER_FAIL.getCode());
            msgContentMapper.updateMsgStatus(messageContent);

            channel.basicReject(deliveryTag,false);
        }
    }
     */

    @RabbitListener(queues = {ORDER_TO_PRODUCT_QUEUE_NAME})
    @RabbitHandler
    public void consumerMsgWithLock(Message message, Channel channel) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        MsgTxtBo msgTxtBo = objectMapper.readValue(message.getBody(), MsgTxtBo.class);
        Long deliveryTag = (Long) message.getMessageProperties().getDeliveryTag();

        if (redisTemplate.opsForValue().setIfAbsent(LOCK_KEY + msgTxtBo.getMsgId(), msgTxtBo.getMsgId())) {
            log.info("消费消息:{}", msgTxtBo);
            try {
                //更新消息表也业务表
                productService.updateProductStore(msgTxtBo);
                //消息签收
                System.out.println(1/0);
                channel.basicAck(deliveryTag, false);
            } catch (Exception e) {
                /**
                 * 更新数据库异常说明业务没有操作成功需要删除分布式锁
                 */
                               if (e instanceof BizExp) {
                    BizExp bizExp = (BizExp) e;
                    log.info("数据业务异常:{},即将删除分布式锁", bizExp.getErrMsg());
                    //删除分布式锁
                    redisTemplate.delete(LOCK_KEY);
                }

                //更新消息表状态
                MessageContent messageContent = new MessageContent();
                messageContent.setMsgStatus(MsgStatusEnum.CONSUMER_FAIL.getCode());
                messageContent.setUpdateTime(new Date());
                messageContent.setErrCause(e.getMessage());
                messageContent.setMsgId(msgTxtBo.getMsgId());
                msgContentMapper.updateMsgStatus(messageContent);
                channel.basicReject(deliveryTag,false);
            }

        } else {
            log.warn("请不要重复消费消息{}", msgTxtBo);
            channel.basicReject(deliveryTag,false);
        }

    }
}
