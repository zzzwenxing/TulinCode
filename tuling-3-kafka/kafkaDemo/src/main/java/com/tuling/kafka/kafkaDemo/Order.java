package com.tuling.kafka.kafkaDemo;

public class Order {

    private Integer orderId;
    private Integer productId;
    private Integer productNum;
    private Double orderAmount;

    public Order() {
    }

    public Order(Integer orderId, Integer productId, Integer productNum, Double orderAmount) {
        super();
        this.orderId = orderId;
        this.productId = productId;
        this.productNum = productNum;
        this.orderAmount = orderAmount;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }
}
