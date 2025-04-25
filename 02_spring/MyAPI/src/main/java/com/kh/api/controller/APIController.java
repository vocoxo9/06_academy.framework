package com.kh.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.api.model.dto.UserDTO;

/*
	* @CrossOrigin : 스프링에서 제공하는 어노테이션으로 CORS 설정을 위해 사용
					=> 클래스, 메소드 단위에 설정
					
	- CORS (Cross Origin Resource Sharing)
		: 클라이언트와 서버가 서로 다른 출처(origin)에서 요청을 주고받을 때 발생하는 보안 정책
		  브라우저에서는 보안 상의 이유로 기본적으로 요청을 차단함
		  
		  리액트 앱(http://localhost:3000)에서 스프링 서버(http://localhost:8080)로 요청을 하면
		  CORS에 의해 차단되었음
		  -> 이 문제를 해결하기 위해 서버 쪽에 @CrossOrigin을 사용하여 허용할 출처(origin)를 명시함
		  
		  * origin : 프로토콜 + 도메인 + 포트
 */
//@CrossOrigin(origins="http://localhost:3000")
@RestController		// @Controller + @ResponseBody
public class APIController {
	
	/**
	 *	/server --> Server에 대한 데이터 조회
	 * @return
	 */
	@GetMapping("/server")
	public String getServer() {
		return "My API";
	}
	
	@GetMapping("/users")
	public List<UserDTO> getUserList() {
		// ----- 비즈니스 로직을 다녀왔다고 가정... -----
		
		List<UserDTO> list = new ArrayList<>();
		
		list.add(new UserDTO("홍길동", 20, "010-1234-5678"));
		list.add(new UserDTO("공길동", 20, "010-0239-9458"));
		list.add(new UserDTO("양길동", 20, "010-2545-1568"));
		
		return list;
	}
}
