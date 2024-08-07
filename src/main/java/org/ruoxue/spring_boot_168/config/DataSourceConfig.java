package org.ruoxue.spring_boot_168.config;

import java.util.Map;
import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", //
		transactionManagerRef = "transactionManager", //
		basePackages = { //
				"org.ruoxue.spring_boot_168.sso.account.repository", //
				"org.ruoxue.spring_boot_168.game.ggg.repository"
		})
@Configuration
@EnableConfigurationProperties
@Slf4j
public class DataSourceConfig extends DataSourceAutoConfiguration {

	@Autowired
	private HibernateProperties hibernateProperties;

	@Autowired
	private JpaProperties jpaProperties;

	@Primary
	@Bean(name = "dataSourceProperties")
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public DataSourceProperties dataSourceProperties() {
		DataSourceProperties dataSourceProperties = new DataSourceProperties();
		return dataSourceProperties;
	}

	@Primary
	@Bean(name = "hikariConfig")
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		HikariConfig hikariConfig = new HikariConfig();
		return hikariConfig;
	}

	@Primary
	@Bean(name = "dataSource")
	public DataSource dataSource(@Qualifier("hikariConfig") HikariConfig hikariConfig) {
		log.info("poolName: " + hikariConfig.getPoolName());
		log.info("jdbcUrl: " + hikariConfig.getJdbcUrl());
		log.info("username: " + hikariConfig.getUsername());
		log.info("driverClassName: " + hikariConfig.getDriverClassName());
		log.info("maximumPoolSize: " + hikariConfig.getMaximumPoolSize());
		log.info("connectionTimeout: " + hikariConfig.getConnectionTimeout());
		log.info("maxLifetime: " + hikariConfig.getMaxLifetime());
		log.info("autoCommit: " + hikariConfig.isAutoCommit());
		log.info("jpaProperties: " + jpaProperties.getProperties());
		DataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}

	@Primary
	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("dataSource") DataSource dataSource) {
		Map<String, Object> properties = hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(),
				new HibernateSettings());
		return builder.dataSource(dataSource)//
				.packages( //
					"org.ruoxue.spring_boot_168.sso.account.model",//
					"org.ruoxue.spring_boot_168.game.ggg.model"

				).persistenceUnit("primaryDatabase")//
				.properties(properties)//
				.build();
	}

	@Primary
	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager(
			@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	@Primary
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate(@Qualifier("dataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}