<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<style>
body {
	font-family: sans-serif;
	line-height: 1.6;
	margin: 20px;
}

.insertBo {
	width: 80%;
	max-width: 800px;
	margin: 20px auto;
	padding: 20px;
	border: 1px solid #555555;
	border-radius: 5px;
	box-shadow: 0 1px 20px #whitegray;
}

.insertBo h2 {
	text-align: center;
	margin-bottom: 20px;
}

.insertBo label {
	display: block;
	margin-top: 10px;
}

.insertBo input[type="text"], .insertBo textarea {
	width: calc(100% - 12px);
	padding: 10px;
	margin-top: 5px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	font-size: 1em;
}

.insertBo textarea {
	height: 200px;
	resize: none;
}

.insertBo button {
	background-color: pink;
	color: white;
	font-weight: bold;
	padding: 5px 15px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	margin-top: 20px;
}

.insertBo button:hover {
	background-color: lightpink;
}
</style>
</head>
<body>
	<jsp:include page="menubar.jsp" />
	<div class="insertBo">
		<h2>게시글 작성</h2>
		<form action="insert.bo" method="post">
			<label for="title">제목</label>
				<input type="text" name="title" id="title" required>
			<label for="content">내용</label>
				<textarea name="content" id="content" placeholder="내용을 입력하세요" required></textarea> 
			<label for="writer">ID</label><input type="number" name="writer" id="writer" /><br>
			<button type="submit">등록</button>
		</form>
	</div>
</body>
</html>