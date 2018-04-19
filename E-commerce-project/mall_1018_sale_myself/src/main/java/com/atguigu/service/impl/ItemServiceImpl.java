package com.atguigu.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.DETIAL_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.mapper.ItemMapper;
import com.atguigu.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Override
	public DETIAL_T_MALL_SKU get_sku_detail(int sku_id) {
		//使用map，增加扩展性
		HashMap<Object , Object > map = new HashMap<>();
		map.put("sku_id", sku_id);
		DETIAL_T_MALL_SKU obj_sku = itemMapper.select_sku_detail(map);
		return obj_sku;
	}

	@Override
	public List<T_MALL_SKU> get_sku_list_by_spu(int spu_id) {
		List<T_MALL_SKU> list_sku =	itemMapper.select_sku_list_by_spu(spu_id);
		return list_sku;
	}

}
