package com.d2c.product.business.service;

import java.math.BigDecimal;

import com.d2c.product.business.model.Product;

public interface ProductService {

    Product findBySn(String sn);

    Product findCacheBySn(String sn);

    int updatePriceById(Long id, BigDecimal price);

}
