package com.java.spring.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.java.spring.dao.CommentsDao;
import com.java.spring.dao.MarkPicDao;
import com.java.spring.dao.PictureDao;
import com.java.spring.dao.UserDao;
import com.java.spring.pojo.MarkPic;
import com.java.spring.pojo.Picture;
import com.java.spring.pojo.User;
import com.java.spring.redisdao.RedisUserDao;
import com.java.spring.service.PictureManagerService;
import com.java.spring.util.Fileupload;
import com.java.spring.util.system.FormatUtils;
import com.java.spring.util.system.Message;
import com.java.spring.util.system.Page;
import com.java.spring.util.system.PagerVO;
import com.java.spring.vo.PictureVo;

/**
 * @author 作者:zhaofq
 * @version 创建时间：2017年2月7日 下午5:24:42 类说明
 */
@Service
public class PictureManagerServiceImpl implements PictureManagerService {

	private static Logger loger = Logger.getLogger(PictureManagerServiceImpl.class.getName());

	@Autowired
	PictureDao pictureDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	RedisUserDao redisUserDao;
    
	@Autowired
	CommentsDao commentsDao;
	
	@Autowired
	MarkPicDao markPicDao;
	public Message pictureManager(MultipartFile items_pic, String name, HttpServletRequest request) {
		Fileupload fp = new Fileupload();
		Message message = new Message();
		try {
			User user = (User) request.getSession().getAttribute("userInfo");

			if (null != user) {
				String uploadResult = fp.pictureManagerUpload(items_pic, name);
				if (uploadResult != "filed") {
					Picture picture = new Picture();
					picture.setDiscription(request.getParameter("picdesc"));
					picture.setId(UUID.randomUUID().toString().toUpperCase());
					picture.setCreateadate((FormatUtils.millisDateFormat(new Date())).toString());
					picture.setUserId(user.getId());
					picture.setUrl(uploadResult);
					int i = this.addPicture(picture);
					if (i != 0) {
						message.setCode(0);
						message.setMessage("成功");
					} else {
						message.setCode(1);
						message.setMessage("失败");
					}
				} else {
					message.setCode(1);
					message.setMessage("上传图片失败");
				}
			} else {
				message.setCode(1);
				message.setMessage("用户登录过期,请从新登录");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	private int addPicture(Picture picture) {
		int i = 0;
		try {
			i = pictureDao.addPicture(picture);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public List<PictureVo> findPictures(HttpServletRequest request) {
		List<PictureVo> pictureVos = new ArrayList<>();
		List<PictureVo> pictureVo = new ArrayList<>();
		Picture picture = new Picture();
		User user = (User) request.getSession().getAttribute("userInfo");
		try {
			pictureVos = pictureDao.findPictures(picture);
			if (pictureVos.size() > 0) {
				for (int i = 0; i < pictureVos.size(); i++) {
					PictureVo picture1 = new PictureVo();
					picture1.setId(pictureVos.get(i).getId());
					picture1.setUrl("http://localhost" + pictureVos.get(i).getUrl());
					//查询点赞次数和图片发布这信息
					PictureVo pictureVo1 = this.getUserAllInfoAndMarkNumber(pictureVos.get(i).getId());
					//查询当前用户是否已经点赞
					if (null != user) {
						int markStatus = this.getMarkStatus(pictureVos.get(i).getId(),user.getId());
						picture1.getMarkPic().setMark(markStatus);
					}
					//获得评论
					this.getComments();
					picture1.setCreateadate(FormatUtils.millisDateFormats(pictureVos.get(i).getCreateadate()));
					picture1.setUserId(pictureVos.get(i).getId());
					picture1.setDiscription(pictureVos.get(i).getDiscription());
					picture1.getUser().setHeadPicUrl("http://localhost" + pictureVo1.getUser().getHeadPicUrl());
					picture1.getUser().setLoginName(pictureVo1.getUser().getLoginName());
					picture1.setMarkNumber(pictureVo1.getMarkNumber());
					pictureVo.add(picture1);
				}
			}
		} catch (Exception e) {
			loger.info("findPictures is Exception------" + e);
			e.printStackTrace();
		}
		return pictureVo;
	}

	private void getComments() {
		// TODO Auto-generated method stub
		
	}

	private int getMarkStatus(String picId, String userId) {
		int i=0;
		try {
			i=  markPicDao.getMarkStatus(picId,userId);
		} catch (Exception e) {
			loger.info("getMarkStatus is Exception------" + e);
			e.printStackTrace();
		}
		return i;
	}

	//获取用户信息(基本信息+附加信息)从mysql中
	private PictureVo  getUserAllInfoAndMarkNumber(String picId) {
		PictureVo pictureVo = new PictureVo();
		try {
			pictureVo = markPicDao.getUserAllInfoAndMarkNumber(picId);
		} catch (Exception e) {
			e.printStackTrace();
			loger.info("find user information fiel------d"+e);
		}
		return pictureVo;
	}

	//点赞
	public Message markForPic(HttpServletRequest request,MarkPic markPic) {
		Message message = new Message();
		User user = (User)request.getSession().getAttribute("userInfo");
		if(null !=user){
			markPic.setUserId(user.getId());
			int i = this.operationMarkForPictures(markPic);
			if (1==i) {
			} else {
				message.setCode(0);
				message.setMessage("点赞成功");
			}
		}else{
			message.setCode(1);
			message.setMessage("用户未登录");
		}
		return message;
	}

	private int operationMarkForPictures(MarkPic markPic) {
		int i =0;
		try {
			if (0 == markPic.getMark()) {//0说明用户是点赞操作所以加一条数据标识为已经点赞，否则就是删除
				markPic.setId(UUID.randomUUID().toString().toUpperCase());
				i=markPicDao.addMarkPic(markPic);
			} else {
				i=markPicDao.deleteMarkPic(markPic);
			}
			
		} catch (Exception e) {
			loger.info(e);
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public PagerVO<PictureVo> findPicturessss(HttpServletRequest request,Page<PictureVo> pager) {
		List<PictureVo> pictureVos = new ArrayList<>();
		List<PictureVo> pictureVo = new ArrayList<>();
		PagerVO<PictureVo> pictureVopage = new PagerVO<PictureVo>();
		Picture picture = new Picture();
		int totalNumber = 0;
		User user = (User) request.getSession().getAttribute("userInfo");
		try {
			Page<PictureVo>page=new Page<PictureVo>();
			page.setPageNo(pager.getPageNo());
			totalNumber = pictureDao.findPictureTotalNumber();
			pictureVos = pictureDao.findPicturessss(page);
			if (pictureVos.size() > 0) {
				for (int i = 0; i < pictureVos.size(); i++) {
					PictureVo picture1 = new PictureVo();
					picture1.setId(pictureVos.get(i).getId());
					picture1.setUrl("http://localhost" + pictureVos.get(i).getUrl());
					//查询点赞次数和图片发布这信息
					PictureVo pictureVo1 = this.getUserAllInfoAndMarkNumber(pictureVos.get(i).getId());
					//查询当前用户是否已经点赞
					if (null != user) {
						int markStatus = this.getMarkStatus(pictureVos.get(i).getId(),user.getId());
						picture1.getMarkPic().setMark(markStatus);
					}
					//获得评论
					this.getComments();
					picture1.setCreateadate(FormatUtils.millisDateFormats(pictureVos.get(i).getCreateadate()));
					picture1.setUserId(pictureVos.get(i).getId());
					picture1.setDiscription(pictureVos.get(i).getDiscription());
					picture1.getUser().setHeadPicUrl("http://localhost" + pictureVo1.getUser().getHeadPicUrl());
					picture1.getUser().setLoginName(pictureVo1.getUser().getLoginName());
					picture1.setMarkNumber(pictureVo1.getMarkNumber());
					pictureVo.add(picture1);
				}
				pictureVopage.setData(pictureVo);
				pictureVopage.setTotal(totalNumber);
				pictureVopage.setTotalPage(totalNumber/pictureVopage.getLength());
				
			}
		} catch (Exception e) {
			loger.info("findPictures is Exception------" + e);
			e.printStackTrace();
		}
		return pictureVopage;
	}

}
