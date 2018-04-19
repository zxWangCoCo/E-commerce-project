package com.atguigu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.bean.T_MALL_SKU;
import com.atguigu.service.AttrService;
import com.atguigu.service.SkuService;

@Controller
public class SkuController {
	
	@Autowired
	private AttrService attrService;
	
	@Autowired
	private SkuService skuService;
	
	
	@RequestMapping("/save_sku")
	public ModelAndView save_sku(ModelMap map,T_MALL_PRODUCT spu,
			MODEL_T_MALL_SKU_ATTR_VALUE list_attr,T_MALL_SKU sku) {
		skuService.save_sku(spu, list_attr.getList_attr(), sku);
		ModelAndView mv = new ModelAndView("redirect:/goto_sku_add.do");
		mv.addObject("flbh1", spu.getFlbh1());
		mv.addObject("flbh2", spu.getFlbh2());
		return mv;
	}
	
	
	@RequestMapping("/goto_sku_add")
	public String goto_sku_add(@RequestParam("flbh1") int flbh1,@RequestParam("flbh2") int flbh2,ModelMap map) {
		List<OBJECT_T_MALL_ATTR> attr_list = attrService.get_attr_list(flbh2);
		map.put("attr_list", attr_list);
		map.put("flbh1", flbh1);
		map.put("flbh2", flbh2);
		return "skuAdd";
	}
}
