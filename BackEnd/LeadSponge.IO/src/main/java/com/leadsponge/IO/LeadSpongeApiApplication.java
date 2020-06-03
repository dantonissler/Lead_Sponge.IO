package com.leadsponge.IO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import com.leadsponge.IO.config.property.LeadSpongeApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(LeadSpongeApiProperty.class)
public class LeadSpongeApiApplication extends SpringBootServletInitializer {
	private static ApplicationContext APPLICATION_CONTEXT;

	public static void main(String[] args) throws Exception {
		APPLICATION_CONTEXT = SpringApplication.run(LeadSpongeApiApplication.class, args);
	}

	public static <T> T getBean(Class<T> type) {
		return APPLICATION_CONTEXT.getBean(type);
	}
}
