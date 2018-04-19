package com.atguigu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.OBJECT_T_MALL_FLOW;
import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.exception.OverSaleException;
import com.atguigu.mapper.OrderMapper;
import com.atguigu.service.OrderService;
import com.atguigu.utils.MyDateUtil;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;

	@Override
	public void save_order(OBJECT_T_MALL_ORDER order, T_MALL_ADDRESS get_address) {
		//用于保存插入的选中的购物
		List<Integer> list_id = new ArrayList<Integer>();
		
		//保存order
		Map<Object , Object > map = new HashMap<>();
		map.put("order", order);
		map.put("address", get_address);
		orderMapper.insert_order(map);
		
		//保存flow
		List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
		for (int i = 0; i < list_flow.size(); i++) {
			//获取每个物流信息
			OBJECT_T_MALL_FLOW flow = list_flow.get(i);
			//设置属性
			flow.setMdd(get_address.getYh_dz());
			flow.setDd_id(order.getId());
			HashMap<Object, Object> flow_map = new HashMap<>();
			flow_map.put("flow", flow);
			orderMapper.insert_flow(flow_map);
			
			//保存info
			List<T_MALL_ORDER_INFO> list_info = flow.getList_info();
			Map<Object, Object> info_map = new HashMap<>();
				info_map.put("list_info", list_info);
				info_map.put("flow_id", flow.getId());
				info_map.put("dd_id", order.getId());
				orderMapper.insert_info(info_map);
			
				//保存所有的插入的购物车id，用于删除
				for (int j = 0; j < list_info.size(); j++) {
					list_id.add(list_info.get(j).getGwch_id());
				}
		}
		//删除已经生成订单的购物车对象
		Map<Object, Object> cart_map = new HashMap<>();
		cart_map.put("list_id", list_id);
		orderMapper.delete_carts(cart_map);
	}

	@Override
	public void pay_success(OBJECT_T_MALL_ORDER order) throws OverSaleException  {
		//第一次修改 更改订单里的进度号为2以及预计送到时间
		orderMapper.update_order(order);
		//修改物流信息
		List<OBJECT_T_MALL_FLOW> list_flow = order.getList_flow();
		for (int i = 0; i < list_flow.size(); i++) {
			OBJECT_T_MALL_FLOW flow = list_flow.get(i);
			//修改物流业务，调mapper
			flow.setPsmsh("商品正在出库");
			//当期时间加一天开始派送
			flow.setPsshj(MyDateUtil.getMyDate(1));
			flow.setYwy("佐助");
			flow.setLxfsh("13843838438");
			orderMapper.update_flow(flow);
			List<T_MALL_ORDER_INFO> list_info = flow.getList_info();
			for (int j = 0; j < list_info.size(); j++) {
				T_MALL_ORDER_INFO info = list_info.get(j);
				//获取警戒线，判断是否执行带锁的查询sql
				long count = orderMapper.select_count_ck(info);
				//定义库存
				long kc = 0;
				if(count==0) {
					//执行带锁sql
					//获取库存的数量(带锁查询)
					kc = orderMapper.select_kc_lock(info);
				}else {
					//执行不带锁sql
					//获取库存的数量(带锁查询)
					kc = orderMapper.select_kc(info);
				}
				if(kc > info.getSku_shl()) {
					//对kc进行修改
					//修改sku数据量和销量等信息
					orderMapper.update_sku(info);
				}else {
					throw new OverSaleException("Over Sale");
				}
			}
		}
		//第二次修改Order
		//修改预计送达时间，当前时间+3(三天以后送达)
		order.setYjsdshj(MyDateUtil.getMyDate(3));
		orderMapper.update_order(order);
	}
}
