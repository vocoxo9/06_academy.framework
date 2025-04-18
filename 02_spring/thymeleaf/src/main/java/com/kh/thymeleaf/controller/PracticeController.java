package com.kh.thymeleaf.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.thymeleaf.model.dto.PersonDTO;

import jakarta.servlet.http.HttpSession;

@Controller
public class PracticeController {

	// [GET] "/go" 요청을 매핑
	@GetMapping("go")
	public String practicePage() {
		
		/*
		 *  * thymeleaf 사용 시 기본 설정값
		 *  => classpath : src/main/resources/
		 *  	- prefix : classpath:templates/
		 *  	- suffix : .html
		 */
		// classpath:templates/practice/page1.html
		return "practice/page1";
	} 
	
	// [POST] /add
	// { name : 이름, age : 나이, birth : 생년월일, hobby : 취미, ... }
//	@PostMapping("add")
//	public String resultPage(HttpServletRequest request) {
//		String name = request.getParameter("name");
//		String age = request.getParameter("age");
//		String birth = request.getParameter("birth");
//		String[] hobby = request.getParameterValues("hobby");
//		
//		System.out.println(name + age + birth + Arrays.toString(hobby));
//		return "redirect:go";
//	}
	
//	@PostMapping("add")
//	public String resultPage(String name, 
//								@RequestParam(defaultValue="20") int age, String birth, String[] hobby) {
//		System.out.println(name + age + birth + Arrays.toString(hobby));
//		return "redirect:go";
//	}
	
	@PostMapping("add")
	public String result2Page(PersonDTO personDTO, Model model, HttpSession session) {
		
		System.out.println(personDTO);
		// ----- service --- dao --- 비즈니스 로직 처리
		
		// Model : 스프링에서 제공해주는 객체로, request 영역에 데이터 저장
		
		// * request 영역에 전달받은 데이터를 "p" 키값으로 저장
		model.addAttribute("p", personDTO);
		
		// * session 영역에 전달받은 데이터를 "user" 키값으로 저장
		session.setAttribute("user", personDTO);
		
		// * 임의의 데이터를 리스트에 담아 request 영역에 "personList" 키값으로 저장
		ArrayList<PersonDTO> list = new ArrayList<>();
		list.add(new PersonDTO("아이유", 20, "2005-03-13"));
		list.add(new PersonDTO("마이유", 21, "2006-04-13"));
		list.add(new PersonDTO("하이유", 22, "2007-05-13"));
		
		model.addAttribute("personList", list);
		
		return "practice/page2";
	}
}
