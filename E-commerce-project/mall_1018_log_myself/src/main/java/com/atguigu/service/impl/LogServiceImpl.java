package com.atguigu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.mapper.LogMapper;
import com.atguigu.service.LogService;

@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
	private LogMapper logMapper;
	
	@Override
	public void saveLogMess(String massage) {
		logMapper.saveLogMessage(massage);
	}

}
