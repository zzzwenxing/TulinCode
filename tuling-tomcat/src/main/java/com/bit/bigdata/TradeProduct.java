package com.bit.bigdata;

import java.util.Date;

public class TradeProduct implements java.io.Serializable {
	private int productId; // 货品ID
	private String productName;// 货品名称
	private int size; // 货品数量
	private int price  ;// 单价
	private Date currentTime=new Date();
	
	
	
	public TradeProduct(int productId, String productName, int size, int price) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.size = size;
		this.price = price;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}
	
}
