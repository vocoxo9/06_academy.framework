package com.kh.mybatis.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.board.service.BoardService;

/**
 * Servlet implementation class BoardDeleteController
 */
@WebServlet("/detail.bo?/${board.boardNo}/delete.bo")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int boardNo = Integer.parseInt(request.getParameter("boardNo"));
        BoardService service = new BoardService();
        int result = service.deleteBoard(boardNo);
        
        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/board/list");
        } else {
            request.setAttribute("message", "게시글 삭제 실패");
            request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
