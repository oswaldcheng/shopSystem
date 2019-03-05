package com.shibeijie.demo.controller.back;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shibeijie.demo.bean.GoodsInfo;
import com.shibeijie.demo.service.GoodsInfoService;
import com.shibeijie.demo.utils.PageBean;

/**
 * 商品管理控制器
 * @author 郭城
 *
 */
@Controller
@RequestMapping("/back/goodsinfo/")
public class GoodsInfoController {
	
	@Autowired
	private GoodsInfoService goodsInfoService;
	
	/**
	 * 加载添加商品页面
	 * @return
	 */
	@RequestMapping("loadadd")
	public String loadAdd(){
		return "back/goodsinfo/goodsinfo_add";
	}
	
	/**
	 * 上传商品  预览图片
	 * @return
	 */
	@RequestMapping("uploadimg")
	public @ResponseBody String uploadImg(@RequestParam MultipartFile upload){
		String url = goodsInfoService.doPutToFileServer(upload);
		return url;
	}
	
	/**
	 * 富文本编辑器上传图片
	 * @param upload
	 * @param response
	 * @param request
	 */
	@RequestMapping("uploadfile")
	public void uploadFile(@RequestParam MultipartFile upload,HttpServletResponse response,HttpServletRequest request){
		String url = goodsInfoService.doPutToFileServer(upload);
		try {
			PrintWriter out = response.getWriter();
			String callBack = request.getParameter("CKEditorFuncNum");
			out.println("<script>window.parent.CKEDITOR.tools.callFunction(" + callBack + ",'" + url + "')</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * 添加商品
	 * @param goodsInfo
	 * @param model
	 */
	@RequestMapping("add")
	public String add(GoodsInfo goodsInfo,Model model){
		try {
			goodsInfoService.addGoodsInfo(goodsInfo);
			model.addAttribute("result", "添加商品成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("result", "添加商品失败");
		}
		return "back/goodsinfo/goodsinfo_info";
	}
	
	
	/**
	 * 根据条件/分页查询商品信息
	 * @param userInfo
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping("list")
	public String list(GoodsInfo goodsInfo,Model model,Integer page){
		PageBean<GoodsInfo> pageBean = goodsInfoService.getGoodsInfoList(goodsInfo, page);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("goodsinfo", goodsInfo);//保存查询条件，供页面分页标签使用
		return "back/goodsinfo/goodsinfo_list";
	}
	
	
	/**
	 * 加载待修改商品信息的页面
	 * @param goodsInfo
	 * @return
	 */
	@RequestMapping("loadupdate")
	public String loadUpdate(GoodsInfo goodsInfo,Model model){
		GoodsInfo pgoods = goodsInfoService.getGoodsInfo(goodsInfo);
		model.addAttribute("goodsinfo", pgoods);
		return "back/goodsinfo/goodsinfo_update";
	}
	
	/**
	 * 修改商品信息
	 * @param goodsInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("update")
	public String update(GoodsInfo goodsInfo,Model model){
		try {
			goodsInfoService.updateGoodsInfo(goodsInfo);
			model.addAttribute("result", "商品修改成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("result", "商品修改失败");
		}
		return "back/goodsinfo/goodsinfo_info";
	}
	
	/**
	 * 商品上架
	 * @param goodsInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("upgoods")
	public String upGoods(GoodsInfo goodsInfo,Model model){
		try {
			goodsInfoService.upGoodsInfo(goodsInfo);
			model.addAttribute("result", "商品上架成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("result", "商品上架失败");
		}
		return "back/goodsinfo/goodsinfo_info";
	}
	
	/**
	 * 商品下架
	 * @param goodsInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("downgoods")
	public String downGoods(GoodsInfo goodsInfo,Model model){
		try {
			goodsInfoService.downGoodsInfo(goodsInfo);
			model.addAttribute("result", "商品下架成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			model.addAttribute("result", "商品下架失败");
		}
		return "back/goodsinfo/goodsinfo_info";
	}
}

