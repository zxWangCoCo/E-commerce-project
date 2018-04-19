package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;

public interface AddressService {

	List<T_MALL_ADDRESS> get_address(T_MALL_USER_ACCOUNT user);

	T_MALL_ADDRESS get_address_by_addrId(int id);

}
