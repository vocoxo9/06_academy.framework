package com.kh.mybatis.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.dao.BoardDao;
import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.template.Template;

public class BoardService {
	private BoardDao bDao = new BoardDao();
	
	
	public List<Board> getboardList() {
		SqlSession sqlSession = Template.getSqlSession();
		
		List<Board> list = bDao.getboardList(sqlSession);
		sqlSession.close();
		
		return list;
	}
	
	public int insertBoard(Board b) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = bDao.insertBoard(sqlSession, b);
		
		if(result>0) {
			sqlSession.commit();
		}
		sqlSession.close();
		return result;
	}

	public Board getBoardDetail(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		
		Board b = bDao.getBoardDetail(sqlSession, boardNo);
		
		sqlSession.close();
		
		return b;
	}

	public int deleteBoard(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = bDao.deleteBoard(sqlSession, boardNo);
		
		if(result > 0) {
			sqlSession.commit();
		}
		sqlSession.close();
		
		return result;
	}
}
