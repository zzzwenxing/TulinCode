package com.tuling.compent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.messaging.Message;
import org.springframework.web.client.RestTemplate;


/**
 * Created by smlz on 2019/4/29.
 */
@Component
@Slf4j
public class AutoRefresh {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private Integer port;

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue("auto.refresh.8003"),
                    exchange = @Exchange(value = "auto.refresh.topic",type = "topic"),key = "auto.refresh.#")
    })
    public void consumerTopicMsg(Message message) {
        log.info("端口:{},获取到刷新通知",port);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        restTemplate.postForEntity("http://localhost:"+port+"/actuator/refresh",requestEntity,Object.class);
    }
}
