package com.xuzhihao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

/**
 * spring配置类
 * 
 * @author Administrator
 */
//扫描方式1：默认路径下的所有 
//扫描方式2：自定义扫描器(包含规则、排除规则)
@ComponentScan(value = "com.xuzhihao", excludeFilters = {
		@Filter(type = FilterType.CUSTOM, classes = MyComponentScan.class) })
public class SpringConfiguration {
}
