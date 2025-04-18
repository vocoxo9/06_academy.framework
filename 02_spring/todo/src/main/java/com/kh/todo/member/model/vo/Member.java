package com.kh.todo.member.model.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Member {
	private int memberNo;
	private String id;
	private String password;
	
	// 로그인 생성자
	public Member(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	
	
}
