package com.atguigu.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.service.CartService;
import com.atguigu.utils.MyJsonUtil;

@Controller
public class CartController {

	@Autowired
	private CartService cartService;

	@RequestMapping("change_shfxz")
	public String change_shfxz(HttpServletResponse response,@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,T_MALL_SHOPPINGCAR cart ,ModelMap map,HttpSession session) {
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		T_MALL_USER_ACCOUNT loginuser = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		//修改业务
		if(loginuser == null) {
			//修改cookie
			list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
		}else {
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
		}
		
		for (int i = 0; i < list_cart.size(); i++) {
			if(list_cart.get(i).getSku_id() == cart.getSku_id()) {
				//更新是否赋值属性
				list_cart.get(i).setShfxz(cart.getShfxz());
				if(loginuser == null) {
					//覆盖cookie
					Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(list_cart));
					// 设置cookie过期时间
					cookie.setMaxAge(60 * 60 * 24);
					response.addCookie(cookie);
				}else {
					cartService.update_cart(list_cart.get(i));
				}
			}
		}
		map.put("sum", get_sum(list_cart));
		map.put("list_cart", list_cart);
		return "cartListInner";
	}
	//计算总价
	private BigDecimal get_sum(List<T_MALL_SHOPPINGCAR> list_cart) {
		BigDecimal sum = new BigDecimal("0");
		for (int i = 0; i < list_cart.size(); i++) {
			if(list_cart.get(i).getShfxz().equals("1")) {
				//说明已选中，价格相加
				sum = sum.add(new BigDecimal(list_cart.get(i).getHj()+""));
			}
		}
		return sum;
	}
	
	/**
	 * 到购物车页面
	 * @param map
	 * @param session
	 * @param list_cart_cookie
	 * @return
	 */
	@RequestMapping("/goto_cart_lsit")
	public String goto_cart_lsit(ModelMap map, HttpSession session,
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie) {
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		// 获取session中的用户信息
		T_MALL_USER_ACCOUNT loginuser = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		// 判断用户是否登录
		if (loginuser == null) {
			// 客户没登录，从cookie中获取
			list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
		} else {
			// 从session中获取
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
		}
		map.put("list_cart", list_cart);
		map.put("sum", get_sum(list_cart));
		return "cartList";
	}
	
	/**
	 * 到迷你购物车
	 * @param map
	 * @param session
	 * @param list_cart_cookie
	 * @return
	 */
	@RequestMapping("/miniCart")
	public String minicart(ModelMap map, HttpSession session,
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie) {
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		// 获取session中的用户信息
		T_MALL_USER_ACCOUNT loginuser = (T_MALL_USER_ACCOUNT) session.getAttribute("user");
		// 判断用户是否登录
		if (loginuser == null) {
			// 客户没登录，从cookie中获取
			list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
		} else {
			// 从session中获取
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
		}
		map.put("list_cart", list_cart);
		return "minicartList";
	}

	// 添加购物车要使用转发，防止重复提交
	@RequestMapping("add_cart")
	public String add_cart(HttpServletResponse response,
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie, HttpSession session,
			T_MALL_SHOPPINGCAR cart, ModelMap map) {
		List<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		// 获取用户
		int yh_id = cart.getYh_id();
		// 判断用户是否登录
		if (yh_id == 0) {
			// 用户未登录,操作Cookie
			// 1.如果购物车为空，直接添加购物车
			if (StringUtils.isBlank(list_cart_cookie)) {
				// 放入集合
				list_cart.add(cart);
			} else {
				// 2.购物车不为空，则继续判断购物车是否重复
				// 通过cookie获取数据
				list_cart = MyJsonUtil.json_to_list(list_cart_cookie, T_MALL_SHOPPINGCAR.class);
				// 判断是购物车是否哦重复
				boolean b = if_new_cart(list_cart, cart);
				if (b) {
					// 新购物车
					list_cart.add(cart);
				} else {
					// 旧购物车
					for (int i = 0; i < list_cart.size(); i++) {
						if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
							list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + cart.getTjshl());
							list_cart.get(i).setHj(list_cart.get(i).getTjshl() * list_cart.get(i).getSku_jg());
							// 老车，更新
						}
					}
				}
			}
			// 覆盖，创建Cookie
			Cookie cookie = new Cookie("list_cart_cookie", MyJsonUtil.list_to_json(list_cart));
			// 设置cookie过期时间
			cookie.setMaxAge(60 * 60 * 24);
			//覆盖cookie，cookie要用覆盖的方式更新，因为取cookie时只是取到了cookie的地址
			response.addCookie(cookie);
		} else {
			// 客户已登录，通过session获取数据
			list_cart = (List<T_MALL_SHOPPINGCAR>) session.getAttribute("list_cart_session");
			// 用户已登录，判断数据库购物车中此用户的购物车车中是否有这个sku订单
			boolean b = cartService.if_cart_exsit(cart);
			//如果没有直接添加购物车
			if (!b) {
				cartService.add_cart(cart);
				//同步session
				//判断session中的购物车是否为空，如果为空直接创建并添加
				if (list_cart == null || list_cart.isEmpty()) {
					list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
					list_cart.add(cart);
					session.setAttribute("list_cart_session", list_cart);
				} else {
					//如果不为空，在原来的基础上添加新的cart
					list_cart.add(cart);
				}
			} else {
				//如果数据库中购物车中有这个订单，则就更新这个购物车，更新数量和价格
				for (int i = 0; i < list_cart.size(); i++) {
					if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
						list_cart.get(i).setTjshl(list_cart.get(i).getTjshl() + cart.getTjshl());
						list_cart.get(i).setHj(list_cart.get(i).getTjshl() * list_cart.get(i).getSku_jg());
						// 老车，更新
						cartService.update_cart(list_cart.get(i));
					}
				}
			}
		}
		//购物车不能用转发，用重定向，防止表单重复提交
		return "redirect:/cart_success.do";
	}

	private boolean if_new_cart(List<T_MALL_SHOPPINGCAR> list_cart, T_MALL_SHOPPINGCAR cart) {
		boolean b = true;
		for (int i = 0; i < list_cart.size(); i++) {
			if (list_cart.get(i).getSku_id() == cart.getSku_id()) {
				b = false;
			}
		}
		return b;
	}

	@RequestMapping("cart_success")
	public String cart_success() {
		return "cartSuccess";
	}

}
