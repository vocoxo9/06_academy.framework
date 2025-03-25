<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyBatis World</title>

<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<style>
	#mem-enroll-area table{margin: auto;}
</style>
</head>
<body>
	<%-- munubar.jsp 포함 --%>
	<jsp:include page="../common/menubar.jsp"/>
	
	<div class="outer">
		<br>
		<h2 align="center">회원 가입</h2>
		<form action="insert.me" method="post" id="mem-enroll-area">
			<table>
				<tr>
					<td>* 아이디</td>
					<td><input type="text" class="form-control" name="userId" maxlength="30" required></td>
					<td><input type="button" class="btn btn-sm btn-outline-dark" value="중복체크"></td>
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td><input type="password" class="form-control" name="userPwd" maxlength="100" required></td>
				</tr>
				<tr>
					<td>* 비밀번호 확인</td>
					<td><input type="password" class="form-control" required></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" class="form-control" name="userName" required></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;이메일</td>
					<td><input type="email" class="form-control" name="email"></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;성별</td>
					<td>
						<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender" value="M" id="genderM" checked>
						<label class="form-check-label" for="genderM">남</label>
					  </div>
					  <div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender" value="F" id="genderF">
						<label class="form-check-label" for="genderF">여</label>
					  </div></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;생년월일</td>
					<td><input type="date" class="form-control" name="birthDate"></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;연락처</td>
					<td><input type="tel" class="form-control" name="phone" placeholder="- 포함해서 입력"></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;주소</td>
					<td><input type="text" class="form-control" name="address"></td>
				</tr>
			</table>
				<br><br>
			<table>
				<tr>
					<td><button type="submit" class="btn btn-primary">회원가입</button></td>
					<td><button type="reset" class="btn btn-secondary">초기화</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>