package com.kh.todoAPI.model.vo;

import lombok.Data;

@Data
public class AuthData {
	private String code;	// 인증 코드
	private Long time;		// 인증 코드 유효시간
	
	private final long LIMIT_TIME = 3 * 60 * 1000;		// 제한 시간 (3분)
	
	// 기본 생성자 => 인증 코드 생성, 유효시간 저장
	public AuthData() {
		this.code = makeRandomCode("");		// 랜덤값 6자리
		this.time = System.currentTimeMillis() + LIMIT_TIME;
		// => 현재시간 + 제한시간
	}
	
	private String makeRandomCode(String code) {
		// 랜던값 6자리 생성
		// => 재귀 함수로서 사용하여 생성
		
		if (code.length() == 6) return code;
		else {
			// 0 ~ 9 랜덤값 추출
			// ( 끝값 - 시작값 + 1 ) + 시작값
			int random = (int)Math.random() * 10;
			
			if ( random % 2 == 0) {
				// 짝수인 경우, 소문자로 변경
				int chRandom = (int) (Math.random() * ('z' - 'a' + 1 ) + 'a');
				code += (char)chRandom;
			} else {
				// 홀수인 경우, 숫자 그대로 사용				
				code += random;
			}
			
			return makeRandomCode(code);
		}
	}
}
