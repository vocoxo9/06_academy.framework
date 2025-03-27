package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.member.service.MemberServiceImpl;

/**
 * Servlet implementation class MemberPwdUpdateController
 */
@WebServlet("/updatePwd.me")
public class UpdatePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글이 포함되어 있지 않아 인코딩 생략
		// request.setCharacterEncoding("UTF-8");
		
		// 전달된 데이터 추출(아이디, 현재 비번, 변경할 비번)
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String newPwd = request.getParameter("newPwd");
		
		MemberServiceImpl service = new MemberServiceImpl();
		int result = service.updatePassword(userId, userPwd, newPwd);
		
		// 결과값 처리
		if(result > 0) {
			// 비밀번호 변경 성공
			//		로그아웃 처리 -> 세션 영역 비우기, "비밀번호 변경 성공했습니다" 메시지 저장, 메인 페이지로 url 재요청
			request.getSession().invalidate();
			request.getSession().setAttribute("alertMsg","비밀번호를 변경하였습니다.");
			response.sendRedirect(request.getContextPath());
//			request.getRequestDispatcher(request.getContextPath()).forward(request, response);
		} else {
			request.setAttribute("errorMsg","비밀번호 변경에 실패하였습니다.");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

}
