package com.atguigu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.mapper.AddressMapper;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public List<T_MALL_ADDRESS> get_address(T_MALL_USER_ACCOUNT user) {
		
		List<T_MALL_ADDRESS> lsit_address =addressMapper.select_address(user);
		return lsit_address;
	}

	@Override
	public T_MALL_ADDRESS get_address_by_addrId(int id) {
		T_MALL_ADDRESS address = addressMapper.select_address_by_addrId(id);
		return address;
	}

}
