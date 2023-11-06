package org.jsp.busbookingapp;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.jsp.busbookingapp")
public class AdminConfig {
	@Bean
	public EntityManager getEntiManager() {
		return Persistence.createEntityManagerFactory("dev").createEntityManager();
	}
}
