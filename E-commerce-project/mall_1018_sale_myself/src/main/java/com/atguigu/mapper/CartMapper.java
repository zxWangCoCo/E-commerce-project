package com.atguigu.mapper;

import java.util.List;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;

public interface CartMapper {

	void add_cart(T_MALL_SHOPPINGCAR cart);

	void update_cart(T_MALL_SHOPPINGCAR cart);

	int select_cart_exist(T_MALL_SHOPPINGCAR cart);

	List<T_MALL_SHOPPINGCAR> select_list_cart_by_user(T_MALL_USER_ACCOUNT loginuser);
}
