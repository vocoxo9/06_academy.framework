package com.kh.todo.user.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor   // 기본생성자
public class UserDTO {
// { userId: 아이디, userPwd: 비밀번호, nickname: 닉네임, email: 이메일 }
	private String userId;
	private String userPwd;
	private String nickname;
	private String email;
}
