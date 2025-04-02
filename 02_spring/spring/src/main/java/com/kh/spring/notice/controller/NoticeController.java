package com.kh.spring.notice.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.notice.model.vo.Notice;
import com.kh.spring.notice.service.NoticeService;

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
	public ModelAndView noticeList(ModelAndView mv) {
		// 응답 전 DB에서 공지사항 목록 조회
		ArrayList<Notice> list = nService.selectNoticeList();
		
		// request 영역에 조회된 목록 저장 => Model
		
		// ModelAndView : 스프링에서 제공해주는 객체
		// - Model : 데이터를 key-value 형태로 저장할 수 있는 공간 (단독 사용)
		// - View : 응답 페이지에 대한 정보를 저장할 수 있는 공간 (단독 사용 불가 => ModelAndView)
		mv.addObject("list", list);
		
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
	public String noticeDetail(@RequestParam(value="no", defaultValue="0")int no, Model model) {
		
		// 글번호에 해당하는 공지사항 정보(글번호, 제목, 작성자, 내용, 작성일)
		Notice noticeDetail = nService.selectNoticeDetail(no);
		
		if(noticeDetail != null) {
			// request 영역에 저장 => Model 객체 사용
			model.addAttribute("n",noticeDetail);	
			// 상세페이지 응답
			return "notice/noticeDetail";
		} else {
			return "common/errorPage";
		}
	}
}
