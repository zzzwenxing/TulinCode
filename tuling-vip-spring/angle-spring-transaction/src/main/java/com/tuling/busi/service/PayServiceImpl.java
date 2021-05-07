package com.tuling.busi.service;

import com.tuling.busi.dao.AccountInfoDao;
import com.tuling.busi.dao.ProductInfoDao;
import com.tuling.plugins.anno.AngleTransactionl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by smlz on 2019/6/17.
 */
@Component

public class PayServiceImpl implements PayService {

    @Autowired
    private AccountInfoDao accountInfoDao;

    @Autowired
    private ProductInfoDao productInfoDao;


    @AngleTransactionl
    public void pay(String accountId, double money) {
        //查询余额
        double blance = accountInfoDao.qryBlanceByUserId(accountId);

        //余额不足正常逻辑
        if(new BigDecimal(blance).compareTo(new BigDecimal(money))<0) {
            throw new RuntimeException("余额不足");
        }

        System.out.println(1/0);

        //更新余额
        int retVal = accountInfoDao.updateAccountBlance(accountId,money);
    }



}
