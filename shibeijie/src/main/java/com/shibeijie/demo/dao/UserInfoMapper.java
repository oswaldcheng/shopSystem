package com.shibeijie.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shibeijie.demo.bean.UserInfo;

/**
 * 用户信息管理核心DAO
 * @author 郭城
 *
 */
@Mapper
public interface UserInfoMapper {
	
	/**
	 * 添加用户信息
	 ** @author 郭城
	 * @param userInfo
	 */
	public void insertUserInfo(UserInfo userInfo);

	
	/**
	 * 根据查询条件分页查询用户信息
	 ** @author 郭城
	 * @param userInfo
	 * @return
	 */
	public List<UserInfo> getUserInfoList(UserInfo userInfo);
	
	
	/**
	 * 查询满足条件的用户数量
	 ** @author 郭城
	 * @param userInfo
	 * @return
	 */
	public Long getUserInfoCount(UserInfo userInfo);
	
	
	/**
	 * 添加用户
	 ** @author 郭城
	 * @param userInfo
	 */
	public void addUserInfo(UserInfo userInfo);
	
	
	/**
	 * 根据条件查询用户信息
	 ** @author 郭城
	 * @param userInfo
	 * @return
	 */
	public UserInfo getUserInfo(UserInfo userInfo);
	
	
	
	/**
	 * 修改用户信息
	 ** @author 郭城
	 * @param userInfo
	 */
	public void updateUserInfo(UserInfo userInfo);
	
	
	/**
	 * 删除用户信息
	 ** @author 郭城
	 * @param userInfo
	 */
	public void deleteUserInfo(UserInfo userInfo);
	
}
