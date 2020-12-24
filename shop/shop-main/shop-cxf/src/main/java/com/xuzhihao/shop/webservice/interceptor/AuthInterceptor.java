package com.xuzhihao.shop.webservice.interceptor;

import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import lombok.extern.slf4j.Slf4j;

/**
 * 定义服务端用户名密码校验的拦截器
 */
@Slf4j
public class AuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "admin";

	public AuthInterceptor() {
		// 定义在哪个阶段进行拦截
		super(Phase.PRE_PROTOCOL);
	}

	@Override
	public void handleMessage(SoapMessage soapMessage) throws Fault {
		log.info("come to auth interceptor...");
		// 获取SOAP消息的所有Header
		List<Header> headers = soapMessage.getHeaders();
		if (headers == null || headers.size() < 1) {
			throw new Fault(new IllegalArgumentException("未找到Header"));
		}
		// 获取Header携带是用户和密码信息
		Header firstHeader = headers.get(0);
		Element element = (Element) firstHeader.getObject();

		NodeList userNameElement = element.getElementsByTagName("username");
		NodeList passwordElement = element.getElementsByTagName("password");

		if (userNameElement.getLength() != 1) {
			throw new Fault(new IllegalArgumentException("用户名格式错误"));
		}

		if (passwordElement.getLength() != 1) {
			throw new Fault(new IllegalArgumentException("用户密码格式错误"));
		}

		// 获取元素的文本内容
		String userName = userNameElement.item(0).getTextContent();
		String password = passwordElement.item(0).getTextContent();

		// 实际项目中, 应该去查询数据库, 该用户名,密码是否被授权调用该webservice
		if (!userName.equals(USERNAME) || !password.equals(PASSWORD)) {
			throw new Fault(new IllegalArgumentException("用户名或密码不正确"));
		}
	}

}
