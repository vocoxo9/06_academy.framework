<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style>
body {
	font-family: sans-serif;
	text-align: center;
	margin: 20px;
}

.bordList {
	width: 90%;
	max-width: 1200px;
	border-collapse: collapse;
	margin: 20px auto;
}

.bordList th, .bordList td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

.bordList th {
	background-color: #f2f2f2;
}

.bordList tr:hover {
	background-color: #f1f1f1;
}

.board-title {
	text-decoration: none;
	font-weight : bold;
	color: black;
}

.board-title:hover {
	text-decoration: underline;
	color : lightpink;
}

.write-button {
	display: inline-block;
	padding: 10px 20px;
	background-color: pink;
	color: white;
	text-decoration: none;
	font-weight : bold;
	border-radius: 4px;
	margin-top: 10px;
}

.write-button:hover {
	background-color: lightpink;
}

</style>
</head>
<body>
	<jsp:include page="menubar.jsp" />

	<h2>게시판 목록</h2>
	<table class="bordList">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="b" items="${bList}">
				<tr>
					<td>${b.boardNo}</td>
					<td><a href="detail.bo?boardNo=${b.boardNo}"  class="board-title">${b.boardTitle}</a></td>
					<td>${b.boardContent}</td>
					<td>${b.boardWriter}</td>
					<td>${b.count}</td>
					<td>${b.createDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<a href="insert.bo" class="write-button">글쓰기</a>

</body>
</html>