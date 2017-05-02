package com.java.spring.util.dynamicDataSource;
/**
* @author 作者:zhaofq
* @version 创建时间：2017年4月14日 下午4:22:41
* 类说明
*/
public class DynamicDataSourceHolder {
	
	
	    public static final ThreadLocal<String> holder = new ThreadLocal<String>();
	    public static void putDataSource(String name) {
	        holder.set(name);
	    }

	    public static String getDataSouce() {
	        return holder.get();
	    }
}
