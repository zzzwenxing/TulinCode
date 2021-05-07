package com.tuling.service;

import com.tuling.mapper.ProductInfoMapper;
import com.tuling.support.anno.AngleTransactional;
import com.tuling.support.enumaration.TransactionalTypeEunm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by smlz on 2019/7/28.
 */
@Service
public class ProductStockServiceImpl implements IProductStockInfoService {

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    @Transactional
    @AngleTransactional(transType = TransactionalTypeEunm.END)
    public void updateProductStock(String productId) {
        productInfoMapper.updateProductInfo(productId);
    }
}
