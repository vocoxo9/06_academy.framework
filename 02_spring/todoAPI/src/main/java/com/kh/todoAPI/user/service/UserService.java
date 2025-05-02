package com.kh.todoAPI.user.service;

import org.springframework.stereotype.Service;

import com.kh.todoAPI.user.model.dao.UserMapper;
import com.kh.todoAPI.user.model.dto.UserDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserMapper userMapper;
	
	/**
	 * 아이디 중복 체크
	 * @param id 	사용자 아이디
	 * @return		true : 사용 가능, false : 중복(사용 불가)
	 */
	public boolean checkId(String id) {
		boolean result = true;
		// DB에서 아이디와 일치하는 개수를 조회하여 0인 경우 true, 0보다 큰 경우 false 리턴
		int count = userMapper.countById(id);
		
		return count == 0 ;
	}
	
	/** 회원가입
	 *  @param UserDTO 회원 정보 (아이디, 비밀번호, 닉네임, 이메일)
	 *  @return 처리결과(행수)
	 */
	public int signupUser(UserDTO userDTO) {
		return userMapper.signupUser(userDTO);
	}
}
