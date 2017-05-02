package com.java.spring.vo;

import com.java.spring.pojo.MarkPic;
import com.java.spring.pojo.User;
import com.java.spring.pojo.UserInfo;

/**
* @author 作者:zhaofq
* @version 创建时间：2017年2月10日 下午2:45:37
* 类说明
*/
public class PictureVo {
	
	  private String id;
	  private String url;
	  private String name;
	  private String  discription;
	  private String createadate;
	  private String userId;
	  private int markNumber;
	  private User user = new User();
	  private UserInfo userInfo;
	  private MarkPic markPic = new MarkPic();

	  
	  
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	
	public String getCreateadate() {
		return createadate;
	}
	public void setCreateadate(String createadate) {
		this.createadate = createadate;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public MarkPic getMarkPic() {
		return markPic;
	}
	public void setMarkPic(MarkPic markPic) {
		this.markPic = markPic;
	}
	public int getMarkNumber() {
		return markNumber;
	}
	public void setMarkNumber(int markNumber) {
		this.markNumber = markNumber;
	}
	
	
	  
	  
}
