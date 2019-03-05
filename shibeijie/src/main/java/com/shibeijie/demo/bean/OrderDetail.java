package com.shibeijie.demo.bean;

/**
 * 订单明细
 * @author 郭城
 *
 */
public class OrderDetail {

	/**
	 * 订单明细ID
	 */
	private Integer orderDetailId;
	//订单ID
	private Integer orderId;
	//商品ID
	private Integer goodsId;
	//数量
	private Integer ordeNumber;
	//
	private Double everyTotal;
	
	
	/**
	 * 商品价格
	 */
	private String orderPrice;
	//商品名
	public String goodsName;
	//商品url
	private String goodsUrl;
	
	

	public Double getEveryTotal() {
		return everyTotal;
	}

	public void setEveryTotal(Double everyTotal) {
		this.everyTotal = everyTotal;
	}


	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsUrl() {
		return goodsUrl;
	}

	public void setGoodsUrl(String goodsUrl) {
		this.goodsUrl = goodsUrl;
	}

	public Integer getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getOrdeNumber() {
		return ordeNumber;
	}

	public void setOrdeNumber(Integer ordeNumber) {
		this.ordeNumber = ordeNumber;
	}

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

}

