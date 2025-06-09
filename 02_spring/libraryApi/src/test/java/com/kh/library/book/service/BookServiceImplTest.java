package com.kh.library.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.kh.library.book.model.vo.Book;
import com.kh.library.common.model.dto.SearchParams;

class BookServiceImplTest {

	private static BookService bookService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		bookService = new BookServiceImpl();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	@DisplayName("도서 목록 조회 테스트 :: 제목 검색")
	void testGetBooks() {
		// * Given
		SearchParams params = new SearchParams();
		params.setFilter("title");
		params.setKeyword("혼자");
		
		// * When
		List<Book> list = bookService.getBooks(params);
		
		// * Then
//		assertTrue(list.size() == 2 );
		// => 전달한 조건이 true인지 확인
		assertEquals(list.size(), 2);
	}

	@Test
	@DisplayName("도서 목록 조회 테스트 :: 조건이 없을 때")
	void testGetBooksNoOption() {
		// 조건이 없을 때 --> 검색 조건이 없을 때, 전체 목록 (5개)
		
		// * Given
		SearchParams params = new SearchParams();
		// params.setFilter("");
		// --> BookServiceImpl 조건 수정
		
		// * When
		List<Book> list = bookService.getBooks(params);
		
		// * Then
		assertEquals(list.size(), 5);
	}
	
	@Test
	@DisplayName("도서 정보 조회 기능 테스트")
	void testGetBook() {
		
		// * Given
		int no = 5;
		
		// * When
		Book book = bookService.getBook(no);
		
		// * Then
		assertTrue(book != null);
		assertNotNull(book);
	}

	@Test
	@DisplayName("도서 정보 조회 실패 테스트")
	void testGetBookFailed() {
		// * Given
		int no = 100;
		
		// * When
		Book book = bookService.getBook(no);
		
		// * Then
		assertNull(book);
	}
	@Test
	void testAddBook() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateBook() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteBook() {
		fail("Not yet implemented");
	}

}
