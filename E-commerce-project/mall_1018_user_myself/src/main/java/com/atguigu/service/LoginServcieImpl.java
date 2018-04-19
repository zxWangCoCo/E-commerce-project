package com.atguigu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mapper.LoginMapper;
import com.atguigu.utils.MyRoutingDataSource;

@Service
public class LoginServcieImpl implements LoginService {
	@Autowired
	private LoginMapper loginMapper;
	@Override
	public T_MALL_USER_ACCOUNT login(T_MALL_USER_ACCOUNT user) {
		T_MALL_USER_ACCOUNT loginuser = loginMapper.login(user);
		return loginuser;
	}
	@Override
	public T_MALL_USER_ACCOUNT login2(T_MALL_USER_ACCOUNT user) {
		T_MALL_USER_ACCOUNT loginuser = loginMapper.login(user);
		return loginuser;
	}
	
}
