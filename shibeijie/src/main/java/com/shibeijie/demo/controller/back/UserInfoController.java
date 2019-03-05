package com.shibeijie.demo.controller.back;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shibeijie.demo.bean.UserInfo;
import com.shibeijie.demo.service.UserInfoService;
import com.shibeijie.demo.utils.PageBean;

/**
 * 后台 用户管理核心控制器
 * @author 郭城
 *
 */
@Controller
@RequestMapping("/back/userinfo/")
public class UserInfoController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * 
	 * 主页
	 ** @author 郭城
	 * 	@return
	 */
	@RequestMapping("index")
	public String index() {
		return "index";
	}
	
	/**
	 * 主页 main域
	 ** @author 郭城
	 * 	@return
	 */
	@RequestMapping("main")
	public String main(){
		return "main";
	}
	
	/**
	 * 根据条件/分页查询用户信息
	 * @param userInfo
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("list")
	public String list(UserInfo userInfo,Model model,Integer page){
		PageBean<UserInfo> pageBean = userInfoService.getUserInfoList(userInfo , page);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("userInfo", userInfo);//保存查询条件，供页面分页标签使用
		return "back/userinfo/userinfo_list";
	}
	
	/**
	 * 批量导入用户
	 * @param users
	 * @param model
	 * @return
	 */
	@RequestMapping("importusers")
	public String importUsers(@RequestParam MultipartFile users,Model model){
		System.out.println("users = " + users);
		try {
			userInfoService.addUsers(users);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list(new UserInfo(),model,1);
	}
	
	/**
	 * 批量导出用户
	 * @param userInfo
	 * @param response
	 * @return
	 */
	@RequestMapping("exportusers")
	public String exportUsers(UserInfo userInfo,HttpServletResponse response){
		PageBean<UserInfo> pageBean = userInfoService.getUserInfoList(userInfo, -1);//不分页
		List<UserInfo> users = pageBean.getList();
		try {
			File file = userInfoService.createDownloadFile(users);
			FileInputStream fis = new FileInputStream(file);
			response.setContentType("application/force-download");
			response.addHeader("Content-disposition", "attachment;fileName=users-export.xls");//设置下载的文件名
			OutputStream os = response.getOutputStream();
			byte[] buf = new byte[1024];
			int len = 0;
			while((len = fis.read(buf)) != -1){
				os.write(buf, 0, len);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 加载  添加用户页面
	 * @return
	 */
	@RequestMapping("loadadd")
	public String loadAdd(){
		return "back/userinfo/userinfo_add";
	}
	
	/**
	 * 添加用户
	 ** @author 郭城
	 * @param userInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("add")
	@CacheEvict(cacheNames="sbj",allEntries=true)
	public String add(UserInfo userInfo,Model model){
		try {
			userInfoService.addUserInfo(userInfo);
			model.addAttribute("result", "添加用户信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("result", "添加用户信息失败");
		}
		return "back/userinfo/userinfo_info";
	}
	
	/**
	 * 验证用户账号唯一
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("validatephone")
	@Cacheable(cacheNames="sbj",key="#p0")
	@ResponseBody
	public String validatePhone(UserInfo userInfo){
		boolean flag = userInfoService.validatePhone(userInfo);
		if(flag){
			return "ok";
		}else{
			return "not ok";
		}
	}
	
	/**
	 * 加载待修改用户信息的页面
	 * @param userInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("loadupdate")
	@Cacheable(cacheNames="sbj",key="#p0")
	public String loadUpdate(UserInfo userInfo,Model model){

		UserInfo puser = userInfoService.loadUpdateUserInfo(userInfo);
		model.addAttribute("userinfo", puser);
		return "back/userinfo/userinfo_update";
	}
	
	/**
	 * 修改用户信息
	 * @param userInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("update")
	@CacheEvict(cacheNames="sbj",allEntries=true)
	public String updateUserInfo(UserInfo userInfo,Model model){
		try {
			userInfoService.updateUserInfo(userInfo);
			model.addAttribute("result", "修改用户信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("result", "修改用户信息失败");
		}
		return "back/userinfo/userinfo_info";
	}
	
	/**
	 * 删除用户信息
	 * @param userInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("delete")
	@CacheEvict(cacheNames="sbj",allEntries=true)
	public String deleteUserInfo(UserInfo userInfo,Model model){
		try {
			userInfoService.deleteUserInfo(userInfo);
			model.addAttribute("result", "删除用户信息成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("result", "删除用户信息失败");
		}
		return "back/userinfo/userinfo_info";
	}
	
}
