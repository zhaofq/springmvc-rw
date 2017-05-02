package com.java.spring.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.spring.dao.CommentsDao;
import com.java.spring.pojo.Comments;
import com.java.spring.service.CommentsService;

/**
* @author 作者:zhaofq
* @version 创建时间：2017年2月23日 下午2:00:07
* 类说明
*/
@Service
public class CommentsServiceImpl implements CommentsService {
  
	
	@Autowired
	CommentsDao commentsDao;
	
	public int addComments(Comments comments) {
		try {
			return commentsDao.addComments(comments);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
