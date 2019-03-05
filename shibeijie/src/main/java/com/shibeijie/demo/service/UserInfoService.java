package com.shibeijie.demo.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shibeijie.demo.bean.UserInfo;
import com.shibeijie.demo.utils.PageBean;



/**
 * 用户管理业务接口
 * @author 郭城
 *
 */
public interface UserInfoService {
	
	/**
	 * 插入用户信息
	 ** @author 郭城
	 * @param userInfo
	 */
	public void insertUserInfo(UserInfo userInfo);
	
	

	/**
	 * 根据查询条件分页查询用户信息
	 ** @author 郭城
	 * @param userInfo
	 * @param page
	 * @return
	 */
	public PageBean<UserInfo> getUserInfoList(UserInfo userInfo,Integer page);
	
	/**
	 * 查询满足条件的用户数量
	 ** @author 郭城
	 * @param userInfo
	 * @return
	 */
	public Long getUserInfoCount(UserInfo userInfo);
	
	/**
	 * 解析excel文件，批量添加用户。注:只能解析.xls格式
	 ** @author 郭城
	 * @param users
	 * @throws Exception
	 */
	public void addUsers(MultipartFile users) throws Exception;
	
	/**
	 *生成待下载的excel文件 ,.xls格式
	 ** @author 郭城
	 * @param users
	 * @return
	 * @throws Exception
	 */
	public File createDownloadFile(List<UserInfo> users) throws Exception;
	
	/**
	 * 添加用户信息
	 ** @author 郭城
	 * @param userInfo
	 * @throws Exception
	 */
	public void addUserInfo(UserInfo userInfo) throws Exception;
	

	/**
	 * 检验账号是否可以使用
	 ** @author 郭城
	 * @param userInfo
	 * @return true 可用 false 不可用
	 */
	public boolean validatePhone(UserInfo userInfo);
	
	/**
	 * 加载待修改的用户信息
	 * @param userInfo
	 * @return
	 */
	public UserInfo loadUpdateUserInfo(UserInfo userInfo);
	
	/**
	 * 修改用户信息
	 * @param userInfo
	 */
	public void updateUserInfo(UserInfo userInfo) throws Exception;
	
	/**
	 * 删除用户信息
	 * @param userInfo
	 */
	public void deleteUserInfo(UserInfo userInfo) throws Exception;
	
	/**
	 * 用户登陆
	 * @param userInfo
	 * @return
	 */
	public UserInfo doLogin(UserInfo userInfo) ;
}
