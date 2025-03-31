package com.kh.mybatis.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.board.model.vo.Board;

public class BoardDao {

	public List<Board> getboardList(SqlSession sqlSession) {
		return sqlSession.selectList("boardMapper.getBoardList");
	}
	
	public int insertBoard(SqlSession sqlSession, Board b) {
		return sqlSession.insert("boardMapper.insertBoard", b);
	}

	public Board getBoardDetail(SqlSession sqlSession, int boardNo) {
		return sqlSession.selectOne("boardMapper.getBoardDetail", boardNo);
	}

	public int deleteBoard(SqlSession sqlSession, int boardNo) {
		return sqlSession.delete("BoardMapper.deleteBoard", boardNo);
	}
}
