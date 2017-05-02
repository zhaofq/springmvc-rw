package com.java.spring.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.java.spring.pojo.MarkPic;
import com.java.spring.service.PictureManagerService;
import com.java.spring.util.system.Message;
import com.java.spring.util.system.Page;
import com.java.spring.util.system.PagerVO;
import com.java.spring.vo.PictureVo;

/**
* @author 作者:zhaofq
* @version 创建时间：2017年2月7日 下午4:37:31
* 类说明
*/

@Controller
public class PictureManagerController {
	
	    @Autowired
	    PictureManagerService pictureManagerService;
	    
	    //上传图片
	    @RequestMapping(value = "/pictureManagerUpload",method=RequestMethod.POST)
		public @ResponseBody Message upload(MultipartFile items_pic,String name,HttpServletRequest request){
			return pictureManagerService.pictureManager(items_pic, name,request);
		}
	    
	    //查询图片列表
	    @RequestMapping(value = "/findPictures")
		public @ResponseBody List<PictureVo> findPictures(HttpServletRequest request){
			return pictureManagerService.findPictures(request);
		}
	  //查询图片列表
	    @RequestMapping(value = "/findPicturessss")
		public @ResponseBody  PagerVO<PictureVo> findPicturessss(HttpServletRequest request,Page <PictureVo>pager){
			return pictureManagerService.findPicturessss(request,pager);
		}
	    
	    //点赞
	    @RequestMapping(value = "/markForPic")
		public @ResponseBody Message markPictures(HttpServletRequest request,MarkPic markPic){
			return pictureManagerService.markForPic(request,markPic);
		}
}
