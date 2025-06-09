package com.kh.todoAPI.post.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.kh.todoAPI.post.model.dto.TodoDTO;
import com.kh.todoAPI.post.service.TodoService;
import com.kh.todoAPI.user.model.dto.UserDTO;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

	// 모의 객체 주입 ( 테스트용으로만 사용될 객체, 실제로 동작되지 않음 )
	@MockitoBean
	private TodoService todoService;
	
	// MockMvc 객체 : HTTP 요청 시물레이션
	@Autowired
	private MockMvc mockMvc;
	/*
	 *  * MockMvc : 스프링에서 제공하는 웹 어플리케이션 테스트용 라이브러리
	 *  			실행 중인 서버 대신 모의 요청과 응답 객체를 통해 테스트 수행
	 *  	=> @WebMvcTest 가 적용된 테스트 클래스에서 사용 가능
	 *  
	 */
	
	@Test
	@DisplayName("Todo 목록 조회 테스트")	// 테스트에 이름 부여
	void testGetTodoList() throws Exception {
		// * GWT 테스트 패턴 (Given-When-Then)
		// 		- Given : 테스트 시작 전 전제 조건, 초기 상태 설정 (테스트 준비 단계)
		//		- When : 테스트 중에 사용자(프로그램)가 수행한 작업 (테스트 진행 단계)
		//		- Then : When 단계에서 수행한 작업의 결과 (테스트 완료 단계)
		
		// * Given
		MockHttpSession mockSession = new MockHttpSession();	// 모의 세션 객체
		
		// 사용자 정보
		UserDTO user = new UserDTO();
		user.setUserId("admin");
		
		// 세션에 사용자 정보 추가
		mockSession.setAttribute("loginUser", user);
		
		// * When
		mockMvc.perform(
					get("/todo").session(mockSession)
				)
		.andExpect(status().isOk());	// * Then
	}
	
	
	
	@Test
	@DisplayName("Todo 목록 추가 테스트")
	void testAddTodo() throws Exception{
		MockHttpSession mockSession = new MockHttpSession();
		
		UserDTO user = new UserDTO();
		user.setUserId("admin");
		mockSession.setAttribute("loginUser", user);
		
		UserDTO loginUser = (UserDTO)mockSession.getAttribute("loginUser");
		
		TodoDTO todo = new TodoDTO();
		todo.setContent("testttt");
		todo.setUserId(loginUser.getUserId());
		
		mockMvc.perform(
					post("/todo").session(mockSession)
					.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isOk());
	}
	

}
