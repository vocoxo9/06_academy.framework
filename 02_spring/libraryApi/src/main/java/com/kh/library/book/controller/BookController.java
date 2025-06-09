package com.kh.library.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.library.book.model.vo.Book;
import com.kh.library.book.service.BookService;
import com.kh.library.common.model.dto.SearchParams;

@RestController
public class BookController {
	
	private final BookService bookService;
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@PostMapping("book")
	public String addBook(@RequestBody Book book) {
		int result = bookService.addBook(book);
		
		return result > 0 ? "success" : "failed";
	}

	@GetMapping("books")
	public List<Book> getBooks(@RequestParam(name="keyword",defaultValue="") String keyword
							 , @RequestParam(name="filter",defaultValue="") String filter) {
		SearchParams searchParams = new SearchParams();
		searchParams.setKeyword(keyword);
		searchParams.setFilter(filter);
		
		return bookService.getBooks(searchParams);
	}
	
	@GetMapping("book/{no}")
	public Book getBook(@PathVariable(name="no") int no) {
		return bookService.getBook(no);
	}
	
	@PutMapping("book")
	public Book updateBook(@RequestBody Book book) {
		int result = bookService.updateBook(book);
		
		if (result > 0 ) {
			return bookService.getBook(book.getNo());
		} else {
			return null;
		}
	}
	
	@DeleteMapping("book")
	public String deleteBook(@RequestParam(name="no") int no) {
		int result = bookService.deleteBook(no);
		return result > 0 ? "success" : "failed";
	}
}
