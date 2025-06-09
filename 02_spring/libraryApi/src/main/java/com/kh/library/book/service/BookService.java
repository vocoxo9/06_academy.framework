package com.kh.library.book.service;

import java.util.List;

import com.kh.library.book.model.vo.Book;
import com.kh.library.common.model.dto.SearchParams;

public interface BookService {

	/**
	 * 도서 목록 조회 
	 * @param searchParams 검색 조건
	 * @return 도서 목록
	 */
	List<Book> getBooks(SearchParams searchParams);
	
	/**
	 * 도서 조회
	 * @param no 도서번호
	 * @return 도서 정보
	 */
	Book getBook(int no);
	
	/**
	 * 도서 추가
	 * @param book 추가할 도서 정보
	 * @return 추가 성공 시 1, 실패 시 0
	 */
	int addBook(Book book);
	
	/**
	 * 도서 수정
	 * @param book 수정할 도서 정보
	 * @return 수정 성공 시 1, 실패 시 0
	 */
	int updateBook(Book book);
	
	/**
	 * 도서 삭제
	 * @param no 삭제할 도서 번호
	 * @return 삭제 성공 시 1, 실패 시 0
	 */
	int deleteBook(int no);
	
}
