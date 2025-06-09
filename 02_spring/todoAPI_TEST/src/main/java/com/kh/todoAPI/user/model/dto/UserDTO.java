package com.kh.todoAPI.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private String userId;
	private String userPwd;
	private String nickname;
	private String email;
}
