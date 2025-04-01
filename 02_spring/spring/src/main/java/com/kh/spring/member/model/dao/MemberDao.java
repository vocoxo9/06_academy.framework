package com.kh.spring.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.spring.member.model.vo.Member;

@Mapper			// MyBatis를 사용해서 빈 등록
public interface MemberDao {

	/* 회원 가입 - 데이터 추가 */
	int insertMember(Member m);
	
	/* 로그인 - 데이터 조회 */
	Member loginMember(Member m);
	Member selectMember(String userId);
	
	/* 회원정보 수정 - 데이터 수정 */
	int updateMember(Member m);
	
	/* 회원정보 삭제 - 데이터 삭제 */
	int deleteMember(String userId);

	
}
