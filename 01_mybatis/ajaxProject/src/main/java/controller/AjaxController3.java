package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import model.vo.User;

/**
 * Servlet implementation class AjaxController3
 */
@WebServlet("/jqAjax3.do")
public class AjaxController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxController3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userNo = request.getParameter("userNo");
		
		// Member m = new MemberServiceImpl().selectMember(userNo);
		User u = new User(Integer.parseInt(userNo),"홍길동", "hgd123", "서울");
		
		// => vo 객체를 전달(응답)하고자 할 경우, JSONObject 형태로 전달해줘야 함
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("userNo", u.getUserNo());
		jsonObj.put("userName", u.getUserName());
		jsonObj.put("userId", u.getUserId());
		jsonObj.put("adress", u.getAddress());
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonObj);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
