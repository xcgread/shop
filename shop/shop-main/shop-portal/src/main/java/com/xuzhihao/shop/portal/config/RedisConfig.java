package com.xuzhihao.shop.portal.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import com.xuzhihao.shop.common.config.BaseRedisConfig;

/**
 * Redis相关配置
 */
@Configuration
@EnableCaching
public class RedisConfig extends BaseRedisConfig {

}
