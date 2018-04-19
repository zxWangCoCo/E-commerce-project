package com.atguigu.mapper;

import java.util.Map;

import com.atguigu.bean.OBJECT_T_MALL_FLOW;
import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ORDER_INFO;

public interface OrderMapper {

	void insert_order(Map<Object, Object> map);

	void insert_flow(Map<Object, Object> flow_map);

	void insert_info(Map<Object, Object> info_map);

	void delete_carts(Map<Object, Object> cart_map);

	long select_kc(T_MALL_ORDER_INFO info);
	/**
	 * 查询info的库存是否大于10，大于10，返回1，小于10,返回0
	 * @param info
	 * @return
	 */
	long select_count_ck(T_MALL_ORDER_INFO info);

	long select_kc_lock(T_MALL_ORDER_INFO info);

	void update_flow(OBJECT_T_MALL_FLOW flow);

	void update_sku(T_MALL_ORDER_INFO info);

	void update_order(OBJECT_T_MALL_ORDER order);
}
