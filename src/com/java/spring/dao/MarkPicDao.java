package com.java.spring.dao;

import com.java.spring.pojo.MarkPic;
import com.java.spring.vo.PictureVo;

/**
* @author 作者:zhaofq
* @version 创建时间：2017年2月23日 下午4:58:38
* 类说明
*/
public interface MarkPicDao {

	int addMarkPic(MarkPic markPic)throws Exception;

	int deleteMarkPic(MarkPic markPic)throws Exception;

	PictureVo getUserAllInfoAndMarkNumber(String picId)throws Exception;

	int getMarkStatus(String picId, String userId)throws Exception;

}
