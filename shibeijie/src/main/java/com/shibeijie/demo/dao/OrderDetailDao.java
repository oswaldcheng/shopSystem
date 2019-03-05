package com.shibeijie.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.shibeijie.demo.bean.OrderDetail;

/**
 * @author 郭城
 *
 */
@Mapper
public interface OrderDetailDao {
	/**
	 * 添加订单明细
	 * @param od
	 * @return
	 */
	public void add(OrderDetail od);
}
