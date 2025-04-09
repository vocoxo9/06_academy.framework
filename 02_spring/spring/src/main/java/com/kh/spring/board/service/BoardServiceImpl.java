package com.kh.spring.board.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.PageInfo;

@Service
public class BoardServiceImpl implements BoardService{

	// 필드 주입 방식
	// @Autowired
	// private BoardDao bDao;
	
	// 생성자 주입 방식
	private final BoardDao bDao;
	@Autowired
	public BoardServiceImpl(BoardDao bDao) {
		this.bDao = bDao;
	}
	
	/** 
	 * 게시글 개수 조회
	 */
	@Override
	public int selectBoardCount() {
		return bDao.selectBoardCount();
	}

	/**
	 * 게시글 목록 조회
	 */
	@Override
	public ArrayList<Board> selectBoardList(PageInfo pi) {
		// RowBounds 객체 => MyBatis 프레임워크에서 제공하는 객체
		int offset = (pi.getCurrPage() - 1) * pi.getBoardLimit();
		RowBounds rb = new RowBounds(offset, pi.getBoardLimit());
		
		return bDao.selectBoardList(rb);
	}

	@Override
	public Board selectBoardDetail(int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertBoard(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoard(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(int boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertReply(Reply reply) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Reply> selectReplyList(int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int increaseCount(int boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
