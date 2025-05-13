package com.academy.review.board.service;

import com.academy.review.board.model.dao.BoardDAO;
import com.academy.review.board.model.vo.BoardVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardDAO boardDAO;
    public BoardService(BoardDAO boardDAO){
        this.boardDAO = boardDAO;
    }

    public List<BoardVO> getBoardList() {

        boardDAO.selectBoardList();

        return null;
    }
}
