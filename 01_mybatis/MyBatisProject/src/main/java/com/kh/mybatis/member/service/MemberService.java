package com.kh.mybatis.member.service;

import com.kh.mybatis.member.model.vo.Member;

public interface MemberService {
	/* 회원가입 -> 데이터 추가(insert) */
	/* public abstract */int insertMember(Member m);
	
	/* 로그인 -> 데이터 조회(select) */
	Member selectMember(Member m);
	
	/* 회원 정보 수정 -> 데이터 수정(update)*/
	int updateMember(Member m);
	
	/* 회원 탈퇴 -> 데이터 삭제(delete/update) */
	int deleteMember(String userId);
	
}
