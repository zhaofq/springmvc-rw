package com.java.spring.test;
/**
* @author 作者:zhaofq
* @version 创建时间：2017年3月10日 下午4:58:14
* 类说明
*/
public class C{
	public static String aaa;
	public static String bbb = aaa + "b";
	public static void main(String[] args) {
		System.out.println(bbb);
		aaa = "a";
		System.out.println("----------"+aaa);
	}
}
