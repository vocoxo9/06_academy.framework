package com.academy.review.board.controller;

import com.academy.review.board.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BoardController {

    private final BoardService boardService;
    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("/boardList")
    public String getBoardList(){
        boardService.getBoardList();

        return "board/boardList";
    }

}
