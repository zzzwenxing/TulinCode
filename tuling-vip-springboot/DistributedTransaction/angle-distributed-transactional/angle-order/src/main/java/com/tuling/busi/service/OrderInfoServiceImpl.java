package com.tuling.busi.service;

import com.tuling.busi.entity.OrderInfo;
import com.tuling.busi.mapper.OrderInfoMapper;
import com.tuling.support.anno.AngleTransactional;
import com.tuling.support.enumaration.TransactionalTypeEunm;
import com.tuling.support.holder.AngleTransactionalHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * Created by smlz on 2019/7/25.
 */
@Service
@Slf4j
public class OrderInfoServiceImpl implements IOrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    private static final String url = "http://localhost:8088/product/reduceById/";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Transactional
    @AngleTransactional(transType = TransactionalTypeEunm.BEGIN)
    public void saveOrder(OrderInfo order) {
        log.info("执行目标方法");
        orderInfoMapper.saveOrder(order);

        //构建请求头
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("globalTransactionId", AngleTransactionalHolder.get());
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        //发送请求
        ResponseEntity<String> response = restTemplate.exchange(url+order.getProductId(), HttpMethod.GET, requestEntity, String.class);

        if("error".equals(response.getBody())) {
            throw new RuntimeException("调用远程服务异常"+url+order.getProductId());
        }

        System.out.println(1/0);
    }
}
