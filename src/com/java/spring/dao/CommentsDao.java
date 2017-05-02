package com.java.spring.dao;

import com.java.spring.pojo.Comments;

/**
* @author 作者:zhaofq
* @version 创建时间：2017年2月23日 下午2:12:41
* 类说明
*/
public interface CommentsDao {

	public int addComments(Comments comments)throws Exception;

}
