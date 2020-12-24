package com.xuzhihao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.xuzhihao.domain.User;

/**
 * spring配置文件
 * 
 * @author Administrator
 *
 */
//@import 
//1.直接注入@Import(Rainbow.class)
//2.实现导入选择器@Import(MyImportSelector.class)
//3.实现bean信息注册器//@Import({ MyImportBeanDefinitionRegistrar.class })
//前两种注入名称为权限定类名
@ComponentScan(value = "com.xuzhihao")
@Import({ User.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class })
public class SpringConfiguration {

}
