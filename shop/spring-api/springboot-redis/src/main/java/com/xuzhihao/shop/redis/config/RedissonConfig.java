package com.xuzhihao.shop.redis.config;

import java.io.IOException;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Redisson配置
 * @Description:
 */
@Configuration
public class RedissonConfig {

	@Bean
	public RedissonClient redisson() throws IOException {
		// 使用的是yaml格式的配置文件，读取使用Config.fromYAML，如果是Json文件，则使用Config.fromJSON
		Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson.yml"));
		return Redisson.create(config);
	}
}
