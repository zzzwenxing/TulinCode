package com.d2c.order.business.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.NonNull;

public class Order implements Serializable {

    Long id;
    @NonNull
    String sn;
    BigDecimal payAmount;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
    
}
