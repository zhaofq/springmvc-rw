package com.java.spring.pojo;
/**
* @author 作者:zhaofq
* @version 创建时间：2017年2月22日 下午4:44:45
* 类说明：用户对图片的点赞，评论
*/
public class Comments {
	
	private String  Id;
    private String  picId;//点赞图片Id
    private String  commentsContent;//评论内容
    private String  userId;//评论用户id
    private String  createTime;//评论时间
    
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getPicId() {
		return picId;
	}
	public void setPicId(String picId) {
		this.picId = picId;
	}
	public String getCommentsContent() {
		return commentsContent;
	}
	public void setCommentsContent(String commentsContent) {
		this.commentsContent = commentsContent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
