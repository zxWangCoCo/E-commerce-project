package com.atguigu.server;

import javax.jws.WebService;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;

@WebService
public interface LoginServer {
	public String Login(T_MALL_USER_ACCOUNT user);
	public String Login2(T_MALL_USER_ACCOUNT user);
}
