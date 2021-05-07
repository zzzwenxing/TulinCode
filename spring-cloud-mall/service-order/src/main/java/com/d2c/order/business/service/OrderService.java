package com.d2c.order.business.service;

import com.d2c.order.business.model.Order;

public interface OrderService {

    Order findBySn(String sn);

    Order findCacheBySn(String sn);

    int doSomeThing(String sn, Long productId, Long memberId);

}
