package com.shibeijie.demo.utils;

import java.io.Serializable;
/**
 * 
 * @Title: BaseBean.java 
 * @Description: TODO
 * @author oswald
 * @version V1.0
 */
public class BaseBean implements Serializable{
	private Integer start;
	private Integer length;
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}
}