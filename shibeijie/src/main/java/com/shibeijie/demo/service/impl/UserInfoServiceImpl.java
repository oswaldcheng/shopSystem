package com.shibeijie.demo.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shibeijie.demo.bean.UserInfo;
import com.shibeijie.demo.dao.UserInfoMapper;
import com.shibeijie.demo.service.UserInfoService;
import com.shibeijie.demo.utils.Const;
import com.shibeijie.demo.utils.PageBean;
import com.shibeijie.demo.utils.PageUtil;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Service
public class UserInfoServiceImpl implements UserInfoService{
	

	
	@Autowired
	private UserInfoMapper userInfoMapper;



	@Override
	public void insertUserInfo(UserInfo userInfo) {
		userInfoMapper.insertUserInfo(userInfo);
	}
	
	@Cacheable(cacheNames="sbj",key="#p1")
	@Override
	public PageBean<UserInfo> getUserInfoList(UserInfo userInfo, Integer page) {
		
		int allRow = getUserInfoCount(userInfo).intValue();//总记录数
		int totalPage = PageUtil.countTotalPage(allRow, Const.PAGE_SIZE);//总页数
		int currentPage = PageUtil.countCurrentPage(page);//当前第几页
		int start = PageUtil.countStart(Const.PAGE_SIZE, currentPage);//起始记录数
		if(page >= 0){
			userInfo.setStart(start);//起始记录数
			userInfo.setLength(Const.PAGE_SIZE);//每页显示的记录数
		}else{
			userInfo.setStart(-1);//不分页
			userInfo.setLength(-1);//不分页
		}
		List<UserInfo> users = userInfoMapper.getUserInfoList(userInfo);//记录集合
		
		PageBean<UserInfo> pageBean = new PageBean<>();
		pageBean.setAllRow(allRow);
		pageBean.setCurrentPage(currentPage);
		pageBean.setList(users);
		pageBean.setPageSize(Const.PAGE_SIZE);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	@Override
	public Long getUserInfoCount(UserInfo userInfo) {
		return userInfoMapper.getUserInfoCount(userInfo);
	}

	@Override
	public void addUsers(MultipartFile users) throws Exception{
		//从文件输入流中获得excel文件
		Workbook book = Workbook.getWorkbook(users.getInputStream());
		//获得第0个sheet
		Sheet sheet = book.getSheet(0);
		System.out.println("rows : " + sheet.getRows());
		System.out.println("cols : " + sheet.getColumns());
		for(int i = 1; i < sheet.getRows() ; i++){//每一行，舍弃表头
			UserInfo userInfo = new UserInfo();
			
			//姓名
			Cell nameCell = sheet.getCell(0, i);//列，行
			String userName = nameCell.getContents();
			userInfo.setUserName(userName);
			
			//性别
			Cell sexCell = sheet.getCell(1, i);//列，行
			String userSex = sexCell.getContents();
			userInfo.setUserSex(userSex);
			
			//电话
			Cell phoneCell = sheet.getCell(2, i);//列，行
			String userPhone = phoneCell.getContents();
			userInfo.setUserPhone(userPhone);
			
			//密码
			Cell pwCell = sheet.getCell(3, i);//列，行
			String userPw = pwCell.getContents();
			userInfo.setUserPw(userPw);
			
			//类型
			Cell typeCell = sheet.getCell(4, i);//列，行
			String userType = typeCell.getContents();
			userInfo.setUserType(userType);
			
			userInfoMapper.addUserInfo(userInfo);
		}
		book.close();
	}

	@Override
	public File createDownloadFile(List<UserInfo> users) throws Exception{
		//生成一个临时文件
		File file = File.createTempFile("tempusers", ".xls");
		//生成一个可写的excel
		WritableWorkbook book = Workbook.createWorkbook(file);
		//生成一个可写的sheet
		WritableSheet sheet = book.createSheet("sheet", 0);
		//标题
		String[] titles = {"姓名","性别","电话号码","密码","用户类型"};
		//把标题加入第一行
		for(int i = 0 ; i < titles.length; i++){
			Label label = new Label(i, 0, titles[i]);//第0行第i列
			sheet.addCell(label);
		}
		for(int i = 0 ; i < users.size() ; i++){
			sheet.addCell(new Label(0, i+1, users.get(i).getUserName()));//姓名
			sheet.addCell(new Label(1, i+1, users.get(i).getUserSex()));//性别
			sheet.addCell(new Label(2, i+1, users.get(i).getUserPhone()));//电话
			sheet.addCell(new Label(3, i+1, users.get(i).getUserPw()));//密码
			sheet.addCell(new Label(4, i+1, users.get(i).getUserType()));//类型
		}
		book.write();
		book.close();
		return file;
	}

	@Override
	public void addUserInfo(UserInfo userInfo) throws Exception {
		userInfoMapper.addUserInfo(userInfo);
	}

	@Override
	public boolean validatePhone(UserInfo userInfo) {
		UserInfo puser = userInfoMapper.getUserInfo(userInfo);
		if(puser == null){
			return true;
		}
		return false;
	}


	@Override
	public void updateUserInfo(UserInfo userInfo) throws Exception{
		userInfoMapper.updateUserInfo(userInfo);
	}

	@Override
	public void deleteUserInfo(UserInfo userInfo) throws Exception {
		userInfoMapper.deleteUserInfo(userInfo);
	}

	@Override
	public UserInfo doLogin(UserInfo userInfo) {
		return userInfoMapper.getUserInfo(userInfo);
	}

	
	@Override
	public UserInfo loadUpdateUserInfo(UserInfo userInfo) {
		return userInfoMapper.getUserInfo(userInfo);
	}

}
