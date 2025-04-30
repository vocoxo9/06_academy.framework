package com.kh.todoAPI.user.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.todoAPI.user.service.MailService;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins="http://localhost:5173")
@RestController		// => Controller + ResponseBody
@RequiredArgsConstructor
public class UserController {
	
	
	private final MailService mailService;
	
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
	
}
