package com.kh.todo.member.model.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kh.todo.member.model.vo.Member;

@Mapper
public interface MemberDAO {

	Member selectMember(Member member);
	
	int insertMember(Member member);
	
}
