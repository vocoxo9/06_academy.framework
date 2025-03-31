package com.kh.spring.member.service;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {
	/* 회원 가입 서비스 (C) */
	int insertMember(Member m);
	
	/* 로그인 서비스 (R) */
	Member loginMember(Member m);
	
	/* 회원 정보 수정 서비스(U) */
	Member updateMember(Member m);
	
	/* 회원 탈퇴 서비스 (D) */
	int deleteMember(String id);
}
