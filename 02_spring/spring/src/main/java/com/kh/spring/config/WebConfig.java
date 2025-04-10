package com.kh.spring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kh.spring.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 인터셉터 등록
		// * LoginInterceptor
		
		registry.
			addInterceptor(new LoginInterceptor())
			// addPathPatterns("/member/myPage", "/board/enrollForm");
			// 회원서비스(마이페이지), 공지사항(목록 제외하고 접근 제어), 자유게시판(목록 제외하고 접근 제어)
			.addPathPatterns("/member/mypage","/notice/**", "/board/**")
			.excludePathPatterns("/notice/list", "/board/list");
			// notice/** => notice로 시작되는 모든 주소를 막겠다는 뜻, excludePathPatterns로 제외할 주소만 설정
	}
	
}
