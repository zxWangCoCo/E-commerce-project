package com.atguigu.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.atguigu.bean.OBJECT_T_MALL_FLOW;
import com.atguigu.bean.OBJECT_T_MALL_ORDER;
import com.atguigu.bean.T_MALL_ADDRESS;
import com.atguigu.bean.T_MALL_ORDER_INFO;
import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.exception.OverSaleException;
import com.atguigu.server.AddressServer;
import com.atguigu.service.CartService;
import com.atguigu.service.OrderService;

@Controller
@SessionAttributes("order")//将order保存到session中
public class OrderController {
	@Autowired
	private AddressServer addressServer;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderServcie;

	@RequestMapping("/goto_checkOrder")
	public String goto_check(HttpSession session, ModelMap map) {

		// 获取Session中的用户数据
		T_MALL_USER_ACCOUNT loginuser = (T_MALL_USER_ACCOUNT) session.getAttribute("user");

		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		// 判断用户是否登录
		if (loginuser == null) {
			// 用户没登录，重定向到登录页面
			return "redirect:/goto_loginOrder.do";
		} else {
			// 结算业务
			// 1.从Session中获取购物车列表
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
			// 生成主订单
			OBJECT_T_MALL_ORDER order = new OBJECT_T_MALL_ORDER();
			// 设置order属性
			order.setYh_id(loginuser.getId());
			order.setJdh(1);
			order.setZje(get_sum(list_cart));
			// 根据地址分类和选中状态分类（一个相同的地址，一个订单）,用HsahSet进行去重
			HashSet<String> set_kudz = new HashSet<>();
			for (int i = 0; i < list_cart.size(); i++) {
				if ("1".equals(list_cart.get(i).getShfxz())) {
					// 用HashSet进行地址去重
					set_kudz.add(list_cart.get(i).getKcdz());
				}
			}

			// 根据库存地址封装生成送货清单集合
			List<OBJECT_T_MALL_FLOW> list_flow = new ArrayList<>();
			// 遍历地址，一个地址一个清单
			Iterator<String> iterator = set_kudz.iterator();
			while (iterator.hasNext()) {
				String kudz = iterator.next();
				OBJECT_T_MALL_FLOW flow = new OBJECT_T_MALL_FLOW();

				// 生成送货清单
				flow.setMqdd("商品未出库");
				flow.setPsfsh("尚硅谷快递");
				flow.setYh_id(loginuser.getId());
				// 生成每个订单的信息，属于用户自己的信息
				ArrayList<T_MALL_ORDER_INFO> list_info = new ArrayList<T_MALL_ORDER_INFO>();

				for (int i = 0; i < list_cart.size(); i++) {
					if ("1".equals(list_cart.get(i).getShfxz()) && kudz.equals(list_cart.get(i).getKcdz())) {
						// 将cart对象转化成info对象
						T_MALL_SHOPPINGCAR cart = list_cart.get(i);
						T_MALL_ORDER_INFO info = new T_MALL_ORDER_INFO();
						info.setGwch_id(cart.getId());
						info.setShp_tp(cart.getShp_tp());
						info.setSku_id(cart.getSku_id());
						info.setSku_kcdz(kudz);
						info.setSku_mch(cart.getSku_mch());
						info.setSku_jg(cart.getSku_jg());
						info.setSku_shl(cart.getTjshl());
						list_info.add(info);
					}
				}
				// 添加
				flow.setList_info(list_info);
				// 将送货清单放到集合中
				list_flow.add(flow);
			}
			// 将送货清单放入主清单，内存中的对象，游离太对象
			order.setList_flow(list_flow);
			map.put("order", order);
			try {
				// 调用webSevice服务
				List<T_MALL_ADDRESS> list_address = addressServer.get_address(loginuser);
				map.put("list_address", list_address);
			} catch (Exception e) {
				e.getStackTrace();
				return "redirect:/orderErro.do";
			}

		}
		return "checkOrder";
	}
	
	@RequestMapping("/save_order")
	public String save_order(HttpSession session,@ModelAttribute("order") OBJECT_T_MALL_ORDER order,T_MALL_ADDRESS address ) {
		//获取用户
		T_MALL_USER_ACCOUNT loginuser = (T_MALL_USER_ACCOUNT)session.getAttribute("user");
		T_MALL_ADDRESS get_address = addressServer.get_address_by_addrId(address.getId());
		//保存业务订单
		orderServcie.save_order(order,get_address);
		//重新同步session
		session.setAttribute("list_cart_session",cartService.get_list_cart_by_user(loginuser) );
		
		return "realPay";
	}
	
	@RequestMapping("goto_pay")
	public String pay() {
		
		return "pay";
	}
	/**
	 * 
	 * @param order
	 * @return
	 */
	@RequestMapping("pay_success")
	public String pay_success(@ModelAttribute("order") OBJECT_T_MALL_ORDER order) {
		try {
			orderServcie.pay_success(order);
		} catch (OverSaleException e) {
			e.getStackTrace();
			return "redirect:/order_fail.do";
		}
		return "redirect:/order_success.do";
	}
	
	@RequestMapping("real_pay_success")
	@ResponseBody
	public String real_pay_success(@ModelAttribute("order") OBJECT_T_MALL_ORDER order) {
		try {
			orderServcie.pay_success(order);
		} catch (OverSaleException e) {
			e.getStackTrace();
			return "success";
		}
		return "success";
	}
	
	@RequestMapping("order_fail")
	public String order_fail() {
		return "orderFail";
	}
	
	@RequestMapping("order_success")
	public String order_success() {
		return "orderSuccess";
	}

	// 计算总价
	private BigDecimal get_sum(List<T_MALL_SHOPPINGCAR> list_cart) {
		BigDecimal sum = new BigDecimal("0");
		for (int i = 0; i < list_cart.size(); i++) {
			if (list_cart.get(i).getShfxz().equals("1")) {
				// 说明已选中，价格相加
				sum = sum.add(new BigDecimal(list_cart.get(i).getHj() + ""));
			}
		}
		return sum;
	}
}
