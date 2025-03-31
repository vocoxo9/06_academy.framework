<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세</title>
</head>

<style>
	body {
		margin: 20px;
	}
	
	.detail-container {
		width: 80%;
		height : 330px;
		max-width: 800px;
		margin: 20px auto;
		padding: 20px;
		border: 1px solid #ddd;
		border-radius: 5px;
		box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	}
	
	.detail-title {
		font-size: 1.5em;
		font-weight: bold;
		margin-bottom: 20px;
	}
	
	.detail-table {
		width: 100%;
		border-collapse: collapse;
		margin-bottom: 20px;
	}
	
	.detail-table th, .detail-table td {
		border: 1px solid #ddd;
		padding: 8px;
		text-align: left;
	}
	
	.detail-table th {
		background-color: #f2f2f2;
		width: 100px;
	}
	
	.detail-content {
		line-height: 1.6;
		white-space: pre-wrap;
	}
	
	.detail-buttons {
		text-align: center;
		margin-top: 20px;
	}
	
	.detail-buttons button {
		padding: 8px 20px;
		margin: 0 10px;
		border: none;
		border-radius: 4px;
		cursor: pointer;
	}
	
	.detail-buttons button.edit {
		background-color: pink;
		color: white;
	}
	
	.detail-buttons button.delete {
		background-color: #f44336;
		color: white;
	}
	
	.detail-buttons button.list {
		background-color: lightcoral;
		color: white;
	}
	
	.reply-container{
		text-align : center;
	}
	
	.reply-item {
		margin : auto;
	}
	
	.reply-form {
		margin-top: 20px;
		text-align : center;
	}
	
	.reply-form input[type="text"] {
		width: 70%;
		padding: 10px;
		border: 1px solid #ccc;
		border-radius: 4px;
	}
	
	.reply-form button {
		padding: 8px 18px;
		background-color: lightpink;
		color: white;
		border: none;
		border-radius: 4px;
		cursor: pointer;
	}
	
	.detail-buttons form, .list {float: right;}

</style>
<body>
	<jsp:include page="menubar.jsp" />

	<div class="detail-container">
		<h2 class="detail-title">${board.boardTitle}</h2>
		<table class="detail-table">
			<tr>
				<th>번호</th>
				<td>${board.boardNo}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${board.boardWriter}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${board.createDate}</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${board.count}</td>
			</tr>
			<tr>
				<td colspan="2" class="detail-content">${board.boardContent}</td>
			</tr>
		</table>
		<div class="detail-buttons">
		<form action="update.bo" method="post">
			<button class="edit">수정</button>
			<input type="hidden" name="boardNo" value="${board.boardNo}">
		</form>
		
			<button class="list"><a href="read.bo">목록</a></button>
		
		<form action="delete.bo" method="post">	
			<button class="delete">삭제</button>
			<input type="hidden" name="boardNo" value="${board.boardNo}">
		</form>
		</div>
	</div>

<br><br>

	<div class="reply-container">
	    <h3>댓글 목록</h3>
	    <ul class="reply-list">
	        <c:forEach var="reply" items="${replyList}">
	            <li class="reply-item">
	                <span class="reply-writer">${reply.writer}</span>
	                <span class="reply-content">${reply.content}</span>
	                <span class="reply-date">${reply.createDate}</span>
	            </li>
	        </c:forEach>
	    </ul>
	</div>

	<div class="reply-form">
		<h3>댓글</h3>
		<form action="insert.co" method="post">
			<input type="hidden" name="boardNo" value="${board.boardNo}">
			<input type="text" name="content" placeholder="댓글 내용을 입력하세요" required>
			<button type="submit">등록</button>
		</form>
	</div>
</body>
</html>