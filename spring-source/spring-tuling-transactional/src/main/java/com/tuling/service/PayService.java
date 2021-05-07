package com.tuling.service;


import org.springframework.transaction.annotation.Transactional;

/**
 * Created by smlz on 2019/6/17.
 */


public interface PayService {


    void pay(String accountId, double money);

    void updateProductStore(Integer productId);
}
