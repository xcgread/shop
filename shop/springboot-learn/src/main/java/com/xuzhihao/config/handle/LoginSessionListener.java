package com.xuzhihao.config.handle;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;

@Component
public class LoginSessionListener implements HttpSessionListener {

	public static int online = 0;

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("创建session");
		online++;
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("销毁session");

	}

}