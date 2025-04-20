package com.kh.spring.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PageInfo {
	/**
	 * ===== 페이징 처리를 위한 준비작업 =====
	 * 
	 * [필요한 데이터]
	 * + 전체 게시글 개수
	 * + 현재 페이지 번호 => 사용자로부터 전달될 값
	 * + 페이징바의 최대 개수 (표시될 페이지번호의 개수)
	 * + 한 페이지 당 표시할 게시글 최대 개수
	 * 
	 * + 가장 마지막 페이지 번호 (총 페이지 수)
	 * + 표시되는 페이징 바의 시작 번호
	 * + 표시되는 페이징 바의 마지막 번호
	 */
	private int listCount;		// 전체 게시글 수
	private int currPage;		// 현재 페이지 번호
	private int pageLimit;		// 페이징바의 최대 개수
	private int boardLimit;		// 한 페이지 당 게시글 최대 개수
	// => 위의 4개 데이터를 가지고 아래 3개 데이터를 계산할 것임
	
	private int maxPage;		// 가장 마지막 페이지 번호
	private int startPage;		// 시작 번호
	private int endPage;		// 끝 번호
	
	public PageInfo(int listCount, int currPage, int pageLimit, int boardLimit) {
		this.listCount = listCount;
		this.currPage = currPage;
		this.pageLimit = pageLimit;
		this.boardLimit = boardLimit;
		
		
	/**
	 * 가장 마지막 페이지 번호(maxPage)
	 * 
	 * 총 게시글 수(listCount)		한 페이지당 게시글 최대개수(boardLimit)
	 * 		100								10						--> 10
	 * 		107								10						--> 11
	 * 		110								10						--> 11
	 * 		115								10						--> 12
	 * listCount / boardLimit --> 올림
	 */
	this.maxPage = (int)Math.ceil((double)listCount / boardLimit);
	
	/**
	 * 페이징 바 시작 번호(startPage)
	 * => 1, 11, 21, 31, ...
	 * 
	 * 현재 페이지 번호(currPage)		페이징바개수(pageLimit)
	 * 			1							10				-->		1
	 * 			5							10				-->		1
	 * 			11							10				-->		11
	 * 1 ~ 10 --> 1		n : 0
	 * 11 ~ 20 --> 11	n : 1
	 * 21 ~ 30 --> 21	n : 2
	 * 
	 * n*10 + 1
	 * 
	 * ((currPage-1) / pageLimit) * pageLimit + 1
	 */
	
	this.startPage = ((currPage - 1) / pageLimit) * pageLimit + 1 ;
	
	/**
	 * 페이징 바 마지막 번호 (endPage)
	 * if) pageLimit = 10;
	 * 
	 * startPage : 1  --> endPage : 10
	 * 			 : 11 --> endPage : 20
	 * 			 : 21 --> endPage : 30
	 * endPage = startPage + pageLimit - 1
	 */
	this.endPage = startPage + pageLimit - 1;
	
	// 시작번호가 11인 경우 끝번호는 20일 것이다.
	// 만약에, 마지막 페이지번호(maxPage)가 13이라면...? 끝번호도 13으로 저장해야 함
	this.endPage = endPage > maxPage ? maxPage : endPage;
	}

	public int getCurrPage() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getBoardLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

}
