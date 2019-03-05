package com.shibeijie.demo.bean;

/**
 * 订单类
 * @author 郭城
 *
 */
public class OrderManagement {

	//订单ID
	private Integer orderId;
	//用户ID
	private Integer userId;
	
	private Integer consigneeId;
	
	private String orderTime;
	
	private String orderTotal;
	
	public String getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(String orderTotal) {
		this.orderTotal = orderTotal;
	}

	private String orderState;
	
	
	

	@Override
	public String toString() {
		return "OrderManagement [orderId=" + orderId + ", userId=" + userId + ", consigneeId=" + consigneeId
				+ ", orderTime=" + orderTime + ", orderTotal=" + orderTotal + ", orderState=" + orderState + "]";
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getConsigneeId() {
		return consigneeId;
	}

	public void setConsigneeId(Integer consigneeId) {
		this.consigneeId = consigneeId;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	
	
	
}

