package com.atguigu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.bean.T_MALL_VALUE;

public interface AttrMapper {

	void insert_attr(@Param("calss_2_id") int calss_2_id,
			@Param("attr") OBJECT_T_MALL_ATTR attr);

	void insert_values(@Param("attr_id") int attr_id, @Param("list_value") List<T_MALL_VALUE> list_value);

	List<OBJECT_T_MALL_ATTR> select_attr_list(@Param("flbh2") int flbh2);
	
	List<T_MALL_VALUE> select_value_list(Integer id);

}
