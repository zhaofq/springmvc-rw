package com.java.spring.pojo;
/**
* @author 作者:zhaofq
* @version 创建时间：2017年2月23日 下午4:41:34
* 类说明
*/
public class MarkPic {
	   private String  Id;
	   private String  userId;//评论用户id
	   private String  picId;//点赞图片Id
	   private int  mark;//点赞图片mark
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPicId() {
		return picId;
	}
	public void setPicId(String picId) {
		this.picId = picId;
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}

}   
