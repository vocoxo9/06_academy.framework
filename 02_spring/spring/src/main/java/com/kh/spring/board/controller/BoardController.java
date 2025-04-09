package com.kh.spring.board.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.board.model.dto.SearchDto;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.service.BoardServiceImpl;
import com.kh.spring.common.PageInfo;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardServiceImpl bService;
	
//	@Autowired
//	public BoardController(BoardServiceImpl bService) {
//		this.bService = bService;
//	}
	
	
	@GetMapping("/list")
	public String boardList(@RequestParam(defaultValue="1") int cpage
						   // , String condition, String keyword
						   , SearchDto searchDto
						   , Model model) {
		// 페이징 처리
		//  한 페이지당 게시글 개수: 10, 페이징바 표시 개수: 5
		//  현재 페이지 번호 --> 요청 시 전달! (cpage)
		//  전체 게시글 개수 --> 조회
		int count = bService.selectBoardCount(searchDto);
		
		// 페이징 정보를 담은 PageInfo 객체 생성
		PageInfo pi = new PageInfo(count, cpage, 5, 2);
		
		ArrayList<Board> list = bService.selectBoardList(pi, searchDto);
		
		// request 영역에 조회된 데이터 저장
		model.addAttribute("list", list);
		model.addAttribute("pi", pi);
		model.addAttribute("condition", searchDto.getCondition());
		model.addAttribute("keyword", searchDto.getKeyword());
		
		return "board/boardList";
	}

}
