package com.xuzhihao.shop.admin.config;

import java.util.Properties;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.xuzhihao.shop.admin.component.SqlMonitorPlugin;

/**
 * MyBatis相关配置
 */
@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
@MapperScan({ "com.xuzhihao.shop.mbg.mapper", "com.xuzhihao.shop.admin.dao" })
public class MyBatisConfig {
	// 将插件加入到mybatis插件拦截链中
	@Bean
	public ConfigurationCustomizer configurationCustomizer() {
		return new ConfigurationCustomizer() {
			@Override
			public void customize(Configuration configuration) {
				// TODO Auto-generated method stub
				// 插件拦截链采用了责任链模式，执行顺序和加入连接链的顺序有关
				SqlMonitorPlugin myPlugin = new SqlMonitorPlugin();
				// 设置参数，比如阈值等，可以在配置文件中配置，这里直接写死便于测试
				Properties properties = new Properties();
				// 这里设置慢查询阈值为1毫秒，便于测试
				properties.setProperty("time", "1");
				myPlugin.setProperties(properties);
				configuration.addInterceptor(myPlugin);
			}
		};
	}
}
