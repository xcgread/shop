package com.xuzhihao.shop.webservice.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xuzhihao.shop.webservice.CommonService;
import com.xuzhihao.shop.webservice.config.SoapParseBeanMapping;
import com.xuzhihao.shop.webservice.service.UserService;

import cn.hutool.json.JSONUtil;

/**
 * 测试cxf客户端
 * 
 * @author Administrator
 *
 */
@RestController
public class ClientController {
	private static String address = "http://localhost:8080/webservice/gateway?wsdl";
	@Autowired
	private UserService userService;
	@Autowired
	private SoapParseBeanMapping soapParseBeanMapping;

	/**
	 * 容器内容对象正常注入
	 * 
	 * @return
	 */
	@RequestMapping(value = "/test1")
	public String test1() {
		userService.act200001(null);
		return "" + soapParseBeanMapping.getFunctionMap();
	}

	/**
	 * 方式1:使用代理类工厂,需要拿到对方的接口
	 */
	@RequestMapping(value = "/test2")
	public String test2() {
		// 代理工厂
		JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
		// 设置代理地址
		jaxWsProxyFactoryBean.setAddress(address);
		// 添加用户名密码拦截器
		jaxWsProxyFactoryBean.getOutInterceptors().add(new AddHeaderInterceptor("root", "admin"));
		// 设置接口类型
		jaxWsProxyFactoryBean.setServiceClass(CommonService.class);
		// 创建一个代理接口实现
		CommonService cs = (CommonService) jaxWsProxyFactoryBean.create();
		// 数据准备
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", "admin");
		map.put("password", "123456");
		return cs.doServices("act300001", JSONUtil.toJsonStr(map));
	}

	/**
	 * 动态调用方式
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/test3")
	public String test3() throws Exception {
		// 创建动态客户端
		JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
		Client client = dcf.createClient(address);
		// 需要密码的情况需要加上用户名和密码
		client.getOutInterceptors().add(new AddHeaderInterceptor("root", "admin"));
		String[] params = { "100001", "{\"username\":\"admin\",\"password\":\"123456\"}" };
		Object[] objects = client.invoke("doServices", params);
		return objects[0] + "";

	}
}