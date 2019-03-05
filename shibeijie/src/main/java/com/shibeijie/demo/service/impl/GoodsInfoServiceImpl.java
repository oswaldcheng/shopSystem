package com.shibeijie.demo.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shibeijie.demo.bean.GoodsInfo;
import com.shibeijie.demo.dao.GoodsInfoMapper;
import com.shibeijie.demo.service.GoodsInfoService;
import com.shibeijie.demo.utils.Const;
import com.shibeijie.demo.utils.FileNameCreator;
import com.shibeijie.demo.utils.PageBean;
import com.shibeijie.demo.utils.PageUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 * @author 郭城
 *
 */
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService{
	
	@Autowired
	private GoodsInfoMapper goodsInfoMapper;

	@Override
	public String doPutToFileServer(MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String url = FileNameCreator.createRandomName(Const.FILE_SERVER_URL, fileName);
		Client client = new Client();
		WebResource resource = client.resource(url);
		try {
			byte[] buf = file.getBytes();
			resource.put(String.class,buf);
			return url;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addGoodsInfo(GoodsInfo goodsInfo) throws Exception{
		goodsInfo.setGoodsState(Const.GOODS_STATE_INIT);
		goodsInfoMapper.addGoodsInfo(goodsInfo);
	}

	@Override
	public PageBean<GoodsInfo> getGoodsInfoList(GoodsInfo goodsInfo, Integer page) {
		int allRow = getGoodsInfoCount(goodsInfo).intValue();//总记录数
		int totalPage = PageUtil.countTotalPage(allRow, Const.PAGE_SIZE);//总页数
		int currentPage = PageUtil.countCurrentPage(page);//当前第几页
		int start = PageUtil.countStart(Const.PAGE_SIZE, currentPage);//起始记录数
		if(page >= 0){
			goodsInfo.setStart(start);//起始记录数
			goodsInfo.setLength(Const.PAGE_SIZE);//每页显示的记录数
		}else{
			goodsInfo.setStart(-1);//不分页
			goodsInfo.setLength(-1);//不分页
		}
		List<GoodsInfo> goods = goodsInfoMapper.getGoodsInfoList(goodsInfo);//记录集合
		
		PageBean<GoodsInfo> pageBean = new PageBean<>();
		pageBean.setAllRow(allRow);
		pageBean.setCurrentPage(currentPage);
		pageBean.setList(goods);
		pageBean.setPageSize(Const.PAGE_SIZE);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	@Override
	public Long getGoodsInfoCount(GoodsInfo goodsInfo) {
		return goodsInfoMapper.getGoodsInfoCount(goodsInfo);
	}

	@Override
	public void updateGoodsInfo(GoodsInfo goodsInfo) throws Exception {
		goodsInfoMapper.updateGoodsInfo(goodsInfo);
	}

	@Override
	public GoodsInfo getGoodsInfo(GoodsInfo goodsInfo) {
		return goodsInfoMapper.getGoodsInfo(goodsInfo);
	}

	@Override
	public void upGoodsInfo(GoodsInfo goodsInfo) throws Exception {
		goodsInfo.setGoodsState(Const.GOODS_STATE_UP);
		goodsInfoMapper.updateGoodsInfo(goodsInfo);
	}

	@Override
	public void downGoodsInfo(GoodsInfo goodsInfo) throws Exception {
		goodsInfo.setGoodsState(Const.GOODS_STATE_DOWN);
		goodsInfoMapper.updateGoodsInfo(goodsInfo);
	}

	@Override
	public List<GoodsInfo> getFrontGoodsInfoList(GoodsInfo goodsInfo) {
		goodsInfo.setGoodsState(Const.GOODS_STATE_UP);//上架商品
		goodsInfo.setStart(-1);
		goodsInfo.setLength(-1);
		List<GoodsInfo> goodsInfoList = goodsInfoMapper.getGoodsInfoList(goodsInfo);
		return goodsInfoList;
	}
	
}
