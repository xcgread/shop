package com.xuzhihao.shop.webservice.config;

import java.beans.Introspector;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import com.xuzhihao.shop.webservice.annotation.SoapMapping;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 后置处理器处理SOAP绑定方法
 */
@Component
@Slf4j
public class SoapBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	private static HashMap<String, SoapParseBean> functionMap = new HashMap<String, SoapParseBean>();

	protected static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

	/**
	 * 根据扫描包的配置 加载需要检查的方法
	 */
	private static void loadCheckClassMethods(String scanPackages) {
		String[] scanPackageArr = scanPackages.split(",");
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
		for (String basePackage : scanPackageArr) {
			if (StrUtil.isBlank(basePackage)) {
				continue;
			}
			String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
					+ ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage))
					+ "/" + DEFAULT_RESOURCE_PATTERN;
			try {
				Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
				for (Resource resource : resources) {
					// 检查resource，这里的resource都是class
					loadClassMethod(metadataReaderFactory, resource);
				}
			} catch (Exception e) {
				log.error("初始化SensitiveWordInterceptor失败", e);
			}

		}
	}

	/**
	 * 加载资源，判断里面的方法
	 *
	 * @param metadataReaderFactory spring中用来读取resource为class的工具
	 * @param resource              这里的资源就是一个Class
	 * @throws IOException
	 */
	private static void loadClassMethod(MetadataReaderFactory metadataReaderFactory, Resource resource)
			throws Exception {
		if (resource.isReadable()) {
			MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
			if (metadataReader != null) {
				String className = metadataReader.getClassMetadata().getClassName();
				try {
					tryCacheMethod(className);
				} catch (ClassNotFoundException e) {
					log.error("检查" + className + "是否含有需要信息失败", e);
				}
			}
		}
	}

	/**
	 * 把action下面的所有method遍历一次，标记他们是否需要进行xxx验证 如果需要，放入cache中
	 *
	 * @param fullClassName
	 */
	private static void tryCacheMethod(String fullClassName) throws ClassNotFoundException {
		Class<?> clz = Class.forName(fullClassName);
		Service annotation = clz.getAnnotation(Service.class);
		// 获取当前类注解名称 为空使用短类名
		Method[] methods = clz.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getModifiers() != Modifier.PUBLIC && clz.isInterface()) {
				continue;
			}
			SoapParseBean soapParseBean = new SoapParseBean();
			SoapMapping sm = AnnotationUtils.findAnnotation(method, SoapMapping.class);
			if (ObjectUtil.isNotNull(sm) && StrUtil.isNotBlank(sm.funcId())) {
				soapParseBean.setFuncId(sm.funcId());
			} else {
				soapParseBean.setFuncId(method.getName());
			}
			if (ObjectUtil.isNotNull(annotation) && StrUtil.isNotBlank(annotation.value())) {
				soapParseBean.setBeanId(annotation.value());
			} else {
				soapParseBean.setBeanId(Introspector.decapitalize(ClassUtils.getShortName(fullClassName)));
			}
			soapParseBean.setBeanMethod(method.getName());
			functionMap.put(soapParseBean.getFuncId(), soapParseBean);
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		loadCheckClassMethods("com.xuzhihao.shop.webservice.service");
		AbstractBeanDefinition beanDefinition2 = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
		beanDefinition2.setBeanClass(SoapParseBeanMapping.class);// 实例化
		beanDefinition2.getConstructorArgumentValues().addGenericArgumentValue(functionMap);
		registry.registerBeanDefinition("soapParseBeanMapping", beanDefinition2);
		log.info("SoapMapping 载入完毕...");
	}
}