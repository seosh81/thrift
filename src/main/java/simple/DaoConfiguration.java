package simple;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.orm.ibatis.SqlMapClientFactoryBean;

@Configuration
public class DaoConfiguration {
	
	@Value( "${db.driver.class}" )
	String dbDriver;
	
	@Value( "${db.url}" )
	String dbUrl;
	
	@Value( "${db.user}" )
	String dbUser;
	
	@Value( "${db.password}" )
	String dbPassword;
	
	@Bean DataSource dataSource() {
		final BasicDataSource ds = new BasicDataSource();
		
		ds.setDriverClassName(dbDriver);
		ds.setUrl(dbUrl);
		ds.setUsername(dbUser);
		ds.setPassword(dbPassword);
		ds.setInitialSize(10);
		return ds;
	}
	
	@Bean SqlMapClientFactoryBean sqlMapClient() {
		SqlMapClientFactoryBean factory = new SqlMapClientFactoryBean();
		factory.setDataSource(dataSource());
		factory.setConfigLocation(new ClassPathResource("/META-INF/sqlmap-conf/sqlmap-configuration.xml"));
		try {
			factory.afterPropertiesSet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return factory;
	}
}
