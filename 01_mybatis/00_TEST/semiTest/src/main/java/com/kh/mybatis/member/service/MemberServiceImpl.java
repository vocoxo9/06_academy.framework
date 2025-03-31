package com.kh.mybatis.member.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;
import com.kh.mybatis.template.Template;

public class MemberServiceImpl implements MemberService{
	private MemberDao mDao = new MemberDao();
	
	@Override
	public int insertMember(Member m) {
		/*
		 * 기존(JDBC) 방식
		 * - Connection 객체 생성
		 * - DAO에게 Connection 객체와 전달받은 데이터 전달(요청)
		 * - 결과에 따라(DML) -> 트랜잭션 처리
		 * - Connection 객체 반납(close)
		 * - 결과를 리턴
		 */
		
		/* Mybatis 방식 */
		SqlSession sqlSession = Template.getSqlSession();
		int result = mDao.insertMember(sqlSession, m);

		if(result > 0) {
			sqlSession.commit();
		}
		// rollback은 경우 여러 개의 DML을 실행시켰을 경우만 작성하고 --> 게시판 등록 시
		// 단일 행일 경우 생략 가능하다
		sqlSession.close();
		
		return result;
	}

	@Override
	public Member selectMember(Member m) {
		// * SqlSession 객체 생성
		SqlSession sqlSession = Template.getSqlSession();
		
		// * Dao 객체에게 작업 요청
		Member loginUser = mDao.selectMember(sqlSession, m);
		
		// * SqlSession 객체 반납(close)
		sqlSession.close();
		
		// * 결과 리턴
		return loginUser;
	}

	@Override
	public int updateMember(Member m) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = mDao.updateMember(sqlSession, m);
		
		if(result > 0 ) {
			sqlSession.commit();
		}
		// *DML 실행되는 경우, 트랜잭션 처리(commit만 rollback 생략 가능)
		sqlSession.close();
		
		return result;
	}

	@Override
	public int deleteMember(String userId, String userPwd) {
		SqlSession sqlSession = Template.getSqlSession();
		
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		int result = mDao.deleteMember(sqlSession,m);
		
		// (2) Map 형태로 가공처리 후 요청
//		HashMap<String, String> hMap = new HashMap<>();
//		hMap.put("id", userId);
//		hMap.put("pwd", userPwd);
//		mDao.deleteMember(sqlSession, hMap);
		
		if(result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		return result;
	}
	
	@Override
	public int updatePassword(String userId, String userPwd, String newPwd) {
		SqlSession sqlSession = Template.getSqlSession();
		
		HashMap<String, String> data = new HashMap<>();
		data.put("userId", userId);
		data.put("userPwd", userPwd);
		data.put("newPwd", newPwd);
		
//		Member m = new Member();
//		m.setUserId(userId);
//		m.setUserPwd(userPwd);
//		// newPwd에 대한 값...?
		
		int result = mDao.updatePassword(sqlSession, data);
		if(result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		return result;
	}

	@Override
	public int countMemberByUserId(String userId) {
		// SqlSession 객체 생성
		SqlSession sqlSession = Template.getSqlSession();
		
		// Dao 객체에게 SqlSession 객체와 데이터 전달
		int result = mDao.countMemberByUserId(sqlSession, userId);
		
		sqlSession.close();
		
		return result;
	}

	@Override
	public Board readBoard() {
		SqlSession sqlSession = Template.getSqlSession();
		Board b = mDao.readBoard(sqlSession);
		sqlSession.close();
		return b;
	}

	
}
