package com.xuzhihao.shop.webservice;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.xuzhihao.shop.common.api.CommonResult;
import com.xuzhihao.shop.webservice.config.SoapParseBean;
import com.xuzhihao.shop.webservice.config.SoapParseBeanMapping;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;

/**
 * 接口实现com.xuzhihao.shop.webservice
 *
 */
@WebService(serviceName = "CommonService", // 与接口中指定的name一致
		targetNamespace = "http://webservice.shop.xuzhihao.com/", // 与接口中的命名空间一致,一般是接口的包名倒
		endpointInterface = "com.xuzhihao.shop.webservice.CommonService"// 接口地址
)
@Component
public class CommonServiceImp implements CommonService {

	@Autowired
	private SoapParseBeanMapping soapParseBeanMapping;

	@Autowired
	private ApplicationContext applicationContext;

	@SuppressWarnings("unchecked")
	@Override
	public String doServices(String action, String data) {
		SoapParseBean actionObject = soapParseBeanMapping.getFunctionMap().get(action);// 根据业务类型获取当前信息
		String rtn = "";
		if (StrUtil.isEmpty(action) || ObjectUtil.isNull(actionObject))
			return JSONUtil.toJsonStr(CommonResult.unregistered());
		Map<String, String> parameter = new HashMap<String, String>();
		try {
			parameter = JSONUtil.toBean(data, Map.class);
		} catch (Exception e) {
			return JSONUtil.toJsonStr(CommonResult.msgformaterror());
		}
		Object exObject = applicationContext.getBean(actionObject.getBeanId());
		Class<?> type = exObject.getClass();
		try {
			Method method = type.getMethod(actionObject.getBeanMethod(), (Class<?>) Map.class);
			rtn = JSONUtil.toJsonStr(method.invoke(exObject, parameter));
		} catch (Exception e) {
			e.printStackTrace();
			return JSONUtil.toJsonStr(CommonResult.failed(e.getMessage()));
		}
		return rtn;
	}

}