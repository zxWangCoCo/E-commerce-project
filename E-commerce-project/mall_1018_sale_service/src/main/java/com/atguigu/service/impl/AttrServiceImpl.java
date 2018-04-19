package com.atguigu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.mapper.AttrMapper;
import com.atguigu.service.AttrService;

@Service
public class AttrServiceImpl implements AttrService {
	
	@Autowired
	private AttrMapper attrMapper;

	@Override
	public void sava_attr(int flbh2, List<OBJECT_T_MALL_ATTR> list_attr) {
		for (int i = 0; i < list_attr.size(); i++) {
			//插入属性，返回主键
			OBJECT_T_MALL_ATTR attr = list_attr.get(i);
			attrMapper.insert_attr(flbh2,attr);
			//获得主键出入属性值
			attrMapper.insert_values(attr.getId(),attr.getList_value());
		}
	}

	@Override
	public List<OBJECT_T_MALL_ATTR> get_attr_list(int flbh2) {
		List<OBJECT_T_MALL_ATTR> attr_list = attrMapper.select_attr_list(flbh2);
		return attr_list;
	}
}
