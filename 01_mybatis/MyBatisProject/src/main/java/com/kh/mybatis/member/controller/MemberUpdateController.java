package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.member.model.vo.Member;
import com.kh.mybatis.member.service.MemberService;
import com.kh.mybatis.member.service.MemberServiceImpl;

/**
 * Servlet implementation class UpdateMemberController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
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
		// POST 요청 시 인코딩 설정 (한글이 포함되어 있을 경우)
		request.setCharacterEncoding("UTF-8");
		
		// * 전달된 데이터 추출(id, email, gender, phone, address)
		String userId = request.getParameter("userId");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		Member m = new Member(userId, email, gender, phone, address);
		
		// * Service 객체에 전달
		MemberService service = new MemberServiceImpl();
		int result = service.updateMember(m);
		
		// * 결과에 따라
		//			수정 성공 시, "수정 성공했습니다." 메시지 저장
		//			마이페이지로 url 재요청
		if(result > 0) {
			request.getSession().setAttribute("alertMsg", "수정 성공했습니다.");
			response.sendRedirect(request.getContextPath());
		} else {
			//			수정 실패 시, "수정 실패했습니다." 메시지 저장
			//			에러페이지로 응답
			request.getSession().setAttribute("errorMsg", "수정 실패했습니다");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

}
