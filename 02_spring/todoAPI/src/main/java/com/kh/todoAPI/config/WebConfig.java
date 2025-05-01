package com.kh.todoAPI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer{

	@Value("${client.origins}")
	private String origins;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**")
		.allowedOrigins(origins)
		.allowedMethods("GET", "POST", "PUT", "DELETE")
		.allowedHeaders("*")
		.allowCredentials(true);
	}

	
}
