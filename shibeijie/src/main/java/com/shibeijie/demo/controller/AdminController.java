package com.shibeijie.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shibeijie.demo.bean.ConsigneeManagement;
import com.shibeijie.demo.bean.GoodsInfo;
import com.shibeijie.demo.bean.OrderDetail;
import com.shibeijie.demo.bean.OrderManagement;
import com.shibeijie.demo.bean.UserInfo;
import com.shibeijie.demo.service.ConsigneeManagementService;
import com.shibeijie.demo.service.OrderManagementService;

/**
 * @author 郭城
 *
 */
@Controller
@RequestMapping("/admin/")
public class AdminController {
	
	@Autowired
	private ConsigneeManagementService cmService;
	
	@Autowired
	private OrderManagementService omService;
	
	@RequestMapping("ordercheck")
	public String orderCheck(String[] keys,Integer[] number,HttpSession session,Model model){
		Map<String , GoodsInfo> cart = (Map<String, GoodsInfo>)session.getAttribute("cart");
		double sum = 0;//总价
		List<OrderDetail> list = new ArrayList<>();
		for(int i = 0 ; i < number.length ; i++){
			OrderDetail od = new OrderDetail();//订单项
			GoodsInfo goodsInfo = cart.get(keys[i]);
			od.setGoodsId(goodsInfo.getGoodsId());
			od.setGoodsName(goodsInfo.getGoodsName());
			od.setOrderPrice(goodsInfo.getGoodsPrice());
			od.setGoodsUrl(goodsInfo.getGoodsUrl());
			od.setOrdeNumber(number[i]);
			od.setEveryTotal(number[i] * Double.parseDouble(goodsInfo.getGoodsPrice()));
			list.add(od);
			sum += number[i] * Double.parseDouble(goodsInfo.getGoodsPrice());//总价
		}
		int num = 0;//数量
		for(int i : number){
			num += i;
		}
		model.addAttribute("list", list);
		model.addAttribute("sum", sum);
		model.addAttribute("num", num);
		
		ConsigneeManagement cm = new ConsigneeManagement();
		UserInfo userInfo = (UserInfo)session.getAttribute("loginuser");
		cm.setUserId(userInfo.getUserId());
		
		List<ConsigneeManagement> cmList = cmService.getCmList(cm);
		model.addAttribute("cmlist", cmList);
		
		return "front/order_check";
	}
	
	/**
	 * 核对订单
	 ** @author 郭城
	 * @param om
	 * @param goodsInfos
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("orderconfirm")
	public String orderConfirm(OrderManagement om, ArrayList<String> goodsInfos,HttpSession session,Model model){
		UserInfo userInfo = (UserInfo) session.getAttribute("loginuser");

		om.setUserId(userInfo.getUserId());
		om.setOrderTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		//构建订单详情
		List<OrderDetail> list = new ArrayList<>();
		for(String str : goodsInfos){
			String[] goodsInfo = str.split(",");
			OrderDetail od = new OrderDetail();
			od.setGoodsId(Integer.parseInt(goodsInfo[0]));
			
			od.setOrdeNumber(Integer.parseInt(goodsInfo[1]));
			od.setOrderPrice(goodsInfo[2]);
			list.add(od);
		}
		
		try {
			omService.add(om, list);
			model.addAttribute("info", "yes");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("info", "no");
		}
		
		return "front/orderok";
		
	}
}

