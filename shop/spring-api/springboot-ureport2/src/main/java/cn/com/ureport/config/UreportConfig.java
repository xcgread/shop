package cn.com.ureport.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.bstek.ureport.console.UReportServlet;
import com.bstek.ureport.definition.datasource.BuildinDatasource;

@ImportResource("classpath:ureport-console-context.xml") // 不加项目能够启动但是会导致加载数据源报错或加载不了
@Configuration
public class UreportConfig implements BuildinDatasource {
	@Resource
	DataSource dataSource;
	private Logger log = LoggerFactory.getLogger(getClass());

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ServletRegistrationBean ureportServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new UReportServlet());
		servletRegistrationBean.addUrlMappings("/ureport/*");
		return servletRegistrationBean;
	}

	@Override
	public String name() {
		return "myUReportDatasource";
	}

	@Override
	public Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			log.error("Ureport 数据源 获取连接失败！");
			e.printStackTrace();
		}
		return null;
	}

}