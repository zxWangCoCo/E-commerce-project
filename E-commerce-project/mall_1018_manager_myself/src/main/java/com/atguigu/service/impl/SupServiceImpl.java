package com.atguigu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.T_MALL_PRODUCT;
import com.atguigu.mapper.SupMapper;
import com.atguigu.service.SupService;

@Service
public class SupServiceImpl implements SupService {
	@Autowired
	private SupMapper supMapper;
	@Override
	public void save_spu(T_MALL_PRODUCT spu, List<String> list_image) {
		//返回主键到spu
		//设置spu的商品图片
		spu.setShp_tp(list_image.get(0));
		//插入spu信息
		supMapper.insert_spu(spu);
		//根据spu的主键插入spu图片
		Map<Object ,Object> map = new HashMap<Object , Object>();
		map.put("shp_id", spu.getId());
		map.put("list_image", list_image);
		supMapper.insert_images(map);
	}
	@Override
	public List<T_MALL_PRODUCT> get_spu_list(int pp_id, int flbh2) {
		List<T_MALL_PRODUCT> spu_list = supMapper.select_spu_list(pp_id,flbh2);
		return spu_list;
	}
}
