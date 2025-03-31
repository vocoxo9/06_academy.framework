package com.kh.mybatis.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.board.model.vo.Board;
import com.kh.mybatis.board.service.BoardService;

/**
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/views/common/insertBoard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int writer = Integer.parseInt(request.getParameter("writer"));
		
		Board board = new Board();
		board.setBoardTitle(title);
		board.setBoardContent(content);
		board.setBoardWriter(writer);
		
		BoardService service = new BoardService();
		int result = service.insertBoard(board);
		
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/read.bo");
		} else {
			request.setAttribute("errorMsg", "게시글 등록에 실패하였습니다");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

}
