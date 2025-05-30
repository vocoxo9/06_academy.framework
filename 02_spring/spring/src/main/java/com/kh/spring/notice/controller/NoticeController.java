package com.kh.spring.notice.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.common.PageInfo;
import com.kh.spring.notice.model.vo.Notice;
import com.kh.spring.notice.service.NoticeService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	// NoticeService 객체를 생성자 주입 방식으로 추가
	private final NoticeService nService;
	
	@Autowired
	public NoticeController(NoticeService nService) {
		this.nService = nService;
	}
	
	
	/**
	 * 요청 받는 주소 : /notice/list
	 * @return
	 */
	@GetMapping("/list")
	public ModelAndView noticeList(String keyword, 
							ModelAndView mv,
							@RequestParam(value="cpage", defaultValue="1") int currPage) {
		// * ========== 페이징 처리를 위한 추가 작업 ========== *
		// [1] 전체 게시글 수 조회
		// int listCount = nService.selectNoticeCount();
		int listCount = nService.selectByNoticeTitleCount(keyword);
		// => 키워드가 null일 경우 (전달되지 않았을 경우), 전체 게시글 수를 카운트 할 것
		
		// [2] 현재 페이지 번호 --> 요청 시 전달되어야 하는 값
		
		// [3] 페이징바 개수, 한 페이지당 표시할 게시글 개수 --> 지정
		int pageLimit = 10;		// 페이징바 개수
		int boardLimit = 10;	// 한 페이지 당 표시할 게시글 수
		
		PageInfo pi = new PageInfo(listCount, currPage, pageLimit, boardLimit);
		

		// * 페이징바 정보를 request 영역에 저장 --> 페이징 바 표시할 때 사용할 것
		mv.addObject("pi", pi);
		
		// 응답 전 DB에서 공지사항 목록 조회
		// ArrayList<Notice> list = nService.selectNoticeList(pi);
		ArrayList<Notice> list = nService.searchNoticeByTitle(keyword, pi);
		
		// request 영역에 조회된 목록 저장 => Model
		
		// ModelAndView : 스프링에서 제공해주는 객체
		// - Model : 데이터를 key-value 형태로 저장할 수 있는 공간 (단독 사용)
		// - View : 응답 페이지에 대한 정보를 저장할 수 있는 공간 (단독 사용 불가 => ModelAndView)
		mv.addObject("list", list);
		
		// * 검색 키워드(keyword)를 request 영역에 저장
		mv.addObject("keyword", keyword);
		
		
		// 공지사항 목록 페이지 응답
		// return "notice/noticeList";   // "/WEB-INF/view/notice/noticeList.jsp"
		mv.setViewName("notice/noticeList");
		
		return mv;
	}
	
	/**
	 *	/notice/detail 요청을 받을 메소드
	 *	?no=게시글번호
	 * @return
	 */
	@RequestMapping("/detail")
	public String noticeDetail(@RequestParam(value="no", defaultValue="0")int no,
								Model model) {
		
		// 글번호에 해당하는 공지사항 정보(글번호, 제목, 작성자, 내용, 작성일)
		Notice noticeDetail = nService.selectNoticeDetail(no);
		
		if(noticeDetail != null) {
			// request 영역에 저장 => Model 객체 사용
			model.addAttribute("n",noticeDetail);	
			// 상세페이지 응답
			return "notice/noticeDetail";
		} else {
			model.addAttribute("errorMsg", "게시글 정보를 불러올 수 없습니다.");
			return "common/errorPage";
		}
	}
	
	/**
	 *	/notice/enrollForm 요청을 받아 공지사항 작성페이지 응답
	 *	=> /WEB-INF/views/notice/enrollForm.jsp
	 * @return
	 */
	@GetMapping("/enrollForm")
	public String noticeEnrollForm() {
		return "notice/enrollForm";
	}
	
	/**
	 * 	/notice/write 요청을 받아 전달된 공지사항 정보를 DB에 저장
	 * 	성공 시 공지사항 목록 페이지로 url 재요청
	 *  실패 시 에러페이지로 응답
	 * @return
	 */
	@PostMapping("/write")
	public String noticeWrite(Notice notice, Model model, HttpSession session) {
		int result = nService.insertNotice(notice);
		
		if(result > 0) {
			session.setAttribute("alertMsg", "공지사항을 작성했습니다");
			return "redirect:/notice/list";			
		} else {
			model.addAttribute("errorMsg", "공지사항 작성에 실패했습니다.");
			return "common/errorPage";
		}
	}
	
	/**
	 * 	/notice/updateForm 요청을 받아 공지사항 수정 페이지 응답
	 * 	?no=공지사항번호
	 * 	/WEB-INF/views/notice/updateForm.jsp
	 * @return
	 */
	@GetMapping("/updateForm")
	public String updateForm(@RequestParam(value="no", defaultValue="0") int no,
			Model model) {
		// * 공지사항 번호에 해당하는 정보를 조회
		Notice notice = nService.selectNoticeDetail(no);
		
		// * 공지사항 데이터를 저장 (request scope)
		model.addAttribute("notice", notice);
		
		// * 공지사항 수정 페이지 응답
		return "notice/updateForm";
	}
	
	
	// @RequestParam(value="no", defaultValue="0") int no,
	@PostMapping("/update")
	public String noticeUpdateRequest(Notice notice, Model model) {
		int result = nService.updateNotice(notice);
		
		if(result > 0) {
			// 수정 성공 시 공지사항 상세페이지 url 재요청
			model.addAttribute("alertMsg", "공지사항을 수정하였습니다.");
			return "redirect:/notice/detail?no=" + notice.getNoticeNo();
		}else {
			model.addAttribute("errorMsg", "공지사항 수정에 실패했습니다.");
			return "common/errorPage";
		}
	}
	
	@GetMapping("/delete")
	public String noticeDelete(@RequestParam(defaultValue="0") int no, HttpSession session, Model model) {
		int result = nService.deleteNotice(no);
		
		if (result > 0) {
			session.setAttribute("alertMsg", "공지사항을 삭제하였습니다.");
			return "redirect:/notice/list";
		}else {
			model.addAttribute("errorMsg", "공지사항 삭제에 실패했습니다.");
			return "common/errorPage";
		}
	}
	
	/**
	 * 제목으로 공지사항 검색
	 * [GET]	/notice/search
	 * 			?keyword=검색어
	 * @return
	 */
	@GetMapping("/search")
	public String searchNoticeByTitle(String keyword, 
								@RequestParam(value="cpage", defaultValue="1") int currPage, 
								Model model) {
		
		// PageInfo 객체 생성
		int listCount = nService.selectByNoticeTitleCount(keyword);
		int pageLimit = 10;
		int boardLimit = 2;
		PageInfo pi = new PageInfo(listCount, currPage, pageLimit, boardLimit);
		
		// * 조회된 목록을 request 영역에 "list" 키 값으로 저장
		ArrayList<Notice> list = nService.searchNoticeByTitle(keyword, pi);
		model.addAttribute("list", list);
		
		// * 페이징 바 표시를 위해 request 영역에 "pi" 키 값으로 PageInfo 저장
		model.addAttribute("pi", pi);
		return "notice/noticeList";
	}
}
