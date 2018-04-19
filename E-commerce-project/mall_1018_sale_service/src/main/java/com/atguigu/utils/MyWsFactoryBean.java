package com.atguigu.utils;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.FactoryBean;

public class MyWsFactoryBean<T> implements FactoryBean<T> {

	private String url;
	private Class<T> t;

	/**
	 * 
	 * @param url
	 *            访问地址
	 * @param t
	 *            server接口的.class
	 * @return 返回接口
	 */
	public static <T> T getMyWs(String url, Class<T> t) {
		//创建JaxWsProxyFactoryBean工厂实例
		JaxWsProxyFactoryBean jaxbean = new JaxWsProxyFactoryBean();
		//设置url访问地址
		jaxbean.setAddress(url);
		//设置接口
		jaxbean.setServiceClass(t);
		T server = (T) jaxbean.create();
		return server;
	}

	@Override
	public T getObject() throws Exception {

		return getMyWs(url, this.t);
	}

	@Override
	public Class<?> getObjectType() {
		return this.t;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Class<T> getT() {
		return t;
	}

	public void setT(Class<T> t) {
		this.t = t;
	}

}
