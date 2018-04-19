package com.atguigu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.bean.MODEL_T_MALL_ATTR;
import com.atguigu.bean.OBJECT_T_MALL_ATTR;
import com.atguigu.service.AttrService;
	

@Controller
public class AttrController {
	
	@Autowired
	private AttrService attrService;
	
	@RequestMapping("/goto_attr_add")
	public String goto_attr_add(ModelMap map,int flbh2) {
		map.put("flbh2", flbh2);
		return "attrAdd";
	}
	@ResponseBody
	@RequestMapping("/get_attr_list_json")
	public Object get_attr_list_json(ModelMap map,int flbh2) {
		List<OBJECT_T_MALL_ATTR> list_attr = new ArrayList<OBJECT_T_MALL_ATTR>();
		list_attr = attrService.get_attr_list(flbh2);
//		map.put("flbh2", flbh2);
//		map.put("list_attr", list_attr);
		return list_attr;
	}
	
	//此ajax返回的页面，没有@ResponseBody
	@RequestMapping("/get_attr_list")
	public String get_attr_list(ModelMap map,int flbh2) {
		List<OBJECT_T_MALL_ATTR> list_attr = new ArrayList<OBJECT_T_MALL_ATTR>();
		list_attr = attrService.get_attr_list(flbh2);
		map.put("flbh2", flbh2);
		map.put("list_attr", list_attr);
		return "attrListInner";
	}
	
	@RequestMapping("/attr_add")//重定向到添加页面(选项卡)
	public ModelAndView attr_add(int flbh2,MODEL_T_MALL_ATTR list_attr) {
		//项数据库中保存属性
		attrService.sava_attr(flbh2,list_attr.getList_attr());
		//先发请求index.do,到indexController在做处理
		ModelAndView mv = new ModelAndView("redirect:/index.do");
		//mv.addObject("flbh2", flbh2);
		mv.addObject("url", "goto_attr_add.do?flbh2="+flbh2);
		mv.addObject("title", "添加属性");
		return mv;
	}
}
