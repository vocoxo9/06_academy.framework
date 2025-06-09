package com.kh.library.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kh.library.book.model.vo.Book;
import com.kh.library.common.model.dto.SearchParams;

@Service
public class BookServiceImpl implements BookService {

	private List<Book> bookList = new ArrayList<>();

	/**
	 * 도서 목록 초기화
	 */
	public BookServiceImpl() {

		bookList.add(new Book("혼자 공부하는 컴퓨터 구조+운영체제", "강민철", "한빛미디어"));
		bookList.add(new Book("혼자 공부하는 네트워크", "강민철", "한빛미디어"));
		bookList.add(new Book("그림으로 이해하는 서버 구조와 기술 서버, 쉽고 재미있게 시작하자!", "요코타 카즈키, 엔도 유키", "길벗"));
		bookList.add(new Book("한 권으로 배우는 도커 & 쿠버네티스 컨테이너 개념부터 쿠버네티스를 활용한 배포까지", "장철원", "한빛미디어"));
		bookList.add(new Book("Clean Code 클린 코드 애자일 소프트웨어 장인 정신", "로버트 C. 마틴", "인사이트(insight)"));

	}

	@Override
	public List<Book> getBooks(SearchParams searchParams) {
	    String keyword = searchParams.getKeyword();
	    String filter = searchParams.getFilter();

	    if (keyword == null || filter == null) {
	    	return bookList;
	    }
	    
	    switch (filter) {
	        case "title":
	            return getBooksByTitle(keyword);
	        case "author":
	            return getBooksByAuthor(keyword);
	        case "publisher":
	            return getBooksByPublisher(keyword);
	        default:
	            return bookList;
	    }
	}


	/**
	 * 도서 목록에서 도서명에 전달된 값이 포함된 항목 조회
	 * 
	 * @param title 검색 도서명
	 * @return 도서 목록
	 */
	private List<Book> getBooksByTitle(String title) {
		return bookList.stream().filter(book -> book.getTitle().contains(title)).collect(Collectors.toList());
	}

	/**
	 * 도서 목록에서 저자명에 전달된 값이 포함된 항목 조회
	 * 
	 * @param author 검색 저자명
	 * @return 도서 목록
	 */
	private List<Book> getBooksByAuthor(String author) {
		return bookList.stream().filter(book -> book.getAuthor().contains(author)).collect(Collectors.toList());
	}

	/**
	 * 도서 목록에서 출판사에 전달된 값이 포함된 항목 조회
	 * 
	 * @param publisher 검색 출판사
	 * @return 도서 목록
	 */
	private List<Book> getBooksByPublisher(String publisher) {
		return bookList.stream().filter(book -> book.getTitle().contains(publisher)).collect(Collectors.toList());
	}

	@Override
	public Book getBook(int no) {
	    if (Book.getCount() < no)
	        return null;

	    for (Book book : bookList) {
	        if (book.getNo() == no) {
	            return book;
	        }
	    }

	    return null;
	}

	@Override
	public int addBook(Book book) {
		try {

			bookList.add(book);

		} catch (Exception e) {
			return 0;
		}

		return 1;
	}

	@Override
	public int updateBook(Book updateBook) {
		int index = findBookListIndexByNo(updateBook.getNo());
		if (index > -1) {
			bookList.set(index, updateBook);
			return 1;
		}

		return 0;
	}

	@Override
	public int deleteBook(int no) {
		int index = findBookListIndexByNo(no);
		if (index > -1) {
			bookList.remove(index);
			return 1;
		}
		return 0;
	}

	/**
	 * 도서 번호 기준 인덱스 조회
	 * 
	 * @param no 도서 번호
	 * @return 인덱스 번호 (없을 경우 -1)
	 */
	private int findBookListIndexByNo(int no) {
		for (int i = 0; i < bookList.size(); i++) {
			Book book = bookList.get(i);
			if (book.getNo() == no) {
				return i;
			}
		}
		return -1;
	}

}
