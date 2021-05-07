package com.tuling.busi.entity;

import lombok.Data;

/**
 * 商品库存
 * Created by smlz on 2019/7/25.
 */
@Data
public class ProductStockInfo {

    private String productId;

    private String productName;

    private Integer stockNum;
}
