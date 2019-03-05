package com.shibeijie.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.shibeijie.demo.bean.GoodsInfo;

/**
 * @author 郭城
 *
 */
@Mapper
public interface GoodsInfoMapper {
	/**
	 * 添加商品信息
	 * @param goodsInfo
	 */
	public void addGoodsInfo(GoodsInfo goodsInfo);
	
	/**
	 * 根据分页/条件查询商品信息
	 * @param goodsInfo
	 * @return
	 */
	public List<GoodsInfo> getGoodsInfoList(GoodsInfo goodsInfo);
	
	/**
	 * 根据条件查询商品数量
	 * @param goodsInfo
	 * @return
	 */
	public Long getGoodsInfoCount(GoodsInfo goodsInfo);
	
	/**
	 * 修改商品信息
	 * @param goodsInfo
	 */
	public void updateGoodsInfo(GoodsInfo goodsInfo);
	
	/**
	 * 加载商品信息
	 * @param goodsInfo
	 * @return
	 */
	public GoodsInfo getGoodsInfo(GoodsInfo goodsInfo);
}