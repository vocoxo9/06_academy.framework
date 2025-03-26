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
					<td><input type="password" class="form-control" id="userPwdCheck" required></td>
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
						<label class="form-check-label" for="genderM">남자</label>
					  </div>
					  <div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender" value="F" id="genderF">
						<label class="form-check-label" for="genderF">여자</label>
					  </div></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;생년월일</td>
					<td>
						<!-- onchange makeBirth라는 메소드 발생하게 하여 hidden type에 name 속성 부여 -->
						<input type="date" class="form-control" onchange="makeBirth(this)">
						<input type="hidden" name="birthday">
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;연락처</td>
					<td>
						<input type="tel" class="form-control" name="phone" 
						pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" placeholder="- 포함해서 입력">
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;주소</td>
					<td><input type="text" class="form-control" name="address"></td>
				</tr>
			</table>
				<br><br>
			<div align="center">
					<td><button type="submit" class="btn btn-primary" onclick="return pwdCheck();">회원가입</button></td>
					<td><button type="reset" class="btn btn-secondary">초기화</button></td>
			</div>
		</form>
	</div>

	<script>
		// 날짜 데이터의 형식을 DB형식에 맞게 변경하여 저장
		function makeBirth(target){
			// 입력된 값 가져오기
			const data = target.value;
			// document.querySelector("선택자");
			// document.getElementById("아이디값");
			console.log(data);

			// yyyy-mm-dd 형식에서 yymmdd 형식으로 변경
			// (1) Date 객체 사용
			const birth = new Date(data);
			console.log(birth);
			let yy = birth.getFullYear() % 100;
			let mm = ("0" + (birth.getMonth() + 1)).slice(-2);
			let dd = ("0" + birth.getDate()).slice(-2);
			console.log(yy, mm, dd);
			console.log(mm);
			console.log(dd);

			// (2)String 객체 메소드 사용 => split 메소드
			const dArr = data.split('-');	// ['yyyy','mm','dd']
			yy = dArr[0].slice(-2);
			mm = dArr[1];
			dd = dArr[2];

			console.log(yy, mm, dd);

			// yy,mm,dd를 하나로 합쳐서
			// name 속성이 birthday인 요소에 값을 저장
			document.querySelector("#mem-enroll-area input[name=birthday]").value = yy+mm+dd;
		}

		/*
		const makeBirth = (target) => {

		}
		*/
		
		// 입력된 '비밀번호'값과 '비밀번호 확인'값이 같을 경우 true, 다를 경우 false를 리턴
		function pwdCheck(){
			// 비밀번호 입력값 --> name=userPwd
			// const pwd = document.getElementsByName("userPwd").value;
			const pwd = document.querySelector("#mem-enroll-area input[name=userPwd]").value;
			// 비밀번호 확인 입력값 --> id=userPwdCheck
			const pwdCheck = document.getElementById("userPwdCheck").value;
			
			console.log(pwd, pwdCheck);
			
			// 두 값이 다를 경우 false를 리턴
			if(pwd != pwdCheck){
				alert("비밀번호와 비밀번호 확인 입력 값이 다릅니다.");
				return false;				
			} else{
				return true;
			}
		}

	</script>
</body>
</html>