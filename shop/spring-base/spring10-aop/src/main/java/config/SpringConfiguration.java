package config;

import support.factory.YmlPropertySourceFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * spring的配置类
 */
@Configuration
@ComponentScan("com.xuzhihao")
@Import(JdbcConfig.class)
@PropertySource(value = "classpath:jdbc.yml", factory = YmlPropertySourceFactory.class)
@EnableAspectJAutoProxy//开启spring注解aop配置的支持
public class SpringConfiguration {
}
