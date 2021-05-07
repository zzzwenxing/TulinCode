package com.tuling.busi.entity;

import lombok.Data;

import java.util.Date;

/**
 * 订单表
 * Created by smlz on 2019/7/25.
 */
@Data
public class OrderInfo {

    private String orderNo;

    private Integer userId;

    private Date createDate = new Date();

    private String productId;

    private String productNum;
}
