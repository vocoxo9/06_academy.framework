<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis project</title>
</head>
<body>
	<h1>Hello, Mybatis</h1>
	<%-- 
		* 프레임워크(Framework)
			- 개발자가 보다 편리한 환경에서 개발할 수 있도록 제공하는 틀, 뼈대
			- 소프트 웨어 개발 시 공통적으로 사용되는 라이브러리, 개발도구, 인터페이스 등 의미
			
		* 프레임워크 필요성
			- 규모가 큰 프로젝트 작업 시 많은 개발자가 필요로 할 것
				=> 자유롭게 설계 또는 구현하지 않고, 가이드라인에 따라 설계하고 구현해야 함
			- 개발 범위가 정해져 있음
			- 개발자들을 위한 다양한 도구들을 지원
			
		* 장점 : 개발 시간 단축, 유지 보수 용이
		* 단점 : 익숙해지는 데 시간이 필요, 프레임워크에 의존 시 개발 능력이 다소 떨어질 수 있음
		
		* 종류
			- 영속성 프레임워크 : 데이터 관련 CRUD 기능들을 보다 편리하게 작업할 수 있도록 제공
			  ex) Mybatis, hibernate, jpa, ...
			  
			- 자바 프레임워크 : 웹 어플리케이션에 초점을 맞추어 필요한 기능들을 제공
			  ex) spring(framework)
			  
			- 화면구현 프레임워크 : 프론트엔드를 보다 쉽게 구현할 수 있도록 제공
			  ex) bootstrap
			  
			- 기능 및 지원 프레임워크 : 특정 기능이나 업무 수행에 도움을 줄 수 있는 프레임워크
			  ex) Log4j, JUnit, ... 
	 --%>
	 
	 <%-- main.jsp 페이지로 포워딩 --%>
	 <jsp:forward page="WEB-INF/views/main.jsp"/>
	 <%-- <jsp:forward page="WEB-INF/views/main.jsp"></jsp:forward> --%>
</body>
</html>