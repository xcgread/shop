package com.xuzhihao.shop.search.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan({ "com.xuzhihao.shop.mbg.mapper", "com.xuzhihao.shop.search.dao" })
public class MyBatisConfig {
}
