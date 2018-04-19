package com.atguigu.util;

import java.io.InputStream;
import java.util.Properties;

public class MyPropertyUtil {
	
	public static String getPropert(String propert, String key) throws Exception {
		Properties properties = new Properties();
		InputStream stream = MyPropertyUtil.class.getClassLoader().getResourceAsStream(propert);
		properties.load(stream);
		String property = properties.getProperty(key);
		return property;
	}

}
