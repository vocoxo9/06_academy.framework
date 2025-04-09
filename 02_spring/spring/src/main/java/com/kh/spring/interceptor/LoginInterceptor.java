package com.kh.spring.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/*
 *  * Interceptor
 *  	- Spring MVC 컨트롤러 실행 전후로 동작
 *  	- DispatcherServlet 이후 단계에서 실행됨
 *  
 *  장점
 *   - Spring MVC 컨트롤러 실행 전후로 동작하기 때문에 특정
 *     url 단위로 비즈니스 로직을 분석할 수 있음
 *   
 *  단점
 *   - 요청 본문(body)를 직접 읽고 조작하기 어려움
 *   
 *   보통 API(url) 요청 별로 어떤 작업을 수행하기 위해 사용
 *   (인증/권한 체크)
 *   
 */

// [1] HandlerInterceptor 구현 처리
public class LoginInterceptor implements HandlerInterceptor{

	/**
	 * 요청 전 저리 : preHandle
	 * 응답 전 처리 : postHandle
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// * true 리턴 : 정상적으로 동작시키겠다
		// * false 리턴 : 해당 요청을 처리하지 않겠다
		// 로그인 하지 않았을 경우 해당 요청을 처리하지 않도록
		
		// * 로그인 정보 = 세션 영역에 있음
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") != null ) {
			// 로그인이 되어있을 경우 정상 동작 처리
			return true;
		} else {
			// 로그인 되어 있지 않은 경우 해당 요청을 처리하지 않음
			// => 대신 동작될 내용을 작성
			session.setAttribute("alertMsg", "로그인 후 이용할 수 있습니다.");
			// => 메시지 저장
			response.sendRedirect("/");
			// => 메인페이지로 리다이렉트
			return false;
		}
	}
	
}
