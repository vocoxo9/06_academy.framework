package com.kh.spring.board.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring.board.model.dto.SearchDto;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
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
		
		// * 상세 페이지 요청 시 해야할 작업
		
		// [1] 해당 게시글의 조회 수 증가 (=> DB에서 업데이트)
		int result = bService.increaseCount(bno);
		
		if (result > 0) {
			// [2] 해당 게시글 정보 (=> DB에서 조회)
			Board boardDetail = bService.selectBoardDetail(bno);
			
			// [3] 해당 게시글에 달린 댓글 정보 (=> DB에서 조회)
			ArrayList<Reply> rList = bService.selectReplyList(bno);
			
			model.addAttribute("board", boardDetail);
			model.addAttribute("rList", rList);
			return "board/boardDetail";
		} else {
			model.addAttribute("errorMsg","게시글 정보를 불러올 수 없습니다.");
			return "common/errorPage";
		}	
	}
	
	@GetMapping("/delete")
	public String boardDelete(@RequestParam(defaultValue="0") int bno, Model model, HttpSession session) {
		int result = bService.deleteBoard(bno);
		
		if(result > 0) {
			session.setAttribute("alertMsg", "게시글 삭제에 성공하였습니다.");
			return "redirect:/board/list";
		} else {
			model.addAttribute("errorMsg", "게시글 삭제에 실패하였습니다.");
			return "common/errorPage";
		}
	}
	
	@GetMapping("/updateForm")
	public String boardUpdateForm(@RequestParam(defaultValue="0", required=true) int bno, Model model) {
		Board board = bService.selectBoardDetail(bno);
		if(board != null) {
			model.addAttribute("board", board);
			return "board/updateForm";
		} else {
			model.addAttribute("errorMsg", "접근할 수 없습니다.");
			return "redirect:/";
		}
	}
	
/*	ModelAndView 객체 사용
 	@GetMapping("/updateForm")
	public ModelAndView updatePage(@RequestParam(required=true) int bno, ModelAndView mv) {
		
		// 게시글 수정 페이지로 응답
		// --> 필요한 데이터? 전달된 게시글 번호에 해당하는 게시글 정보 조회
		Board board = bService.selectBoardDetail(bno);
		mv.addObject("board", board);
		mv.setViewName("board/updateForm");
		// => 응답 페이지 경로 : /WEB-INF/views/board/updateForm
		
		return mv;
	}
*/
	
	// 요청 주소 : /board/update
	// 요청 방식 : POST
	// 전달 데이터 형식 : Board, 파일 upfile -> MultipartFile
	@PostMapping("/update")
	public String boardUpdate(Board board, MultipartFile upfile, HttpSession session, Model model) {
		System.out.println(board);
		System.out.println(upfile);
		
		if(!upfile.isEmpty()) {
			String changeName = MyFileUtils.saveFile(upfile, session, "/resources/upfile/");
			board.setOriginName(upfile.getOriginalFilename());
			board.setChangeName(changeName);
		} 

		int result = bService.updateBoard(board);
		
		if (result > 0) {
			session.setAttribute("alertMsg", "글 수정에 성공했습니다.");
			Board updateBoard = bService.selectBoardDetail(board.getBoardNo());
			model.addAttribute("board", updateBoard);
			return "redirect:/board/list";
		} else {
			model.addAttribute("errorMsg", "글 수정에 실패하였습니다.");
			return "common/errorPage";
		}
	}
	
	// * 요청 주소 : /board/update
	// * 요청 방식 : POST
	// * 요청 시 전달 데이터 형식 : boardNo, boardTitle, ... -> Board 
	//						   파일 upfile -> MultipartFile
//	@PostMapping("update")
//	public ModelAndView updateBoard(Board board, MultipartFile upfile,
//									ModelAndView mv, HttpSession session) {
//		// * 새로 추가된 첨부파일이 있는 경우
//		//   Board 객체에 첨부파일 정보를 저장. 서버에 해당 파일 저장.
//		
//		// * 새로운 첨부파일이 있는 지 체크
//		if (!upfile.isEmpty()) {	// 첨부파일 객체가 비어있지 않은 경우
//			// TODO: 기존 첨부파일이 있을 경우, 해당 파일 삭제
//			String beforeChangeName = board.getChangeName();
//			if (beforeChangeName != null 
//					&& !beforeChangeName.isEmpty()) {
//				File orgFile = new File(session.getServletContext().getRealPath(beforeChangeName));
//				orgFile.delete();	
//				System.out.println("[INFO] file delete..." + beforeChangeName);
//			}
//			String changeName = MyFileUtils.saveFile(upfile, session, "/resources/upfile/");
//			// => 서버에 파일명을 변경하여 저장.
//			
//			// * Board 객체에 첨부파일 정보 저장 (변경된 파일명, 원본 파일명)
//			board.setChangeName(changeName);
//			board.setOriginName( upfile.getOriginalFilename() );
//		}
//		/*
//			* 첨부파일이 새로 추가된 경우 *
//			  - originName : 추가된 첨부파일 원본명
//			  - changeName : 추가된 첨부파일의 변경된 이름
//			  
//			* 첨부파일이 새로 추가되지 않은 경우 *
//			  - originName : 게시글 추가 시 원본 정보
//			  - changeName : 게시글 추가 시 파일명 변경된 정보
//		 */
//		// * 게시글 정보를 DB에 업데이트
//		int result = bService.updateBoard(board);
//		
//		// * 게시글 변경 성공 시 --> "게시글 수정 성공했습니다" 메시지 저장
//		//						 해당 게시글의 상세페이지로 이동 (URL 재요청)
//		if (result > 0) {
//			session.setAttribute("alertMsg", "게시글 수정 성공했습니다.");
//			mv.setViewName("redirect:/board/detail?bno=" + board.getBoardNo());
//		} else {
//			// * 게시글 변경 실패 시 --> "게시글 수정 실패했습니다" 메시지 저장
//			//						 에러 페이지로 응답
//			mv.addObject("errorMsg", "게시글 수정 실패했습니다.");
//			mv.setViewName("common/errorPage");
//		}
//		return mv;
//	}
	
	/** ----------------------------------------------------------------- **/
	
	@PostMapping("/rinsert")
	@ResponseBody			// 페이지가 아닌 데이터 형식으로 응답하고자 할 때 사용하는 어노테이션
	public String replyInsert(Reply reply, HttpSession session) {
		// * 댓글 정보를 DB에 추가
		int result = bService.insertReply(reply);
		
		if (result > 0) {
			// * 댓글 추가 성공 시 "success"로 응답
			return "success";
		} else {
			// * 댓글 추가 실패 시 "failed"로 응답
			return "failed";
		}
	}
	
	@GetMapping("/rlist")
	@ResponseBody
	public ArrayList<Reply> selectReplyList(@RequestParam(required=true) int boardNo) {
		// 해당 게시글의 댓글 목록 조회
		ArrayList<Reply> list = bService.selectReplyList(boardNo);
		
		// 조회된 결과 응답
			return list;
	}
}
