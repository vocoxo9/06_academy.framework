package com.kh.todo.member.service;


import com.kh.todo.member.model.vo.Member;

public interface MemberService {
	
	public int insertMember(Member member);
	
	public Member selectMember(Member member);
}
