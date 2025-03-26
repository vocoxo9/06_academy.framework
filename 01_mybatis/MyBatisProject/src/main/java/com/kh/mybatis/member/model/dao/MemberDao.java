package com.kh.mybatis.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public class MemberDao {

	public int insertMember(SqlSession sqlSession, Member m) {
		return sqlSession.insert("memberMapper.insertMember", m);
	}

	public Member selectMember(SqlSession sqlSession, Member m) {
		// select --> selectOne : 한 행만 조회
		//			  selectList : 여러 행 조회
		// * 데이터 조회 시 결과가 없을 경우 null을 반환
		return sqlSession.selectOne("memberMapper.selectMember", m);
	}

	public int updateMember(SqlSession sqlSession, Member m) {
		return sqlSession.update("memberMapper.updateMember", m);
		
	}

	
}
