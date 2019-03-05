package com.shibeijie.demo.bean;

import com.shibeijie.demo.utils.BaseBean;

/**
 * 商品信息
 * @author 郭城
 *
 */
public class GoodsInfo extends BaseBean{

	//商品ID
	public Integer goodsId;
	//商品名
	public String goodsName;
	//商品价格
	public String goodsPrice;
	//图片地址
	private String goodsUrl;
	//商品详情
	private String goodsDesc;
	//商品状态
	private String goodsState;
	//商品简介
	private String goodsIntro;

	public String getGoodsIntro() {
		return goodsIntro;
	}

	public void setGoodsIntro(String goodsIntro) {
		this.goodsIntro = goodsIntro;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsUrl() {
		return goodsUrl;
	}

	public void setGoodsUrl(String goodsUrl) {
		this.goodsUrl = goodsUrl;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(String goodsState) {
		this.goodsState = goodsState;
	}

	@Override
	public String toString() {
		return "GoodsInfo [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice
				+ ", goodsUrl=" + goodsUrl + ", goodsDesc=" + goodsDesc + ", goodsState=" + goodsState + "]";
	}
	
	
}
