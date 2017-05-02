package com.java.spring.util.system;

import java.util.List;

/**
* @author 作者:zhaofq
* @version 创建时间：2017年2月27日 下午4:27:00
* 类说明
*/
public class PagerVO<T> {
	    
	    private int totalPage;//总页数
	    private int start=1;//当前页，位置从0开始
		private int length = 2;//页面显示最大记录数
		private String sort;//排序类型，asc/desc
		private int total;//总记录数 
	    private List<T> data;//对应的当前页记录 
	    private String  filed;
		public int getTotalPage() {
			return totalPage;
		}
		public void setTotalPage(int totalPage) {
			this.totalPage = totalPage;
		}
		public int getStart() {
			return start;
		}
		public void setStart(int start) {
			this.start = start;
		}
		public int getLength() {
			return length;
		}
		public void setLength(int length) {
			this.length = length;
		}
		public String getSort() {
			return sort;
		}
		public void setSort(String sort) {
			this.sort = sort;
		}
		public int getTotal() {
			return total;
		}
		public void setTotal(int total) {
			this.total = total;
		}
		public List<T> getData() {
			return data;
		}
		public void setData(List<T> data) {
			this.data = data;
		}
		public String getFiled() {
			return filed;
		}
		public void setFiled(String filed) {
			this.filed = filed;
		}
		public PagerVO() {
			super();
			this.totalPage = totalPage;
			this.start = start;
			this.length = length;
			this.sort = sort;
			this.total = total;
			this.data = data;
			this.filed = filed;
		}
		
	    
	    
	    
}
