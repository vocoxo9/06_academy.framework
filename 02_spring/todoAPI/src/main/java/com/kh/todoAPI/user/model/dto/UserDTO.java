package com.kh.todoAPI.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private String id;
	private String pwd;
	private String nickname;
	private String email;
	
}
