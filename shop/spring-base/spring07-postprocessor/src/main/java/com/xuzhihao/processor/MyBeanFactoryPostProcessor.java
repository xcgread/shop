package com.xuzhihao.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

import com.xuzhihao.domain.User;

/**
 * 这是一个bean工厂的后置处理器 干预bean的属性
 * 1.registerBeanDefinition 2.registerSingleton
 * 
 * @author Administrator
 *
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// 注入方式1
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(User.class);
		beanDefinition.getPropertyValues().addPropertyValue("id", 100);
		((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("user100", beanDefinition);

//		beanFactory.registerSingleton  ???

		// 修改之前bean中的属性
		String[] beanStr = beanFactory.getBeanDefinitionNames();
		for (String beanName : beanStr) {
			if ("user100".equals(beanName)) {
				BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
				MutablePropertyValues m = bd.getPropertyValues();
				if (m.contains("id")) {
					m.addPropertyValue("id", "111111111");
				}
			}
		}

	}

}