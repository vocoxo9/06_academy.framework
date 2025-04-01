package com.kh.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/*
	 * spring security 에서 기본적으로 제공하는 기능 비활성화
	 */
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.formLogin(AbstractHttpConfigurer::disable)		// 로그인폼 비활성화
		.csrf(AbstractHttpConfigurer::disable);		// Cross-Site Request Forgery 비활성화
		return http.build();
	}
}
