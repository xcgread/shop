package com.xuzhihao.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * @author admin.
 * @Description 项目启动初始化类 相当于web.xml
 * @date 2018/4/26.
 *
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final Log log = LogFactory.get();

	/**
	 * spring 根容器
	 * 
	 * @return
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		log.info("------root配置类初始化------");
		return new Class<?>[] { RootConfig.class };
	}

	/**
	 * Spring mvc容器
	 * 
	 * @return
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		log.info("------web配置类初始化------");
		return new Class<?>[] { WebMvcConfig.class };
	}

	/**
	 * DispatcherServlet映射,从"/"开始
	 * 
	 * @return
	 */
	@Override
	protected String[] getServletMappings() {
		log.info("------映射根路径初始化------");
		return new String[] { "/" };
	}

}