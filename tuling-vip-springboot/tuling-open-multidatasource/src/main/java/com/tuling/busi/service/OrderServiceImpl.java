package com.tuling.busi.service;

import com.tuling.busi.bean.Order;
import com.tuling.busi.dao.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by smlz on 2019/4/17.
 */
@Service
public class OrderServiceImpl {

    @Autowired
    private OrderMapper orderMapper;

    public void insertOrder(Order order){
        orderMapper.insertOrder(order);
    }
}
