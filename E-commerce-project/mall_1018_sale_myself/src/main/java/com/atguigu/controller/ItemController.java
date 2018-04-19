package com.atguigu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.DETIAL_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemvice;
	
	@RequestMapping("/goto_sku_detail")
	public String goto_sku_detail(int sku_id , int spu_id, ModelMap map) {
		//查询商品详细信息
		DETIAL_T_MALL_SKU obj_sku = itemvice.get_sku_detail(sku_id);
		//查询spu下其他商品信息集合
		List<T_MALL_SKU> list_sku = itemvice.get_sku_list_by_spu(spu_id);
		map.put("obj_sku", obj_sku);
		map.put("list_sku", list_sku);
		return "skuDetial";
	}
}
