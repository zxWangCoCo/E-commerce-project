package com.atguigu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.KEYWORDS_T_MALL_SKU;
import com.atguigu.bean.MODEL_T_MALL_SKU_ATTR_VALUE;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.service.AttrService;
import com.atguigu.service.ListService;
import com.atguigu.utils.JedisPoolUtils;
import com.atguigu.utils.MyCacheUtil;
import com.atguigu.utils.MyHttpGetUtil;
import com.atguigu.utils.MyJsonUtil;
import com.atguigu.utils.MyPropertiesUtil;

import redis.clients.jedis.Jedis;

@Controller
public class ListController {
	@Autowired
	private ListService listService;

	@Autowired
	private AttrService attrService;
	
	@RequestMapping("keywords")
	public String keywords(String keywords,ModelMap map) throws Exception {
		//远程调用keywords服务
		 String strJson = "";
		 //通过HTTPClient使用java代码模拟浏览器发送请求
		 strJson = MyHttpGetUtil.doGet(MyPropertiesUtil.getMyProperty("ws.properties", "keywords_url")+"?keywords="+keywords);
		List<KEYWORDS_T_MALL_SKU> list_sku = MyJsonUtil.json_to_list(strJson,KEYWORDS_T_MALL_SKU.class );
		map.put("list_sku", list_sku);
		 map.put("keywords", keywords);
		return "search";
	}

	@RequestMapping("/goto_list")
	public String goto_list(int flbh2, ModelMap map) {
		// flbh2的属性列表
		List<OBJECT_T_MALL_ATTR> attr_list = attrService.get_attr_list(flbh2);
		List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<OBJECT_T_MALL_SKU>();
		String key = "class_2_" + flbh2;
		// 先从redis中查询是否有
		list_sku = MyCacheUtil.getList(key, OBJECT_T_MALL_SKU.class);
		// 如果没有再从mySQL中查询
		if (list_sku == null || list_sku.size() < 1) {
			// flbh2的商品列表,mySql检索
			list_sku = listService.get_list_by_flbh2(flbh2);
			// 将数据库中查到的数据同步到redis
			MyCacheUtil.setKey(key, list_sku);
		}
		map.put("attr_list", attr_list);
		map.put("list_sku", list_sku);
		map.put("flbh2", flbh2);
		return "list";
	}

	@RequestMapping("/get_list_by_attr")
	public String get_list_by_attr(MODEL_T_MALL_SKU_ATTR_VALUE attr_list, int flbh2, ModelMap map) {
		List<OBJECT_T_MALL_SKU> list_sku = new ArrayList<>();
		// 先查询redis中的信息
		List<T_MALL_SKU_ATTR_VALUE> list_attr = attr_list.getList_attr();
		String[] keys = new String[list_attr.size()];
		for (int i = 0; i < list_attr.size(); i++) {
			//拼接key
			keys[i] = "attr_"+ flbh2+"_" + list_attr.get(i).getShxm_id() + "_" + list_attr.get(i).getShxzh_id();
		}
		// 生成动态的keys
		
		// 获取keys数组值的交集的键
		String interKeys = MyCacheUtil.getInterKeys(keys);
		// 根据获取交集的键获取redis中的值
		list_sku = MyCacheUtil.getList(interKeys, OBJECT_T_MALL_SKU.class);
		if (list_sku == null || list_sku.size() < 1) {
			list_sku = listService.get_list_by_attr(attr_list.getList_attr(), flbh2);
			try {
				Jedis jedis = JedisPoolUtils.getJedis();
				// 向redis中插入数据
				for (int i = 0; i < list_sku.size(); i++) {
					for (int j = 0; j < list_attr.size(); j++) {
						jedis.zadd(keys[j], i, MyJsonUtil.object_to_json(list_sku.get(i)));
					}
				} 
			} catch (Exception e) {
				
			}
		}
		map.put("list_sku", list_sku);
		return "skuList";
	}
}
