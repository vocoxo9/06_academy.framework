package com.kh.todo.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.todo.member.model.vo.Member;
import com.kh.todo.member.service.MemberService;
import com.kh.todo.member.service.MemberServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	private final MemberService memberService;
	private final BCryptPasswordEncoder pwdEncoder;
	
	@Autowired
	public MemberController(MemberServiceImpl memberService, BCryptPasswordEncoder pwdEncoder) {
		this.memberService = memberService;
		this.pwdEncoder = pwdEncoder;
	}
	
	
	// 로그인 후 바로 todo 페이지로
	@PostMapping("/signin")
	public String selectMember(Member member, Model model, HttpSession session) {
		Member loginMember = memberService.selectMember(member);
		
//		if(loginMember == null) {
//			session.setAttribute("loginMember", loginMember);
//			//model.addAttribute("alert", "정상적으로 로그인 되었습니다.");
//			return "todo";
//		} else {
//			//model.addAttribute("alert", "로그인 중 오류가 발생하였습니다.");
//			return "redirect:/";
//		}
		
		if(loginMember == null) {
			return "";
		} else if (!pwdEncoder.matches(member.getPassword(), loginMember.getPassword())) {
			return "";
		} else {
			return "todo";
		}
	}
	
	@GetMapping("/signupForm")
	public String signupForm() {
		return "signup";

	}
	
	@PostMapping("/signup")
	public String insertMember(Member member, Model model) {
		// 암호화된 비밀번호로 DB에 저장
		String encodingPassword = pwdEncoder.encode(member.getPassword());
		member.setPassword(encodingPassword);
		
		int result = memberService.insertMember(member);
		
		if(result > 0) {
			model.addAttribute("alert", "회원가입이 완료되었습니다.");
			return "redirect:/";			
		} else {
			return "...?"; 	// TODO 오류페이지 만들기
		}
	}
}
