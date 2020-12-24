package com.xuzhihao.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

import com.xuzhihao.domain.User;

/**
 * 这是一个bean定义注册器的后置处理器 1.registerBeanDefinition 2.registerSingleton
 * 
 * @author Administrator
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//		beanFactory.registerSingleton  ???
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

		GenericBeanDefinition beanDefinition = (GenericBeanDefinition) BeanDefinitionBuilder.genericBeanDefinition()
				.getBeanDefinition();
		beanDefinition.setBeanClass(User.class);// 实例化
		beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(200);// 必须有残构造 区别于按属性id赋值
		registry.registerBeanDefinition("user200", beanDefinition);
	}
}