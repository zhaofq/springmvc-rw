package com.java.spring.pojo;
/**
* @author 作者:zhaofq
* @version 创建时间：2017年2月21日 下午2:26:33
* 类说明
*/
public class UserInfo {
	
	
    private String Id;
    private String headPicUrl;
    private String userId;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getHeadPicUrl() {
		return headPicUrl;
	}
	public void setHeadPicUrl(String headPicUrl) {
		this.headPicUrl = headPicUrl;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

    
}
