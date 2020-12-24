package com.xuzhihao.config;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.xuzhihao.domain.Stock;

/**
 * 自定义返回导入的组件逻辑
 * 返回值注册
 * 或者通过实现Aware拿到beanFactory进行注册
 * 
 * @author Administrator
 *
 */
public class MyImportSelector implements ImportSelector, BeanFactoryAware {

	private BeanFactory beanFactory;

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
		beanDefinition.setBeanClass(Stock.class);
		beanDefinition.getPropertyValues().addPropertyValue("id", 123456);
		((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("Stock", beanDefinition);
		return new String[] { "com.xuzhihao.domain.Order" };
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
}
