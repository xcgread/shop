package com.xuzhihao.shop.portal.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis配置类
 */
@Configuration
@EnableTransactionManagement
@MapperScan({ "com.xuzhihao.shop.mbg.mapper" })
public class MyBatisConfig {
}
