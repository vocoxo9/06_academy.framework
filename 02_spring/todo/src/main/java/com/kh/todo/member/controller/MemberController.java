package com.kh.todo.member.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.todo.member.model.vo.Member;
import com.kh.todo.member.service.MemberService;
import com.kh.todo.member.service.MemberServiceImpl;
import com.kh.todo.todoList.model.vo.Todo;
import com.kh.todo.todoList.service.TodoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	private final MemberService memberService;
	private final TodoService todoService;
	private final BCryptPasswordEncoder pwdEncoder;
	
	@Autowired
	public MemberController(MemberServiceImpl memberService, BCryptPasswordEncoder pwdEncoder,TodoService todoService) {
		this.memberService = memberService;
		this.pwdEncoder = pwdEncoder;
		this.todoService = todoService;
	}
	
	@GetMapping("/signinForm")
	public String signinForm() {
		return "signin";
	}
	
	// 로그인 후 바로 todo 페이지로
	@RequestMapping("/todo")
	public String selectMember(Member member, Model model, HttpSession session) {
		// 로그인 처리
		Member loginMember = memberService.selectMember(member);
		
		// 로그인 멤버의 memberNo를 Todo에도 저장
		Todo todo = new Todo();
		todo.setMemberNo(loginMember.getMemberNo());
		
		if(loginMember == null) {
			model.addAttribute("error", "회원정보가 없습니다.");
			return "common/error";
		} else if (!pwdEncoder.matches(member.getPassword(), loginMember.getPassword())) {
			model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
			return "common/error";
		} else {
			session.setAttribute("loginMember", loginMember);
			// 로그인 동시에 멤버의 투두리스트 가져오기
			ArrayList<Todo> todoList = todoService.myTodoList(todo.getMemberNo());
			model.addAttribute("todoList", todoList);
			
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
			return "redirect:/signinForm";			
		} else {
			model.addAttribute("error", "회원가입에 실패하였습니다.");
			return "common/error";
		}
	}
}
