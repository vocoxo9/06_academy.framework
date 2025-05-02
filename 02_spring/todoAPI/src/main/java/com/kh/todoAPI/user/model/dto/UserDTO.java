package com.kh.todoAPI.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

	private String id;
	private String pwd;
	private String nickname;
	private String email;
}
