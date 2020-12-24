package com.xuzhihao.conditional;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.xuzhihao.service.Unix;

/**
 * 条件判断 满足条件的时候注册
 * 
 * @author Administrator
 *
 */
public class WindowsConditional implements Condition {

	@Override
	@SuppressWarnings("unused")
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();// 获取bean工厂
		ClassLoader classLoader = context.getClassLoader();// 获取类加载器
		Environment environment = context.getEnvironment();// 获取环境变量
		BeanDefinitionRegistry registry = context.getRegistry();// 获取bean自定义注册器

		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Unix.class);
		rootBeanDefinition.getPropertyValues().addPropertyValue("name", "Unix");
		registry.registerBeanDefinition("Unix2", rootBeanDefinition);
		
		String property = environment.getProperty("os.name");
		if (property.contains("Windows")) {
			return true;
		}
		return false;
	}

}
