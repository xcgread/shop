package com.xuzhihao.config;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import com.xuzhihao.domain.Stock;

/**
 * BeanDefinitionRegistry和BeanFactory都一注册bean
 * 
 * @author Administrator
 *
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Stock.class);
		registry.registerBeanDefinition("Stock2", rootBeanDefinition);
	}

}
