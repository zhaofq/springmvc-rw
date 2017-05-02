package com.java.spring.util.dynamicDataSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* @author 作者:zhaofq
* @version 创建时间：2017年4月14日 下午4:10:00
* 类说明
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
	String value();
}
