<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax Project</title>
</head>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<body>
	<h1>Ajax</h1>
	
	<p>
		<i>Asynchronous JavaScript And XML</i>의 약자 <br>
		서버로부터 데이터를 가져와 전체 페이지를 바꾸지 않고 일부만 로드하여 변경하도록 하는 기법 <br>
		기존의 a태그를 사용하거나, form태그의 submit 요청 방식은
		"동기식"요청 => 응답 페이지가 전달되어야 결과 확인 가능 <br>
	</p>
	
	<h3>* 동기식 vs 비동기식</h3>
	- 동기식
	<ul>
		<li>요청 처리 후 해당 응답 페이지가 전달된 후에 다음 작업 가능</li>
		<li>
			서버에 요청한 결과에 따라 응답이 지연되면 무작정 기다려야 함  (지연 시 화면이 하얗게 표시됨)
		</li>
		<li>전체 페이지를 응답하기 때문에 화면에 깜박거림 현상이 있음</li>
	</ul>
	
	- 비동기식(ajax)
	<h4>* 장점</h4>
	<ol>
		<li>현재 페이지를 그대로 유지하면서 추가적인 작업(요청)이 있는 경우 사용 가능</li>
		<li>요청 시 다른 페이지로 변경되지 않음 => 페이지 깜박거림 없음</li>
		<li>요청을 하고 응답(데이터)가 돌아올 때까지 기다리지 않고, 다른 작업을 수행할 수가 있다</li>
		<li>
			ex) 검색어 자동완성, 아이디 중복체크, 찜하기/해제하기, 추천, 댓글 등등
		</li>
	</ol>
	
	<h4>* 단점</h4>
	<ol>
		<li>현재 페이지의 리소스가 지속적으로 쌓임 => 페이지가 느려질 수 있음</li>
		<li>오류 발생 시 디버깅</li>
		<li>요청 처리 후 응답된 데이터를 가지고 새로운 요소(태그)를 만들어서 화면에 표시해야 함 
			=> DOM 요소를 만드는 구문을 잘 익혀둬야 함
		</li>
	</ol>
	
	<hr>
	
	<h3>* AJAX 구현 방식</h3>
	<ul>
		<li>
			순수 자바스크립트 방식 (브라우저마다 방식을 다르게 해야할 수도 있음)
		</li>
		<li>
			jQuery 방식 (코드가 간결하고 사용하기 쉬움)
		</li>
	</ul>
	
	<pre>
		* jQuery 방식으로 AJAX 통신
		$.ajax({
			속성명 : 속성값,
			속성명 : 속성값,
			...
		});
		
		- 주요 속성
			+ url : 요청주소
			+ type|method : 요청 전송 방식(get/post/...)
			+ data : 요청 시 전달할 값
			
			+ success : 통신 성공 시 실행할 함수 정의
			+ error : 통신 실패 시 실행할 함수 정의(url 잘못되었거나, 전송 방식이 맞지 않거나 등)
			+ complete : 통신 성공/실패 상관없이 무조건 실행할 함수 정의
	</pre>
	
	<hr>
	
	<h3>jQuery 방식을 이용한 AJAX 테스트</h3>
	<h4>1. get방식으로 서버에 요청 및 응답받기</h4>
	<input type="text" id="data1" />
	<button id="btn1">전송</button>
	<br>
	응답 결과 : <span id="result1">응답 없음</span>
	
	<script>
		// [전송] 클릭 되었을 때 /jqAjax1.do 입력된 값을 전송(요청)
		$("#btn1").click(function() {
			// 기존 방식 (동기식)
			// location.href = 'jqAjax1.do?data=' + $("#data1").val() ;
			
			// 비동기식 방식(ajax)
			$.ajax({
				url : 'jqAjax1.do',
				type : 'get',
				data : { data : $("#data1").val() },
				
				success : function(result){
					// 통신 성공 시 실행할 내용
					// => 응답 데이터(결과)가 result 변수에 담겨져 있을 것
					console.log("** jqAjax1.do 통신 성공 **");
					console.log(result);
					
					// result1 아이디 속성을 가진 요소에 응답 결과를 표시(출력)
					$("#result1").text(result);
				},
				error : function(err){
					// 통신 실패 시 실행할 내용
					// => 오류 관련 내용이 err 변수에 담겨져 있을 것임
					console.log("** jqAjax1.do 통신 실패 **");
					console.log(err);
				},
				complete : function(){
					// 통신 성공여부 상관 없이 실행할 내용
					console.log("** jqAjax1.do 통신 완료 **");
				}
			});
		});
		
	</script>
	
	<hr>
	
	<h4>2. post 방식으로 서버에 요청 및 응답받기</h4>
	이름 : <input type="text" id="name" /> <br>
	나이 : <input type="number" id="age" value="20" /> <br>
	<button id="btn2">전송</button>
	<br>
	응답 결과 : <div id="result2">응답 없음</div>
	
	<script>
		// [전송] 클릭되었을 때 입력된 값들(이름, 나이)을 jqAjax2.do로 전송(post)
		$("#btn2").click(function(){
			$.ajax({
				url : 'jqAjax2.do',
				method : 'post',
				data : { 
					name : $("#name").val(),
					age : $("#age").val()
				},
				success : function(result){
					console.log("** jqAjax2.do 통신 성공 **");
					console.log(result);
					
					// * 문자열 데이터로 응답 시 처리
					$("#result2").text(result);
					
				/*
					// * 배열 객체(JSONArray){} 응답된 데이터 처리
					$("#result2").text("");	// "응답없음 내용 제거 (초기화)";
					for(let d of result){
						$("#result2").append(
							"<p>" + d + "</p>"
						);
						// => 결과가 배열형태이므로 반복문을 사용하여 요소 추가 가능!
					}						
				*/
				
				
					// * 일반 객체(JSONObejct) 타입으로 응답된 데이터 처리
					// result => {name: 'xxx', age: 'xxx'}
					
					console.log(result.name);
					console.log(result['age']);
					
					// 응답 결과 부분에 순서 없는 목록 태그로 이름, 나이 출력
					// const element = "<ul><li>" + result.name +"</li><li>" + result.age + "</li></ul>";
					
					$("#result2").html("<ul><li>" + result.name +"</li><li>"
										+ result.age + "</li></ul>");
				},
				error : function(err){
					console.log(err);
					console.log("** jqAjax.do 통신 실패 **");
				},
				complete : function(){
					console.log("** jqAjax2.do 통신 완료 **");
				}
			});
		});
	</script>
	
	<br>
	<hr>
	
	<h4>3. vo(dto) 객체로 응답 받기</h4>
	회원 번호 : <input type="number" id="userNo" />
	<button onclick="searchUser();">조회</button>
	<div id="result3"></div>
	
	<script>
		// [조회] 버튼이 클릭되었을 때 입력된 회원 번호를 jqAjax3.do로 전송 (get)
		// 1) const searchUser = () => {}
		// 2) const searchUser = function() {}
		function searchUser(){
			$.ajax({
				url : 'jqAjax3.do',
				method : 'get',
				data : { userNo : $("#userNo").val() },
				// success : ()=>{}
				// success : result => {}
				success : function(user){
					console.log("** jqAjax3.do 통신 성공 **");
					console.log(user);
					const element = "<ul><li>"+ user.userNo +"</li></ul>"
									+ "<li>" + user.userName + "</li>"
									+ "<li>" + user.userId + "</li>"
									+ "<li>" + user.address + "</li>";
					$("#result3").html(element);
				},
				error : function(error){
					console.log("** jqAjax3.do 통신 실패 **");
					console.log(error);
				}
			});
		}
	</script>
	
	<hr>
	
	<h4>4. 객체 배열로 응답 받기</h4>
	<button onclick="selectList();">조회</button>
	<div id="result4">
		<table border="1">
			<thead>
				<tr>
					<th>No</th>
					<th>이름</th>
					<th>아이디</th>
					<th>주소</th>
				</tr>
			</thead>
			<tbody></tbody>
		</table>
	</div>
	
	<script>
		function selectList() {
			$.ajax({
				url : 'jqAjax4.do',
				success : function(list){
					console.log(list);
					
					// list --> JSONArray (배열 형태)
					// console.log(list[0]);
					
					// list[인덱스] --> JSONObject (일반 객체)
					// console.log(list[0].address);
					
					let trList = "";
					for(let i of list){
						// console.log(i);	// 객체(json)
						trList += "<tr>" 
									+ "<td>" + i.userNo + "</td>"
									+ "<td>" + i.userName + "</td>"
									+ "<td>" + i.userId + "</td>"
									+ "<td>" + i.address + "</td>"
								+ "</tr>";
					}
					
					$("#result4 tbody").html(trList);
				},
				error : function(error){
					console.log("** jqAjax4.do 통신 실패 **");
					console.log(error);
				}
			});
		}
	</script>
</body>
</html>
