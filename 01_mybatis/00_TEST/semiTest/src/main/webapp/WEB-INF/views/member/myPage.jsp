<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyBatis World</title>

<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<style>
	#mypage-area table{margin: auto;}
</style>
</head>
<body>
	<%-- alertMsg 키에 데이터가 있을 경우 alert()를 사용하여 메시지 출력 --%>
	<c:if test="${ not empty alertMsg }">
		<script>
			alert("${alertMsg}");
		</script>
		
		<c:remove var="alertMsg" /> <%-- scope="session" 생략 가능 --%>
	</c:if>
	
	<%-- munubar.jsp 포함 --%>
	<jsp:include page="../common/menubar.jsp"/>
	
	<div class="outer">
		<br>
		<h2 align="center">마이페이지</h2>
		<form action="update.me" method="post" id="mypage-area">
			<table>
				<tr>
					<td>* 아이디</td>
					<td><input type="text" class="form-control" name="userId" maxlength="30" value="${ loginUser.userId }" readonly></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" class="form-control" name="userName" value="${ loginUser.userName }" readonly></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;이메일</td>
					<td><input type="email" class="form-control" name="email" value="${ loginUser.email }"></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;성별</td>
					<td>
						<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender" value="M" id="genderM" checked>
						<label class="form-check-label" for="genderM" >남자</label>
					  </div>
					  <div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="gender" value="F" id="genderF">
						<label class="form-check-label" for="genderF">여자</label>
					  </div></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;생년월일</td>
					<td>
						<input type="date" class="form-control" id="birth" value="${ loginUser.birthday }" readonly/>
						<input type="hidden" name="birthday" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;연락처</td>
					<td>
						<input type="tel" class="form-control" name="phone" 
						pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" placeholder="- 포함해서 입력" value="${ loginUser.phone }">
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;주소</td>
					<td><input type="text" class="form-control" name="address" value="${ loginUser.address }"></td>
				</tr>
			</table>
				<br><br>
		
			<div align="center">
				<button type="submit" class="btn btn-sm btn-primary">정보 수정</button>
				<button type="button" class="btn btn-sm btn-secondary" 
					data-bs-toggle="modal" data-bs-target="#updatePwdModal">비밀번호 변경</button>
				<button type="button" class="btn btn-sm btn-danger" 
					data-bs-toggle="modal" data-bs-target="#deleteMemModal">회원 탈퇴</button>
			</div>
		</form>	
			<!-- 회원 탈퇴 모달 -->		
			<div class="modal fade text-dark" id="deleteMemModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" >
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h1 class="modal-title fs-5" id="exampleModalLabel">회원 탈퇴</h1>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			        <form action="delete.me" method="post">
						<%-- 회원ID : 입력받지 x --%>
						<input type="hidden" name="userId" value="${ loginUser.userId }" />
						<p>
							탈퇴 후 복구가 불가능합니다.<br>
							그래도 탈퇴하시겠습니까?
						</p>
						<%-- 회원PWD : 입력 받기 --%>
						<div class="mb-3">
							<label for="password" class="col-form-label">비밀번호 : </label>
							<input type="password" class="form-control" id="password" name="userPwd" required/>
						</div>
						
						<button type="submit" class="btn btn-sm btn-danger float-end">탈퇴하기</button>
			        </form>
			      </div>
			    </div>
			  </div>
			</div>
			
			
			<!-- 비밀번호 변경 모달 -->
			<div class="modal fade text-dark" id="updatePwdModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" >
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h1 class="modal-title fs-5" id="exampleModalLabel">비밀번호 변경</h1>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			        <form action="updatePwd.me" method="post">
			        	<%--요청할 때 userId 값도 전달 --%> 
			        	<input type="hidden" name="userId" value="${ loginUser.userId }" />
						<div class="mb-3">
							<label for="password" class="col-form-label">현재 비밀번호 : </label>
							<input type="password" class="form-control" id="password" name="userPwd" required/>
						</div>
						<div class="mb-3">
							<label for="newPwd" class="col-form-label">변경할 비밀번호 : </label>
							<input type="password" class="form-control" id="newPwd" name="newPwd" required/>
						</div>
						<div class="mb-3">
							<label for="newPwdCheck" class="col-form-label">변경할 비밀번호 확인 : </label>
							<input type="password" class="form-control" id="newPwdCheck" required/>
						</div>
						
						<button type="submit" class="btn btn-sm btn-secondary float-end" onclick="return pwdCheck();">비밀번호 변경하기</button>
						<%-- 함수의 결과를 return 하고 결과에 따라 요청 --%>
			        </form>
			      </div>
			    </div>
			  </div>
			</div>
			
			
			
			
			<!-- script -->
			<script>
				// 모든 요소들이 로드되었을 때 (화면에 표시되었을 때)
				onload = function(){
					// 성별 항목을 적용
					const gender = "${ loginUser.gender }";	// 로그인 한 사용자으 ㅣ성별 정보
					console.log(gender);
					
					// 성별(name=gender) 입력 요소들에 접근
					const gArr = document.querySelectorAll("input[name=gender]");
					// => [E, E]
					for(let gEle of gArr){
						// console.log(gEle.value);
						if(gEle.value == gender){
							// 로그인 사용자의 성별 정보와 해당 요소(gender)의 value 속성값이 같은 경우 checked 값을 true로 처리
							gEle.checked = true;
							break;
						}
					}
					
					// * 생년월일 데이터 가공
					makeBirth();
				}
			
				function makeBirth(){
					// * 생년월일 변경을 허용하지 않는 경우 => DB에서 조회한 데이터 사용
					// const data = "${ loginUser.birthday}";	// ['yyyy','mm','dd']
					
					// * 생년월일 변경을 허용하는 경우 => 입력요소의 저장된 값을 사용
					//		+ onchange 이벤트도 사용 가능
					const data = document.getElementById("birth").value;
					
					const dArr = data.split('-');
					yy = dArr[0].slice(-2);
					mm = dArr[1];
					dd = dArr[2];
		
					console.log(yy, mm, dd);
		
					// yy,mm,dd를 하나로 합쳐서
					// name 속성이 birthday인 요소에 값을 저장
					document.querySelector("#mypage-area input[name=birthday]").value = yy+mm+dd;
				
				}
				
				function pwdCheck(){
					// 변경할 비밀번호 값과 변경할 비밀번호 확인 값이 일치하지 않을 경우 변경 요청하지 않도록 처리
					const newPwd = document.getElementById("newPwd").value;
					// const newPwd = document.getquerySelector("#updatePwdModal #newPwd").value
					const newPwdCheck = document.getElementById("newPwdCheck").value;
					
					// * 모두 입력되지 않았을 때도 요청하지 않도록 처리
					
					/* 
						required 속성이 동작되어 해당 로직 필요 없을 수도...
					const userPwd = document.querySelector("#updatePwdModal #password").value;
					if( userPwd == "" || newPwd = "" || newPwdCheck =""){
						alert("입력되지 않은 값이 있습니다. 확인해주세요.");
						return false;
					}
					*/
					if(newPwd != newPwdCheck){
						alert("변경할 비밀번호 값이 다릅니다. 다시 확인해주세요.");
						return false;
					}
				}
			</script>
	</div>

</body>
</html>