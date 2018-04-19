package com.atguigu.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyPropertiesUtil {
	/**
	 * 
	 * @param properties 配置文件名称
	 * @param key 配置文件中的key值
	 * @return
	 */
	public static String getMyProperty(String properties, String key) {
		Properties properties2 = new Properties();
		InputStream resourceAsStream = MyPropertiesUtil.class.getClassLoader().getResourceAsStream(properties);
		try {
			properties2.load(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String property = properties2.getProperty(key);
		return property;
	}

}
