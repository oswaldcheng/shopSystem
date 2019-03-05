package com.shibeijie.demo.controller.front;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shibeijie.demo.bean.GoodsInfo;
import com.shibeijie.demo.bean.UserInfo;
import com.shibeijie.demo.service.GoodsInfoService;
import com.shibeijie.demo.service.UserInfoService;

/**
 * @author 郭城
 *
 */
@Controller
@RequestMapping("/")
public class FrontController {
	
	@Autowired
	private GoodsInfoService goodsInfoService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	/**
	 * 展示商城首页
	 * @return
	 */
	@RequestMapping("index")
	public String index(Model model){
		//查询商品信息
		List<GoodsInfo> list = goodsInfoService.getFrontGoodsInfoList(new GoodsInfo());
		model.addAttribute("list", list);
		return "front/index";
	}
	
	/**
	 * 查看商品详情
	 * @return
	 */
	@RequestMapping("goodsinfo/{goodsId}")
	public String getGoodsInfo(@PathVariable Integer goodsId,Model model){
		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo.setGoodsId(goodsId);
		GoodsInfo pgoodsInfo = goodsInfoService.getGoodsInfo(goodsInfo);
		model.addAttribute("g", pgoodsInfo);
		return "front/goodsinfo";
	}
	
	/**
	 * 商品加入购物车
	 * @param goodsId
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("cart/{goodsId}")
	public String addToCart(@PathVariable Integer goodsId,Model model,HttpSession session){
		Map<String , GoodsInfo> cart = (Map<String, GoodsInfo>)session.getAttribute("cart");
		if(cart == null){
			cart = new HashMap<String, GoodsInfo>();
			session.setAttribute("cart", cart);
		}
		GoodsInfo goodsInfo = new GoodsInfo();
		goodsInfo.setGoodsId(goodsId);
		goodsInfo = goodsInfoService.getGoodsInfo(goodsInfo);
		cart.put(goodsId+goodsInfo.getGoodsName(), goodsInfo);
		return "front/shoppingcart";
	}
	/**
	 * 删除商品
	 * @param keys
	 * @param session
	 * @return
	 */
	@RequestMapping("cart/delete")
	public String cartDelete(String[] keys,HttpSession session){
		Map<String , GoodsInfo> cart = (Map<String, GoodsInfo>)session.getAttribute("cart");
		for(String key : keys){
			System.out.println("key = " + key);
			cart.remove(key);
		}
		return "front/shoppingcart";
	}
	
	/**
	 * 显示购物车
	 * @return
	 */
	@RequestMapping("cart/show")
	public String cartShow(){
		return "front/shoppingcart";
	}
	
	/**
	 * 用户登陆页面
	 * @return
	 */
	@RequestMapping("login")
	public String login(){
		return "front/login";
	}
	/**
	 * 
	 * @Title: doLogin  
	 * @Description: TODO 
	 * @param userInfo
	 * @param session
	 * @param model
	 * @return    参数  
	 * @return String    返回类型  
	 *
	 */
	@RequestMapping("dologin")
	public String doLogin(UserInfo userInfo,HttpSession session,Model model){
		UserInfo loginuser = userInfoService.doLogin(userInfo);
		if(loginuser != null){
			session.setAttribute("loginuser", loginuser);
			return index(model);
		}else{
			return "front/login";
		}
	}
	/**
	 * 用户注销
	 * @Title: doLogout  
	 * @Description: TODO 
	 * @param userInfo
	 * @param session
	 * @param model
	 * @return    参数  
	 * @return String    返回类型  
	 *
	 */
	@RequestMapping("dologout")
	public String doLogout(HttpSession session,Model model) {
		session.setAttribute("loginuser", null);
		return index(model);
	}
}
