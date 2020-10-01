package com.leadsponge.IO.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.leadsponge.IO.config.property.LeadSpongeApiProperty;

@Configuration
public class DataConfiguration {

	@Autowired
	private LeadSpongeApiProperty property;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://" + property.getBancoMysql().getHost() + ":"
				+ property.getBancoMysql().getPort() + "/leadsponge?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false");
		dataSource.setUsername(property.getBancoMysql().getUsername());
		dataSource.setPassword(property.getBancoMysql().getPassword());
		return dataSource;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");
		adapter.setPrepareConnection(true);
		return adapter;
	}
}