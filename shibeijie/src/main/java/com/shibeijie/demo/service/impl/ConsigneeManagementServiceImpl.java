package com.shibeijie.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shibeijie.demo.bean.ConsigneeManagement;
import com.shibeijie.demo.dao.ConsigneeManagementMapper;
import com.shibeijie.demo.service.ConsigneeManagementService;

/**
 * @author 郭城
 *
 */
@Service
public class ConsigneeManagementServiceImpl implements ConsigneeManagementService{
	
	@Autowired
	private ConsigneeManagementMapper cmMapper;
	
	@Override
	public List<ConsigneeManagement> getCmList(ConsigneeManagement cm) {
		return cmMapper.getCMList(cm);
	}

}
