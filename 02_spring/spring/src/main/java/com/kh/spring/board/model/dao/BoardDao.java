package com.kh.spring.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.kh.spring.board.model.vo.Board;

@Mapper
public interface BoardDao {
	/* 게시글 목록 조회 */
	ArrayList<Board> selectBoardList(RowBounds rb);

	/* 게시글 개수 조회 */
	int selectBoardCount();
	
	
	
}
