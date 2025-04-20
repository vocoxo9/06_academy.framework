package com.kh.spring.board.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.dto.SearchDto;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.service.BoardServiceImpl;
import com.kh.spring.common.MyFileUtils;
import com.kh.spring.common.PageInfo;

import jakarta.servlet.http.HttpSession;
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
		PageInfo pi = new PageInfo(count, cpage, 5, 10);
		
		ArrayList<Board> list = bService.selectBoardList(pi, searchDto);
		
		// request 영역에 조회된 데이터 저장
		model.addAttribute("list", list);
		model.addAttribute("pi", pi);
		model.addAttribute("condition", searchDto.getCondition());
		model.addAttribute("keyword", searchDto.getKeyword());
		
		return "board/boardList";
	}

	
	@GetMapping("/enrollForm")
	public String boardEnrollForm() {
		return "board/enrollForm";
	}
	
	@PostMapping("/write")
	public String insertBoard(Board board, MultipartFile upfile, HttpSession session, Model model) {
		// *MultipartFile : springboot-start-web 패키지에 포함되어 있음
		// => commons-io, commons-fileupload 라이브러리
		System.out.println(board);
		/*
		System.out.println(upfile);
		System.out.println("첨부파일 :: " + upfile.isEmpty());
		*/
		// 첨부파일이 있는 경우 파일에 대한 처리
		if( !upfile.isEmpty()) {
			// 파일명을 변경 -> "spring_" + 현재날짜시간 + random값 + 확장자
			String changeName = MyFileUtils.saveFile(upfile, session, "/resources/upfile/");
			
			// * Board 객체에 파일 관련된 필드 => originName, changeName
			board.setOriginName(upfile.getOriginalFilename());	// 파일 원본명 저장
			board.setChangeName(changeName);					// 
		} else {
			
		}
		// * DB에 게시글 정보 저장 --> 첨부파일 유무 상관없이 처리해야 함
		int result = bService.insertBoard(board);
		if(result > 0) {	// 게시글 작성 성공
			session.setAttribute("alertMsg", "게시글 작성 성공했습니다.");
			return "redirect:/board/list";
		} else {			// 게시글 작성 실패
			model.addAttribute("errorMsg", "게시글 작성 실패");
			return "common/errorPage";
		}
	}
	
	@GetMapping("/detail")
	public String boardDetail(@RequestParam(defaultValue="0") int bno, Model model) {
		// @RequestParam(required=true) 필수 전달 항목에 대해 required 속성 추가 가능
		Board boardDetail = bService.selectBoardDetail(bno);
	
		if(boardDetail != null) {
			model.addAttribute("b", boardDetail);
			return "board/boardDetail";
		} else {
			model.addAttribute("errorMsg","게시글 정보를 불러올 수 없습니다.");
			return "common/errorPage";
		}
	}
}
