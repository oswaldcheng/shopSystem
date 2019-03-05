package com.shibeijie.demo.bean;

import java.io.Serializable;
import com.shibeijie.demo.utils.BaseBean;

/**
 * 
 * @Title: UserInfo.java 
 * @Description: 消费者类（用户类）
 * @author oswald
 * @version V1.0
 */
public class UserInfo extends BaseBean implements Serializable {
	
	//用户ID
	private Integer userId;
	
	//用户姓名
	private String userName;
	
	//用户性别
	private String userSex;
	
	//用户账号信息
	private String userPhone;
	
	//用户密码信息
	private String userPw;
	
	//用户类型
	private String userType;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName + ", userSex=" + userSex + ", userPhone="
				+ userPhone + ", userPw=" + userPw + ", userType=" + userType + "]";
	}
	
	
}