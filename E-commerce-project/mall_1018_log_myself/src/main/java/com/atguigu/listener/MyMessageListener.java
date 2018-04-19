package com.atguigu.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.atguigu.mapper.LogMapper;
/**
 * 监听登录队列消息
 * @author Administrator
 *
 */
public class MyMessageListener implements MessageListener {

	@Autowired
	private LogMapper loginMapper;

	@Override
	public void onMessage(Message message) {
		// 消息内容
		TextMessage textMessage = (TextMessage) message;
		String massage = null;
		try {
			massage = textMessage.getText();
			// 保存yoghurt信息
			loginMapper.saveLogMessage(massage);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
