package com.java.spring.util.dynamicDataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
* @author 作者:zhaofq
* @version 创建时间：2017年4月14日 下午4:21:59
* 类说明
*/
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		 return DynamicDataSourceHolder.getDataSouce();
	}

}
