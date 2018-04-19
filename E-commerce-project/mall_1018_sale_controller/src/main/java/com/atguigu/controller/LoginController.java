package com.atguigu.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.bean.T_MALL_USER_ACCOUNT;
import com.atguigu.server.LoginServer;
import com.atguigu.service.CartService;
import com.atguigu.utils.MyJsonUtil;

@Controller
public class LoginController {

	@Autowired
	private LoginServer loginServer;
	
	@Autowired
	private JmsTemplate jmsTemplate;

	// @Autowired
	// private TestServerInf testServer;

	@Autowired
	private CartService cartServcie;
	
	@Autowired
	private ActiveMQQueue queueDestination;

	// 注销用户
	@RequestMapping("/goto_loginout")
	public String goto_loginout(HttpSession session) {
		session.invalidate();
		return "redirect:/index.do";
	}

	@RequestMapping("/login")
	public String goto_login(@RequestParam(value = "redirect", required = false) String redirect,
			@CookieValue(value = "list_cart_cookie", required = false) String list_cart_cookie,
			HttpServletResponse response, T_MALL_USER_ACCOUNT user, HttpSession session, String dataSource_type)
			throws Exception {
		T_MALL_USER_ACCOUNT loginuser = new T_MALL_USER_ACCOUNT();
		String loginJson = "";
		if (dataSource_type.equals("1")) {
			// 远程调用用户登录（webservcie）
			loginJson = loginServer.Login(user);
			// 测试加密协议
			// testServer.ping("hello");
		} else if (dataSource_type.equals("2")) {
			// 远程调用用户登录（webservcie）
			loginJson = loginServer.Login2(user);
		}

		loginuser = MyJsonUtil.json_to_object(loginJson, T_MALL_USER_ACCOUNT.class);

		if (loginuser == null) {
			// 未登陆成功转发到登录页面
			return "redirect:/login.do";
		} else {
			// 保存用户名到request域
			session.setAttribute("user", loginuser);
			final String message = loginuser.getYh_nch() + "登录了";
			jmsTemplate.send(queueDestination, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					return session.createTextMessage(message);
				}
			});

			// 在客户端出巡用户个性化信息
			Cookie cookie = new Cookie("yh_mch", URLEncoder.encode(loginuser.getYh_mch(), "UTF-8"));
			// 设置cookie存放路径，一般都设就行
			cookie.setPath("/");
			// 设置cookie过期时间
			cookie.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie);

			Cookie cookie2 = new Cookie("yh_nch", URLEncoder.encode("周润发", "UTF-8"));
			// 设置cookie存放路径，一般都设就行
			cookie2.setPath("/");
			// 设置cookie过期时间
			cookie2.setMaxAge(60 * 60 * 24);
			response.addCookie(cookie2);
			// 登录成功，同步同步购物车
			combine_cart(loginuser, response, session, list_cart_cookie);

		}
		if (StringUtils.isBlank(redirect)) {
			return "redirect:/index.do";
		} else {
			return "redirect:/" + redirect;
		}

	}

	private void combine_cart(T_MALL_USER_ACCOUNT loginuser, HttpServletResponse response, HttpSession session,
			String list_cart_cookie) {
		ArrayList<T_MALL_SHOPPINGCAR> list_cart = new ArrayList<T_MALL_SHOPPINGCAR>();
		// 判断Cookie
		if (StringUtils.isBlank(list_cart_cookie)) {
			// cookie为空
		} else {
			// cookie不为空并且db是为空
			List<T_MALL_SHOPPINGCAR> list_cart_db = cartServcie.get_list_cart_by_user(loginuser);
			list_cart = (ArrayList<T_MALL_SHOPPINGCAR>) MyJsonUtil.json_to_list(list_cart_cookie,
					T_MALL_SHOPPINGCAR.class);

			for (int i = 0; i < list_cart.size(); i++) {
				T_MALL_SHOPPINGCAR cart = list_cart.get(i);
				// 定义客户id
				cart.setYh_id(loginuser.getId());
				boolean b = cartServcie.if_cart_exsit(list_cart.get(i));
				if (b) {
					// ture，说明cookie中有，更新
					for (int j = 0; j < list_cart_db.size(); j++) {
						if (cart.getSku_id() == list_cart_db.get(j).getSku_id()) {
							cart.setTjshl(cart.getTjshl() + list_cart_db.get(j).getTjshl());
							cart.setHj(cart.getTjshl() * cart.getSku_jg());
							// 老车，更新
							cartServcie.update_cart(cart);
						}
					}
				} else {
					cartServcie.add_cart(cart);
				}
			}
		}
		// 同步session,请空cookie
		session.setAttribute("list_cart_session", cartServcie.get_list_cart_by_user(loginuser));
		response.addCookie(new Cookie("list_cart_cookie", " "));
	}

	// 验证ajax异步提交表单
	@RequestMapping("/form_login")
	@ResponseBody
	public String form_login(T_MALL_USER_ACCOUNT suer) {
		return "login";
	}
}
