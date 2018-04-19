package com.atguigu.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/orderErro")
	public String orderErro() {
		return "orderErro";
	}
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request,ModelMap map) {
		//获取cookie
		String yh_mch = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length != 0) {
			for (int i = 0; i < cookies.length; i++) {
				String name = cookies[i].getName();
				if(name.equals("yh_mch")) {
					yh_mch = cookies[i].getValue();
					
				}
			}
		}
		map.put("yh_mch", yh_mch);
		return "index";
	}
	
	@RequestMapping("/goto_login")
	public String goto_login() {
		return "login";
	}
	
	@RequestMapping("/goto_loginOrder")
	public String goto_loginOrder() {
		return "loginOrder";
	}
	@RequestMapping("/ajax")
	public String test_ajax() {
		return "TestAjax";
	}
	
	
}
