package com.naryung.web.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:/properties/jdbc.properties")
@EnableJpaRepositories("com.naryung.web.modules.**.repository")
@EnableTransactionManagement
@EnableSpringDataWebSupport
public class JpaConfig {

	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	
	@Value("${jdbc.url}")
	private String url;
	
	@Value("${jdbc.username}")
	private String username;
	
	@Value("${jdbc.password}")
	private String password;
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
	
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean lcenf = new LocalContainerEntityManagerFactoryBean();
		lcenf.setDataSource(dataSource);
		lcenf.setPackagesToScan("com.naryung.web.modules.**.entity");
		lcenf.setJpaVendorAdapter(jpaVendorAdapters());
		lcenf.setJpaProperties(jpaProperties());
		
		return lcenf;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityFactory) {
		JpaTransactionManager jtm = new JpaTransactionManager();
		jtm.setEntityManagerFactory(entityFactory);
		
		return jtm;
	}
	
	public JpaVendorAdapter jpaVendorAdapters() {
		HibernateJpaVendorAdapter jva = new HibernateJpaVendorAdapter();
		jva.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
		
		return jva;
	}
	
	public Properties jpaProperties() {
		Properties prop = new Properties();
		prop.setProperty("hibernate.show_sql", "true");
		prop.setProperty("hibername.format_sql", "true");
		prop.setProperty("hibernate.use_sql_commemnts", "true");
		prop.setProperty("hibernate.globally_quoted_identifiers", "true");
		prop.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
		
		return prop;
	}

}
