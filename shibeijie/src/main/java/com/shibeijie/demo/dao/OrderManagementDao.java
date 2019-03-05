package com.shibeijie.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.shibeijie.demo.bean.OrderManagement;

/**
 * @author 郭城
 *
 */
@Mapper
public interface OrderManagementDao {
	/**
	 * 
	 * @param om
	 */
	public int addOrder(OrderManagement om);
}
