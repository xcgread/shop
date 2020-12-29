package com.xuzhihao.config.handle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.xuzhihao.config.YmlConfig;
import com.xuzhihao.domain.SessionInfo;

@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {

	@Autowired
	private YmlConfig config;

	/**
	 * 在调用controller具体方法前拦截
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("进入Login拦截器");
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = requestUri.substring(contextPath.length());
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
		if (!config.getNoAuthUrls().contains(url)) {// 需要拦截认证的页面
			if ((sessionInfo == null) || (sessionInfo.getId() == null)) {
				response.sendRedirect(request.getContextPath() + "/"); // 未登录自动跳转界面
				return false;
			}
		}
		return true;
	}
}