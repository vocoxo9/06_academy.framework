package com.kh.todoAPI.user.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.todoAPI.user.model.dto.UserDTO;
import com.kh.todoAPI.user.service.MailService;
import com.kh.todoAPI.user.service.UserService;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins="http://localhost:5173")
@RestController		// => Controller + ResponseBody
@RequiredArgsConstructor
public class UserController {
	
	
	private final MailService mailService;
	private final UserService userService;
	
	/**
	 * 이메일을 전달받아 인증코드를 메일로 전송
	 * @param email
	 * @return "ok" : 인증코드 발송, "nok" : 인증코드 발송 실패
	 */
	@PostMapping("/email/send")
	public String sendEmailCode(@RequestBody Map<String, Object> requestBody) {
		/*
		 	폼 요청 시 전송 형식과 axios(fetch) 요청 시 전송 형식이 달라 
		 	요청 본문에서 전달데이터를 추출해야 한다
		 	@RequestBody : 요청 본문. 전송 형식이 application/json
		 	@RequestParam : 쿼리 파라미터 또는 폼 데이터. 단일 데이터 처리
		 	@ModelAttribute : 쿼리 파라미터 또는 폼 데이터. 객체 바인딩 처리
		 */
		String email = (String)requestBody.get("email");
		
		System.out.println("email :: " + email);
		
		try {
			mailService.sendMail(email);
		} catch (MessagingException e) {
			return "nok";
		}
		
		return "ok";
	}
	
	
	/**
	 * 이메일에 대한 인증코드 검증
	 * @param requestBody { email : "이메일정보", code : "인증코드" }
	 * @return "success" : 검증성공, "failed" : 검증 실패
	 */
	@PostMapping("/email/verify")
	public String verifyEmailCode(@RequestBody Map<String, Object> requestBody) {
		String email = (String)requestBody.get("email"); 
		String code = (String)requestBody.get("code");
		
		System.out.println("email :: " + email);
		System.out.println("code :: " + code);
		
		boolean result = mailService.verifyCode(email, code);
		
		return result ? "success" : "failed";
	}
	
	/**
	 * 아이디 중복체크
	 * [POST] /checkId
	 * @param id 아이디
	 * @return "NNNNN" : 중복된 아이디, "NNNNY" : 사용 가능한 아이디
	 */
	@PostMapping("/checkId")
	public String checkId(@RequestBody Map<String, Object> requestBody) {
		String id = (String)requestBody.get("id");
		
		// 서비스로부터 중복체크 -> 사용자 테이블에서 id에 해당하는 개수를 조회할 것임!
		boolean result = userService.checkId(id);
		
		return result ? "NNNNY" : "NNNNN";
	}
	
	/**
	 * 회원가입 (회원 정보 등록)
	 * [POST] /user
	 * @param UserDTO 회원 정보 { userId: 아이디, userPwd: 비밀번호, nickname: 닉네임, email: 이메일 }
	 * @return "success" : 가입 성공, "failed" : 가입 실패
	 */
	@PostMapping("user")
	public String registUser(@RequestBody UserDTO userDto) {
		int result = userService.signupUser(userDto);		
		return result > 0 ? "success" : "failed";		
	}
	
	/**
	 * 로그인 (회원 정보 조회)
	 * [POST] /login
	 * @param UserDTO 로그인 정보 { id, pwd }
	 * @return "success" : 로그인 성공, "failed" : 로그인 실패
	 *  * 로그인 성공 시, 세션에 사용자 정보를 저장
	 */
	@PostMapping("/login")
	public String loginUser(@RequestBody UserDTO userDTO, HttpSession session) {
		UserDTO loginUser = userService.loginUser(userDTO);
		System.out.println(userDTO);
		System.out.println(loginUser);
		
		if (loginUser == null) {
			return "failed";
		} else {
			session.setAttribute("loginUser", loginUser);
			return "success";
		}
	}
	
}
