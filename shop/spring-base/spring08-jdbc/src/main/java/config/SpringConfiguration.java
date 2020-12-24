package config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * spring的配置类，代替applicationContext.xml
 */
@Configuration
@ComponentScan("com.xuzhihao")
@Import({ JdbcConfig.class, TransactionManagerConfig.class })
@PropertySource("classpath:jdbc.properties")
@EnableTransactionManagement // 开启spring注解事务的支持
public class SpringConfiguration {
}
