package com.atguigu.utils;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySqlSessionFactory {
	/**
	 * 获取SqlSessionFatoin,注意更换mapper
	 * @return
	 */
	public static SqlSessionFactory getSqlSessionFB() {
	InputStream inputStream =
			MySqlSessionFactory.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory =
			new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
}
