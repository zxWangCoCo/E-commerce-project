package com.atguigu.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_SKU;
import com.atguigu.bean.T_MALL_SKU_ATTR_VALUE;
import com.atguigu.mapper.ListMapper;
import com.atguigu.service.ListService;

@Service
public class ListServiceImpl implements ListService {

	@Autowired
	private ListMapper listMapper;

	@Override
	public List<OBJECT_T_MALL_SKU> get_list_by_flbh2(int flbh2) {
		return listMapper.select_list_by_flbh2(flbh2);
	}

	@Override
	public List<OBJECT_T_MALL_SKU> get_list_by_attr(List<T_MALL_SKU_ATTR_VALUE> 
		list_attr, int flbh2) {
		StringBuffer subsql = new StringBuffer(" ");
		// 根据属性值冬天拼接sql语句
	subsql.append(" and sku.id in ( select sku0.sku_id from ");
		// 集合的验空
	if (list_attr != null && list_attr.size() > 0) {
		// 拼接中间sql
	for (int i = 0; i < list_attr.size(); i++) {
	subsql.append(
	" (select sku_id from t_mall_sku_attr_value where shxm_id = " + list_attr.get(i).getShxm_id()
		+ " and shxzh_id = " + list_attr.get(i).getShxzh_id() + ") sku" + i + "");
			// 判断是否加,
		if ((i + 1) < list_attr.size() && list_attr.size() > 1) {
				subsql.append(" , ");
			}
		}
			// 判断where
			if (list_attr.size() > 1) {
				subsql.append(" where ");
				//判断连接条件
				for (int i = 0; i < list_attr.size(); i++) {
					if ((i + 1) < list_attr.size()) {
						subsql.append(" sku"+i+".sku_id = sku"+(i + 1)+".sku_id");
					//判断and
						if(	list_attr.size() > 2 && i < (list_attr.size()-2)) {
							subsql.append(" and ");
						}
					}
				}
			}
		}
		subsql.append(" )");
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		map.put("subsql", subsql.toString());
		map.put("flbh2", flbh2);
		List<OBJECT_T_MALL_SKU> list_sku = listMapper.select_list_by_attr(map);
		return list_sku;
	}
}
