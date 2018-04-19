package com.atguigu.service;

import java.util.List;

import com.atguigu.bean.DETIAL_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;

public interface ItemService {

	DETIAL_T_MALL_SKU get_sku_detail(int sku_id);

	List<T_MALL_SKU> get_sku_list_by_spu(int spu_id);

}
