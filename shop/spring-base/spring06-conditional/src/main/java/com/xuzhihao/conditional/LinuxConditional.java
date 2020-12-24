package com.xuzhihao.conditional;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
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
public class LinuxConditional implements Condition {
	@Override
	@SuppressWarnings("unused")
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();// 获取bean工厂
		
		ClassLoader classLoader = context.getClassLoader();// 获取类加载器
		Environment environment = context.getEnvironment();// 获取环境变量
		BeanDefinitionRegistry registry = context.getRegistry();// 获取bean自定义注册器

		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(Unix.class);
		beanDefinition.getPropertyValues().addPropertyValue("name", "Unix3");
		((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("Unix3", beanDefinition);

		String property = environment.getProperty("os.name");
		if (property.contains("linux")) {
			return true;
		}
		return false;
	}
}
