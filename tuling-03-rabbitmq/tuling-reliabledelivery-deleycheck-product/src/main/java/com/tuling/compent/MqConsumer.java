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

    public static final String LOCK_KEY = "lock.key";

    @Autowired
    private IProductService productService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MsgSender msgSender;


    /**
     * 没有加分布式锁的版本,可能存在重复消费问题
     * @param message
     * @param channel
     * @throws IOException
     */
    /*@RabbitListener(queues = {ORDER_TO_PRODUCT_QUEUE_NAME})
    @RabbitHandler
    public void consumerMsg(Message message, Channel channel) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        MsgTxtBo msgTxtBo = objectMapper.readValue(message.getBody(),MsgTxtBo.class);

        log.info("库存服务product------>消费消息:{}",msgTxtBo);
        Long deliveryTag = (Long) message.getMessageProperties().getDeliveryTag();

        try {
            //更新消息表也业务表
            productService.updateProductStore(msgTxtBo);

            //消息签收
            channel.basicAck(deliveryTag,false);

            //发送一条确认消费消息到callback服务上
            //msgSender.senderMsg(msgTxtBo);

        }catch (Exception e) {
            if(e instanceof BizExp) {
                channel.basicReject(deliveryTag,false);
            }
            log.info("异常:{}",e.getMessage());
        }
    }*/

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

                //发送一条确认消费消息到callback服务上
                //msgSender.senderMsg(msgTxtBo);

                //消息签收
                channel.basicAck(deliveryTag,false);
            } catch (Exception e) {

                if (e instanceof BizExp) {
                    BizExp bizExp = (BizExp) e;
                    log.info("数据业务异常:{},即将删除分布式锁", bizExp.getErrMsg());
                    //删除分布式锁
                    redisTemplate.delete(LOCK_KEY);
                }

            }

        } else {
            log.warn("请不要重复消费消息{}", msgTxtBo);
            channel.basicReject(deliveryTag,false);
        }

    }
}
