package com.d2c.product.business.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.NonNull;

public class Product implements Serializable {

    Long id;
    @NonNull
    String sn;
    BigDecimal price;
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
