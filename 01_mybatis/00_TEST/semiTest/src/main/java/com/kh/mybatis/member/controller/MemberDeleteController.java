package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.member.model.vo.Member;
import com.kh.mybatis.member.service.MemberServiceImpl;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
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
		// request.setCharacterEncoding("UTF-8");
		// --> 한글 포함 안 되어 생략해도 됨
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		MemberServiceImpl service = new MemberServiceImpl();
		int result = service.deleteMember(userId, userPwd);
		
		// TODO : 결과값 처리
		if(result > 0) {
			// 탈퇴 성공
			// "회원 탈퇴가 완료되었습니다. 그동안 감사합니다." 메시지 저장
			// 메인 페이지로 url 재요청 -> 로그인 정보가 제거되어야 할 것
			request.getSession().invalidate();
			// * -> invalidate는 session 영역에 모든 값을 비우기 때문에
			// * 메시지 작성 후 호출하면 메시지까지 비워짐
			
			request.getSession().setAttribute("alertMsg", "회원 탈퇴가 완료되었습니다. 그동안 감사합니다.");
			response.sendRedirect(request.getContextPath());			
			// request.getSession().removeAttribute("loginUser"); --> session 영역에서 해당 키값만 제거
		} else {
			// 탈퇴 실패
			// "회원 탈퇴에 실패했습니다." 메시지 저장
			// 에러페이지로 응답
			request.getSession().setAttribute("errorMsg", "회원 탈퇴에 실패했습니다.");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

}
