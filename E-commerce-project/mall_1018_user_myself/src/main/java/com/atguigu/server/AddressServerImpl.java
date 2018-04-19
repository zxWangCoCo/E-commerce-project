package com.atguigu.server;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.AddressService;
import com.google.gson.Gson;

public class AddressServerImpl implements AddressServer {
	@Autowired
	private AddressService addressService;
	
	@Override
	@Path("getAddress")
	@GET
	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/json")
	public List<T_MALL_ADDRESS> get_address(@BeanParam T_MALL_USER_ACCOUNT user) {
		List<T_MALL_ADDRESS> lsit_address = addressService.get_address(user);
		Gson gson = new Gson();
		return lsit_address;
	}

	@Override
	public T_MALL_ADDRESS get_address_by_addrId(int id) {
		T_MALL_ADDRESS address = addressService.get_address_by_addrId(id);
		return address;
	}
}
