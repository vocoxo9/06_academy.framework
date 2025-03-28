package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;

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
//		response.setCharacterEncoding("UTF-8");
		String userNo = request.getParameter("userNo");
		
		// Member m = new MemberServiceImpl().selectMember(userNo);
		User u = new User(Integer.parseInt(userNo),"홍길동", "hgd123", "서울");
		
		
	/*
		// => vo 객체를 전달(응답)하고자 할 경우, JSONObject 형태로 전달해줘야 함
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("userNo", u.getUserNo());
		jsonObj.put("userName", u.getUserName());
		jsonObj.put("userId", u.getUserId());
		jsonObj.put("adress", u.getAddress());
		
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonObj);
		
	*/
		// => 좀 더 간단한 방법으로 처리 (GSON : Google JSON)
		Gson gson = new Gson();
		response.setContentType("application/json; charset=utf-8");
		
		// Gson객체.toJson(vo객체, 응답시 사용되는 스트림);
		gson.toJson(u, response.getWriter());
		/**
		 * Gson 사용 시 vo 객체를 응답하는 경우 JSONObject 형태로 전달
		 * 			이 때, 키값은 vo 객체의 필드명으로 전달
		 * 
		 * ArrayList/배열 데이터를 응답하는 경우 JSONArray 형태로 전달
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
