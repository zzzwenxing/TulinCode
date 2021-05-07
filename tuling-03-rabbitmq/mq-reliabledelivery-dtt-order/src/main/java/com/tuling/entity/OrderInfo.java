package com.tuling.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
* @vlog: 高于生活，源于生活
* @desc: 类的描述:订单实体
* @author: smlz
* @createDate: 2019/10/11 15:01
* @version: 1.0
*/
@ToString
@Getter
@Setter
public class OrderInfo {

    private long orderNo;

    private Date createTime;

    private Date updateTime;

    private String userName;

    private double money;

    private Integer productNo;
}
