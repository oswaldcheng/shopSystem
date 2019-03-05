package com.shibeijie.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shibeijie.demo.bean.GoodsInfo;
import com.shibeijie.demo.utils.PageBean;

/**
 * @author 郭城
 *
 */
public interface GoodsInfoService {
	/**
	 * 向文件服务器发送图片
	 * @param file
	 * @return 返回地址
	 */
	public String doPutToFileServer(MultipartFile file);
	
	/**
	 * 添加商品信息
	 * @param goodsInfo
	 * @throws Exception 
	 */
	public void addGoodsInfo(GoodsInfo goodsInfo) throws Exception;
	
	/**
	 * 根据条件/分页信息查询商品数据
	 * @return
	 */
	public PageBean<GoodsInfo> getGoodsInfoList(GoodsInfo goodsInfo,Integer page);
	
	/**
	 * 根据条件查询商品记录数
	 * @param goodsInfo
	 * @return
	 */
	public Long getGoodsInfoCount(GoodsInfo goodsInfo);
	
	/**
	 * 修改商品信息
	 * @param goodsInfo
	 * @throws Exception
	 */
	public void updateGoodsInfo(GoodsInfo goodsInfo) throws Exception;
	
	/**
	 * 加载商品信息
	 * @param goodsInfo
	 * @return
	 */
	public GoodsInfo getGoodsInfo(GoodsInfo goodsInfo) ;
	
	/**
	 * 商品上架
	 * @param goodsInfo
	 */
	public void upGoodsInfo(GoodsInfo goodsInfo) throws Exception;
	
	/**
	 * 商品下架
	 * @param goodsInfo
	 */
	public void downGoodsInfo(GoodsInfo goodsInfo) throws Exception;
	
	/**
	 * 商城首页展示商品信息
	 * @param goodsInfo
	 */
	public List<GoodsInfo> getFrontGoodsInfoList(GoodsInfo goodsInfo);
	
}

