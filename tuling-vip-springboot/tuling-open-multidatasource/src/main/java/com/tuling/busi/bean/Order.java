package com.tuling.busi.bean;

import lombok.Data;

/**
 * Created by smlz on 2019/4/17.
 */
@Data
public class Order extends BaseDomin {

    private Long orderId;

    private String userId;

    private double money;


}
