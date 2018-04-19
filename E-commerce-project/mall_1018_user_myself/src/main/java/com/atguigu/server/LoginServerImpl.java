package com.atguigu.server;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.LoginService;
import com.atguigu.utils.MyRoutingDataSource;
import com.google.gson.Gson;

public class LoginServerImpl implements LoginServer {
	@Autowired
	private LoginService loginServcie;

	@Override
	@Path("login")
	@GET
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public String Login(@BeanParam T_MALL_USER_ACCOUNT user) {
		//由于增加多数据源事务，需要在调用业务层方法之前切换数据源
		MyRoutingDataSource.setKey("1");
		T_MALL_USER_ACCOUNT loginuser = loginServcie.login(user);
		Gson gson = new Gson();
		return gson.toJson(loginuser);
	}

	@Override
	@Path("login")
	@GET
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public String Login2(@BeanParam T_MALL_USER_ACCOUNT user) {
		MyRoutingDataSource.setKey("2");
		T_MALL_USER_ACCOUNT loginuser = loginServcie.login2(user);
		Gson gson = new Gson();
		return gson.toJson(loginuser);
	}
}
