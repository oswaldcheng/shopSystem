package com.shibeijie.demo.service;

import java.util.List;

import com.shibeijie.demo.bean.OrderDetail;
import com.shibeijie.demo.bean.OrderManagement;

/**
 * @author 郭城
 *
 */
public interface OrderManagementService {
	public void add(OrderManagement om,List<OrderDetail> list) throws Exception;
}
