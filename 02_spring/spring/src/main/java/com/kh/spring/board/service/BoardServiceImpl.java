package com.kh.spring.board.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.dto.SearchDto;
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
	public int selectBoardCount(SearchDto searchDto) {
		return bDao.selectBoardCount(searchDto);
	}

	/**
	 * 게시글 목록 조회
	 */
	@Override
	public ArrayList<Board> selectBoardList(PageInfo pi, SearchDto searchDto) {
		// RowBounds 객체 => MyBatis 프레임워크에서 제공하는 객체
		int offset = (pi.getCurrPage() - 1) * pi.getBoardLimit();
		RowBounds rb = new RowBounds(offset, pi.getBoardLimit());
		
		return bDao.selectBoardList(rb, searchDto);
	}

	@Override
	public Board selectBoardDetail(int boardNo) {
		return bDao.selectBoardDetail(boardNo);
	}

	@Override
	public ArrayList<Reply> selectReplyList(int boardNo) {
		return bDao.selectReplyList(boardNo);
	}

	@Override
	public int increaseCount(int boardNo) {
		return bDao.increaseCount(boardNo);
	}
	
	@Override
	public int insertBoard(Board board) {
		return bDao.insertBoard(board);
	}

	@Override
	public int updateBoard(Board board) {
		return bDao.updateBoard(board);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return bDao.deleteBoard(boardNo);
	}

	@Override
	public int insertReply(Reply reply) {
		return bDao.insertReply(reply);
	}

	public ArrayList<Board> selectTopFiveList(){
		return bDao.selectTopFiveList();
		/*
		 	* RowBounds 객체 사용 => 페이징 처리 시 활용했던 객체
		 		RowBounds(시작위치, 개수)
		 		
			RowBounds rb = new RowBounds(0, 5);
		// * 실행할 쿼리문 => 게시글 목록 조회 시 사용했던 쿼리문 사용
		
		 	SearchDto 객체에 정렬 기준 데이터를 추가
		  	SearchDto searchDto = new SearchDto();
		  	searchDto.setOrderby("count");
		  	
		  	return bDao.selectBoardList(rb, searchDto);
		 */
	}
}
