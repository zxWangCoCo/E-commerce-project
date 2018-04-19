package com.atguigu.utils;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//配置多数据元，继承并实现AbstractRoutingDataSource抽象类的方法
public class MyRoutingDataSource extends AbstractRoutingDataSource{
	//为防止并发，必须使用ThreadLocal
	private static ThreadLocal<String> key = new ThreadLocal<String>();
	@Override
	protected Object determineCurrentLookupKey() {
		
		return this.key.get();
	}
	public static String getKey() {
		return key.get();
	}
	public static void setKey(String key_in) {
		key.set(key_in);
	}
}
