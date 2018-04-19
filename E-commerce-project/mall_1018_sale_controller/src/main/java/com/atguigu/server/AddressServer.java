package com.atguigu.server;

import java.util.List;

import javax.jws.WebService;

import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;

@WebService
public interface AddressServer {
	public List<T_MALL_ADDRESS> get_address(T_MALL_USER_ACCOUNT user);

	public T_MALL_ADDRESS get_address_by_addrId(int id);
}
