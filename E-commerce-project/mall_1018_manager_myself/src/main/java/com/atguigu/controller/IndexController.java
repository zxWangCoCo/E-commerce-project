package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/goto_sku")
	public String goto_sku() {
		return "sku";
	}

	@RequestMapping("/goto_spu")
	public String goto_spu() {
		return "spu";
	}
	
	@RequestMapping("/index")
	public String index(String url ,String title,ModelMap map) {
		//获取后台转发过来的url和title用于直接发送请求到上次添加徐娘卡
		map.put("url", url);
		map.put("title", title);
		return "main";
	}
	
	@RequestMapping("/goto_attr")
	public String goto_attr() {
		return "attr";
	}
}
