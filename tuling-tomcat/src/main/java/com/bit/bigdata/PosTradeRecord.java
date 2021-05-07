package com.bit.bigdata;

import java.util.List;

public class PosTradeRecord implements java.io.Serializable {
	private String orderId; // 交易编号
	private List<TradeProduct> products;// 商品列表
	private Long orderMoney;// 订单金额(分)
	private Long couponMoney;// 优惠金额
	private long tradeDate;// 交易时间
	private long posNumber;// pos 机编号
	private String shopName;// 店铺名称

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<TradeProduct> getProducts() {
		return products;
	}

	public void setProducts(List<TradeProduct> products) {
		this.products = products;
	}

	public Long getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(Long orderMoney) {
		this.orderMoney = orderMoney;
	}

	public Long getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(Long couponMoney) {
		this.couponMoney = couponMoney;
	}

	public long getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(long tradeDate) {
		this.tradeDate = tradeDate;
	}

	public long getPosNumber() {
		return posNumber;
	}

	public void setPosNumber(long posNumber) {
		this.posNumber = posNumber;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

}
