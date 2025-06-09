package com.kh.library.book.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.kh.library.book.model.vo.Book;
import com.kh.library.book.service.BookService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(BookController.class)
class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;
	// -> MockMvc를 통해서 HTTP 요청에 대한 시물레이션을 할 수 있음
	// -> HTTP 시뮬레이션 용도의 객체
	
	@MockitoBean					// @MockBean -> 모의(가짜)로 해당 객체를 주입함
	private BookService bookService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		log.info(" ===== BookController Test Start ===== ");
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		log.info(" ===== BookController Test End ===== ");
	}

	@Test
	@DisplayName("도서 추가 요청 테스트")
	void testAddBook() throws Exception {
		// Given-When-Then 테스트 패턴
		
		// * 입력값 (준비) ---> Given
		Book book = new Book("어린왕자", "생텍쥐페리", "민음사");
		
		// * 테스트 수행 ---> When
		// 어떤 Method로, 어떤 주소로 요쳥해야하는지?
		mockMvc.perform(
				post("/book")
				.content(new Gson().toJson(book))
				.contentType(MediaType.APPLICATION_JSON)
				)
		.andExpect(status().isOk());		// * 테스트 수행 결과 ---> Then
		
	}

	@Test
	void testGetBooks() {
		fail("Not yet implemented");
	}
	
	@Test
	void testGetBook() {
		fail("Not yet implemented");
	}
	
	@Test
	@DisplayName("도서 수정 요청 테스트")
	void testUpdateBook() throws Exception {
		// * Given
		Book book = new Book("제목", "저자", "출판사");
		
		// * When / Then
		mockMvc.perform(
				put("/book")
				.content(new Gson().toJson(book))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("도서 삭제 요청 테스트")
	void testDeleteBook() throws Exception{
		// * Given
		String no = "1";
		
		// * When/Then
		mockMvc.perform(
				delete("/book")
				.param("no", no)
				).andExpect(status().isOk());
	
	}
	
	
}
