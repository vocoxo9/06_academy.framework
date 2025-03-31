package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		// jsp에서 name 속성
		String userId = request.getParameter("userId");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		Member m = new Member(userId, email, gender, phone, address);
		// ---> 수정할 데이터가 저장되어 있는 회원 객체
		
		// * Service 객체에 전달 *
		MemberService service = new MemberServiceImpl();
		int result = service.updateMember(m);
		
		// * 결과에 따라
		if(result > 0) {
			//	+ 변경된 회원 정보를 조회하여 session 영역에 저장(키값:loginUser)
			HttpSession session = request.getSession();
			Member updateM = (Member)session.getAttribute("loginUser");
			// ---> 처음 로그인 했을 때 회원 정보(아이디, 비밀번호 사용하기 위해)
			Member updateMember = service.selectMember(updateM);
			// ---> 수정사항이 반영된 회원 정보 (아이디, 비밀번호로 조회하여 가져 온 정보를 담고 있음)
			// ---> 새로운 데이터를 조회해서 session 영역에 저장했다...
			//	+ id, pwd -> session 영역에 저장되어 있는 loginUser 키값에 대한 데이터
			request.getSession().setAttribute("loginUser",updateMember);
			
			//			* 수정 성공 시, "수정 성공했습니다." 메시지 저장
			request.getSession().setAttribute("alertMsg", "수정 성공했습니다.");
			
			//			* 마이페이지로 url 재요청
			response.sendRedirect(request.getContextPath() + "/mypage.me");
			// ---> 모든 servlet은 contextpath 뒤에 붙음 (/mybatis/mypage.me) 를 전달
		} else {
			//			* 수정 실패 시, "수정 실패했습니다." 메시지 저장
			//			* 에러페이지로 응답
			request.setAttribute("errorMsg", "회원 정보 수정 실패했습니다");
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

}
