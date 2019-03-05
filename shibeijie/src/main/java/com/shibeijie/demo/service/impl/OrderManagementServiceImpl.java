package com.shibeijie.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shibeijie.demo.bean.OrderDetail;
import com.shibeijie.demo.bean.OrderManagement;
import com.shibeijie.demo.dao.OrderDetailDao;
import com.shibeijie.demo.dao.OrderManagementDao;
import com.shibeijie.demo.service.OrderManagementService;

/**
 * @author 郭城
 *
 */
@Service
public class OrderManagementServiceImpl implements OrderManagementService{
	@Autowired
	private OrderManagementDao omDao;
	@Autowired
	private OrderDetailDao odDao;
	@Override
	public void add(OrderManagement om, List<OrderDetail> list) throws Exception{
		omDao.addOrder(om);
		for(OrderDetail od : list){
			od.setOrderId(om.getOrderId());
			odDao.add(od);
		}
	}
}

