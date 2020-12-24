package com.xuzhihao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * spring配置文件
 * 
 * @author Administrator
 *
 */
@ComponentScan(value = "com.xuzhihao")
@PropertySource(value = "classpath:application.properties")
@PropertySource(factory = YamlPropertySourceFactory.class, value = "classpath:application.yml")
public class SpringConfiguration {

}
