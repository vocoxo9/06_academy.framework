package com.kh.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration		// 설정 클래스 정의 시 사용되는 어노테이션
@EnableWebSecurity	// spring security 활성화 어노테이션
public class SecurityConfig {
	/*
	 * BCryptPasswordEncoder 객체의 경우 외부 라이브러리 객체이므로
	 * 직접 클래스 구현부에 @Component 등 어노테이션을 사용하여 빈으로 등록할 수 없다
	 * 
	 * 따라서, 해당 객체를 만들어서 리턴하는 메소드를 정의하여
	 * 해당 메소드를 빈에 등록 후 객체를 사용
	 */
	
	@Bean		// 메소드를 빈으로 등록하거나, 외부 라이브러리 객체를 등록하고자 할 때 사
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.formLogin(AbstractHttpConfigurer::disable)
		.csrf(AbstractHttpConfigurer::disable);
		return http.build();
	}
}
