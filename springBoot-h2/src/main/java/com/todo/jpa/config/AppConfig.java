package com.todo.jpa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("com.todo.jpa")
public class AppConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/api/todo/**")
				.allowedOrigins("*")
				.allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS", "TRACE", "HEAD")
				.allowedHeaders("Content-Type","accept")
				.allowCredentials(true)
				.maxAge(6000);

	}
}
