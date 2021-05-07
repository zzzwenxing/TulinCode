package com.tuling.comfirm_listener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;

import java.io.IOException;

/**
 * Created by smlz on 2019/9/29.
 */
public class TulingConfirmListener implements ConfirmListener {

    /**
     *
     * @param deliveryTag 唯一消息Id
     * @param multiple:是否批量
     * @throws IOException
     */
    @Override
    public void handleAck(long deliveryTag, boolean multiple) throws IOException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前时间:"+System.currentTimeMillis()+"TulingConfirmListener handleAck:"+deliveryTag);
    }

    /**
     * 处理异常
     * @param deliveryTag
     * @param multiple
     * @throws IOException
     */
    @Override
    public void handleNack(long deliveryTag, boolean multiple) throws IOException {
        System.out.println("TulingConfirmListener handleNack:"+deliveryTag);

    }
}
