package com.it.tl.edu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author ：图灵-杨过
 * @date：2019/11/4
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class OrderPaymentDto {
    private long orderid;

    private BigDecimal paymoney;

    public OrderPaymentDto(){}

    public OrderPaymentDto(long orderid, BigDecimal paymoney) {
        this.orderid = orderid;
        this.paymoney = paymoney;
    }

    public long getOrderid() {
        return orderid;
    }

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    public BigDecimal getPaymoney() {
        return paymoney;
    }

    public void setPaymoney(BigDecimal paymoney) {
        this.paymoney = paymoney;
    }
}
