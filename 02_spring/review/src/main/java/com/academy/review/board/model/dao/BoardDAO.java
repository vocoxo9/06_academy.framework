package com.academy.review.board.model.dao;

import com.academy.review.board.model.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardDAO {

    public List<BoardVO> selectBoardList();

}
