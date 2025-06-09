package com.kh.library.book.model.vo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class Book {
	
	private static int count = 1;				
	
	@Setter(AccessLevel.PRIVATE) private int no; // 도서 번호
	private String title;						 // 도서명
	private String author;						 // 저자명
	private String publisher;					 // 출판사

	/**
	 * 도서 정보 초기화
	 * @param title     도서명
	 * @param author    저자명
	 * @param publisher 출판사
	 */
	public Book(String title, String author, String publisher) {
		no = count++;
		this.title = title;
		this.author = author;
		this.publisher = publisher;			
	}
	
	public static int getCount() { return count; }
}
