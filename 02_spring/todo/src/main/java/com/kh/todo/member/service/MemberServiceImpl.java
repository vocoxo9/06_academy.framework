package com.kh.todo.member.service;

import org.springframework.stereotype.Service;

import com.kh.todo.member.model.dao.MemberDAO;
import com.kh.todo.member.model.vo.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

	private final MemberDAO memberDAO;
	
	@Override
	public Member selectMember(Member member) {
		return memberDAO.selectMember(member);
	}

	@Override
	public int insertMember(Member member) {
		return memberDAO.insertMember(member);
	}

}
