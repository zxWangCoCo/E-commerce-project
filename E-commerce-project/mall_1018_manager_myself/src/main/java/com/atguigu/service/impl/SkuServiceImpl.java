package com.atguigu.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mapper.SkuMapper;
import com.atguigu.service.SkuService;

@Service
public class SkuServiceImpl implements SkuService {
	@Autowired
	private SkuMapper skuMapper;
	

	@Override
	public void save_sku(T_MALL_PRODUCT spu, List<T_MALL_SKU_ATTR_VALUE> list_attr, T_MALL_SKU sku) {
		//保存sku,spu的id对应的是sku的shp_id
				sku.setShp_id(spu.getId());
				skuMapper.save_sku(sku);
				System.out.println(spu.getId());
				//根据sku返回的主键添加sku属性和属性值关联表
				HashMap<Object,Object> map = new HashMap<>();
				map.put("shp_id", spu.getId());
				//sku的id和关系表的sku_id是一致的
				map.put("sku_id", sku.getId());
				map.put("list_val", list_attr);
				skuMapper.save_sku_val(map);
	}

}
