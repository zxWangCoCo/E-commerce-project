package com.atguigu.mapper;

import java.util.HashMap;
import java.util.List;

import com.atguigu.bean.DETIAL_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;

public interface ItemMapper {

	DETIAL_T_MALL_SKU select_sku_detail(HashMap<Object,Object> map);

	List<T_MALL_SKU> select_sku_list_by_spu( int spu_id);
	
}
