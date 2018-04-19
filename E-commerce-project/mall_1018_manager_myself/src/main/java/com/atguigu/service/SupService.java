package com.atguigu.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.T_MALL_PRODUCT;

public interface SupService {
	
	void save_spu(T_MALL_PRODUCT spu, List<String> list_image);

	List<T_MALL_PRODUCT> get_spu_list(@Param("pp_id") int pp_id,@Param("flbh2") int flbh2);
}
