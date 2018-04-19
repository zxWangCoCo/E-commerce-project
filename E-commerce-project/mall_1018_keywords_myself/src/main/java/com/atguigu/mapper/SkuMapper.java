package com.atguigu.mapper;

import java.util.HashMap;

import com.atguigu.bean.T_MALL_SKU;

public interface SkuMapper {

	void save_sku(T_MALL_SKU sku);

	void save_sku_val(HashMap<Object, Object> map);

}
