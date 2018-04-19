package com.atguigu.utils;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class MyCallback implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		// 配置username/password,私人协议
		WSPasswordCallback wsc = (WSPasswordCallback) callbacks[0];
		String user = wsc.getIdentifier();
		String password = "";// 查询数据库
		if ("cxf".equals(user)) {
			password = "123";
		}
		wsc.setPassword(password);
	}
}
