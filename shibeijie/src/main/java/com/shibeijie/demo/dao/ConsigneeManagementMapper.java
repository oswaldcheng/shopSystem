package com.shibeijie.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shibeijie.demo.bean.ConsigneeManagement;

/**
 * @author 郭城
 *
 */
@Mapper
public interface ConsigneeManagementMapper {
	/**
	 * 查询收货人信息
	 * @param cm
	 * @return
	 */
	public List<ConsigneeManagement> getCMList(ConsigneeManagement cm);
}

