package com.tuling.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by smlz on 2019/10/9.
 */
public class Order implements Serializable {


    @Override
    public String toString() {
        return "Order{" +
                "orderNo='" + orderNo + '\'' +
                ", createDt=" + createDt +
                ", payMoney=" + payMoney +
                ", userName='" + userName + '\'' +
                '}';
    }

    private String orderNo;

    private Date createDt;

    private double payMoney;

    private String userName;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreateDt() {
        return createDt;
    }

    public void setCreateDt(Date createDt) {
        this.createDt = createDt;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
