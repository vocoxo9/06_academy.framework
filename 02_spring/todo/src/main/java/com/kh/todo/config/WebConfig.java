package com.kh.todo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kh.todo.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	private final LoginInterceptor loginInterceptor;
	public WebConfig(LoginInterceptor loginInterceptor) {
		this.loginInterceptor = loginInterceptor;
	}
	
	@Value("${client.origins}")
	private String origins;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				.allowedOrigins(origins)
				.allowedHeaders("*")
				.allowCredentials(true);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(new LoginInterceptor())
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/todo/**");
	}
	
	

}
