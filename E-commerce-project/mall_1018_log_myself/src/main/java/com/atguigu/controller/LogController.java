package com.atguigu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.service.LogService;

@Controller
public class LogController {
	
	@Autowired
	private LogService logService;
	
	@RequestMapping("/loginLog")
	public String loginLog(String massage) {
		logService.saveLogMess(massage);
		return "loginMessage";
	}
}
