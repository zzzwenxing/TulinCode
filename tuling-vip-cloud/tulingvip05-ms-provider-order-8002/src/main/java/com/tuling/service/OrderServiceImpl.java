package com.tuling.service;

import com.netflix.discovery.converters.Auto;
import com.tuling.config.DruidDataSourceProperties;
import com.tuling.dao.OrderDao;
import com.tuling.entity.Order;
import com.tuling.entity.OrderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smlz on 2019/3/26.
 */
@Service
public class OrderServiceImpl {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private DruidDataSourceProperties druidDataSourceProperties;

    public List<OrderVo> queryOrdersByUserId(Integer userId) {
        System.out.println("数据库连接:"+druidDataSourceProperties.getJdbcUrl());
        List<Order> orderList = orderDao.queryOrdersByUserId(userId);
        List<OrderVo> orderVoList = new ArrayList<>();
        for(Order order:orderList) {
            OrderVo orderVo = new OrderVo();
            BeanUtils.copyProperties(order,orderVo);
            orderVoList.add(orderVo);
        }
        return orderVoList;
    }
}
