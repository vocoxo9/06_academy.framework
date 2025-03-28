package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/jqAjax2.do")
public class AjaxController2 extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
		// => ajax를 사용하여 요청 시 생략 가능함
		resp.setContentType("text/html; charset=utf-8");
		
		// 데이터 추출
		String name = req.getParameter("name");
		String strAge = req.getParameter("age");
		
		// 나이 데이터를 String -> int
		int age = -1;
		if(!strAge.isEmpty()) {
			age = Integer.parseInt(strAge);
		}
		
		/*
		// 문자열 하나의 데이터로 응답
		String result = "이름 : " + name + ", 나이 : " 
					+ (age == -1? "알 수 없음" : age);
		resp.getWriter().println(result);
		*/
		
		
		/*
		// * 여러 개의 데이터를 응답
		resp.setContentType("text/html; charset=utf-8");
		resp.getWriter().print(name);
		resp.getWriter().print(age);
		// => 하나의 문자열로 합쳐져서 응답 데이터가 전달됨
		*/
		
		// * 객체 형태로 응답
		/**
		 * 여러 개의 데이터를 응답하고자 할 때, "JSON"형태로 응답
		 * 	- JSON : JavaScript Object Notation (자바스크립트 객체 표기법)
		 * 	=> ajax 통신을 할 때 자주 사용되는 형식 중 하나
		 * 
		 * >> 자바스크립트 배열 객체 : [값1, 값2, 값3, ...] => JSONArray
		 * >> 자바스크립트 일반 객체 : { 키값1:밸류값1, 키값2:밸류값2, ... } => JSONObject
		 * 
		 * * 라이브러리 추가 필요 (json-simple-x.x.x.jar)
		 * => https://code.google.com/archive/p/json-simple/downloads
		 */
		
		/*
		// * 배열객체(JSONArray)에 담아 응답 --> ArrayList와 유사
		JSONArray jsonArr = new JSONArray();
		jsonArr.add(name); 	//["이름"]
		jsonArr.add(age);	//["나이"]
		
		// resp.setContentType("text/html; charset=utf-8");
		// 응답 데이터가 문자열(String) 타입으로 전달됨
		resp.setContentType("application/json; charset=utf-8");
		// 응답 데이터를 JSON(객체) 형태로 전달하고자 할 때
		// 				문서형식(mime type)을 JSON형태(application/josn)로 설정해야 함
		resp.getWriter().print(jsonArr);
		*/
		
		// * 일반 객체 (JSONObject)에 담아 응답-->	HashMap과 유사
		JSONObject jsonObj = new JSONObject();		// {}
		jsonObj.put("name", name);		// {name : 이름}
		jsonObj.put("age", age);		// {name : 이름, age : 20}
		resp.setContentType("application/json; charset=utf-8;");
		resp.getWriter().print(jsonObj);
		
	}

}
