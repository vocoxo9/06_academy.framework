package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.member.service.MemberService;
import com.kh.mybatis.member.service.MemberServiceImpl;

/**
 * Servlet implementation class IdCheckController
 */
@WebServlet("/idCheck")
public class IdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IdCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 전달된 데이터 추출
		String id = request.getParameter("userId");
		// Service 객체에게 전달하여 작업 요청
		MemberService service = new MemberServiceImpl();
		int count = service.countMemberByUserId(id);
		
		// 결과에 따른 처리
		// 결과값은 0 아니면 1임
		if (count == 0) {
			// 	count == 0 이라면(아이디가 사용 가능하다면) 'YYY'값을 응답
			response.getWriter().print("YYY");
		} else {
			// 	count != 0 이라면(아이디가 사용 불가능하다면) 'NNN'값을 응답
			response.getWriter().print("NNN");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
