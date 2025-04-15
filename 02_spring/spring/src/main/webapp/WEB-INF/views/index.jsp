<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Spring Project</title>	
	<style>
        .outer {
            background-color: #e7e7e7;
            width: 80%;
            margin: auto;
        }
        .inner-area {
            border: 1px solid #000025;
            width: 80%;
            margin: auto;
            padding: 5% 15%;
            background: #e7ecf7;
        }
        
        table {width:100%;}
        table * {margin:5px;}
    </style>
    
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    
	</head>
	<body>
		<%-- <h2> Hello, Spring </h2> --%>
		<%-- 헤더 영역 포함(common/header.jsp) --%>
		<jsp:include page="common/header.jsp" />
		
		<main class="outer">
			<br><br>
			<div class="inner-area">
				<h4>게시글 Top 5</h4>
		          <br>
		          <table id="top5-board-list" class="table table-hover" align="center">
		              <thead>
		                  <tr>
		                      <th>글번호</th>
		                      <th>제목</th>
		                      <th>작성자</th>
		                      <th>조회수</th>
		                      <th>작성일</th>
		                      <th>첨부파일</th>
		                  </tr>
		              </thead>
		              <tbody align="center">
		                  <tr>
		                      <td colspan="6" rowspan="4" align="center">
		                          <div class="spinner-border text-primary"></div>
		                      </td>
		                  </tr>
		              </tbody>
		          </table>
		          
		          <script>
		          	window.addEventListener('load', function(){
		          		$.ajax({
		          			url : '/api/board/top5',
		          			method : 'get',
		          			success : function(list){
		          				console.log(list);
		          				
		          				// * 조회 결과를 화면에 표시
		          				let top5list = "";
		          				
		          				for(const l of list){
		          					top5list += "<tr>" 
				          							+ "<td>" + l.boardNo + "</td>"
				          							+ "<td>" + l.boardTitle + "</td>"
				          							+ "<td>" + l.boardWriter + "</td>"
				          							+ "<td>" + l.count + "</td>"
				          							+ "<td>" + l.createDate + "</td>";
				          						if(l.originName != null) {
				          							top5list += "<td>■</td>";
				          						} else {
				          							top5list += "<td></td>";
				          						}
		          							top5list	+= "</tr>";
		          				}
		          				$("#top5-board-list tbody").html(top5list);
		          			},
		          			error : function(){
		          				console.log(err);
		          			}
		          		});
		          		
			          	const boardTr = document.querySelectorAll("#top5-board-list tbody tr");
	    	
				    	for(const ele of boardTr){
				    		ele.onclick = function(){
				    			location.href = "/board/detail?bno=" + ele.children[0].innerText;
				    		}
	    				}
		          		
		          	});
		          	
		          	
		          </script>			
		          
				</div>		
		</main>
		
		<%-- 푸터 영역 포함(common/footer.jsp) --%>
		<jsp:include page="common/footer.jsp" />
	</body>
</html>
