<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.spring.board.model.vo.Board,
				com.kh.spring.board.model.vo.Reply,
				java.util.ArrayList" %>
<% 
	Board b = (Board)request.getAttribute("board"); 
	ArrayList<Reply> rList = (ArrayList<Reply>)request.getAttribute("rList");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 상세보기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    
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
</head>
<body>
    <%-- header --%>
    <%@ include file="../common/header.jsp" %>

    <div class="outer">
        <br><br>
        <div class="inner-area">
            <h2>게시글 상세보기</h2>
            <br>
            <a href="/board/list" class="btn btn-secondary" style="float:right;">목록보기</a>
            <br><br>

            <table align="center" class="table">
                <tr>
                    <th width="100">제목</th>
                    <td colspan="3">
                        ${ board.boardTitle }
                    </td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>
                        <%= b.getBoardWriter() %>
                    </td>
                    <th>작성일</th>
                    <td>
                        <%= b.getCreateDate() %>
                    </td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td colspan="3">
                    <% if ( b.getOriginName() == null) {%>
                    	첨부파일 없음
                    <% } else { %>
                        <a href="<%= b.getChangeName() %>" download>
                        <%= b.getOriginName() %></a>
                    <% } %>
                    </td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td colspan="3"></td>
                </tr>
                <tr>
                    <td colspan="4">
                        <p style="height:150px;">
                            <%= b.getBoardContent() %>
                        </p>
                    </td>
                </tr>
            </table>
            <br>

            <!-- 작성자와 로그인한 계정이 동일한 경우만 표시 -->
            <% if( loginUser.getUserId().equals(b.getBoardWriter()) ) { %>
	            <div align="center">
	                <a href="/board/updateForm?bno=${board.boardNo}" class="btn btn-primary">수정</a>
	                <a href="/board/delete?bno=<%= b.getBoardNo() %>" class="btn btn-danger">삭제</a>
	            </div>
            <% } %>
            <br><br>

            <table id="replyArea" class="table" align="center">
                <thead>
                    <tr>
                        <th colspan="2">
                            <textarea id="content" cols="55" rows="2" class="form-control" style="resize: none;"></textarea>
                        </th>
                        <th style="vertical-align:middle;">
                            <button class="btn btn-secondary">등록</button>
                        </th>
                    </tr>
                    <tr>
                        <td colspan="3">댓글 (<span id="rcount"><%= rList.size() %></span>)</td>
                    </tr>
                </thead>
                <tbody>
                <% for (Reply r : rList) { %>
                    <tr>
                        <th><%= r.getReplyWriter() %></th>
                        <td><%= r.getReplyContent() %></td>
                        <td><%= r.getCreateDate() %></td>
                    </tr>
                    <% } %>                         
                </tbody>
            </table> 
            
            <script>
            	window.addEventListener('load', function(){
            		// [댓글 등록]버튼 클릭 이벤트 => 비동기 통신할 것
            		$("#replyArea button").click(function () {
            			// 입력된 댓글 내용을 추가 요청 => 비동기 통신 (ajax)
            			addReply();
            		});
            	});	
            	
            	// 댓글 추가 함수
            	function addReply() {
            	if($("#replyArea #content").val() == "") {
            		alert("댓글 내용 작성 후 등록 가능합니다.");
            		return;
            	}
            	// 입력된 값이 없을 경우 메시지 표시하고 요청하지 않도록 처리
            	
            		// 비동기 통신 => ajax 
            		$.ajax({
            			//url : '/board/rinsert',
            			url : '/api/board/reply',
            			method : 'post',			// type도 가능
            			data : {			// key : value 형태로 작성
            				replyContent : $("#replyArea #content").val(),		// 댓글 내용
            				replyWriter : '${loginUser.userId}',				// 로그인 사용자의 아이디 (문자) (session 영역에 loginUser 키값으로 저장되어있음)
            				refBno : ${board.boardNo} 							// 게시글 번호 (숫자)
            			},
            			success : function(result){
            				// * result => 응답 데이터(결과)
            				// console.log("댓글 작성 요청 성공");
            				// console.log(result);
            				
            				// * 응답 결과가 "success" 일 경우 댓글 표시되는 부분 변경
            				// => 댓글 목록을 조회하여 표시
            				if (result == "success"){
            					selectReplyList();
            					
            					// 댓글 입력창 초기화
            					$("#replyArea #content").val("");
            				}
            			},
            			error : function(err){
            				// * err => 오류 내용
            				// console.log("댓글 작성 요청 실패");
            				// console.log(err);
            			}
            		});
            	}
            	
            	function selectReplyList(){
            		$.ajax({
            			//url : '/board/rlist',
            			url : '/api/board/reply',
            			// 요청방식 : get (생략 가능)
            			data : {
            				boardNo : ${board.boardNo}
            			},
            			success : function(list){
            				// console.log("--댓글 목록 조회 성공--");
            				// console.log(list);
            				
            				// * 댓글 개수 업데이트
            				$("#replyArea #rcount").text(list.length);
            				
            				// * 댓글 목록 업데이트
            				let replyData = "";
            				for(const reply of list){
            					replyData += "<tr>" 
            								+ "<th>" + reply.replyWriter + "</th>" 
            								+ "<td>" + reply.replyContent + "</td>"
            								+ "<td>" + reply.createDate + "</td>"
            								+ "</tr>";
            				}
            				$("#replyArea tbody").html(replyData);
            			},
            			error : function(err){
	            			console.log("--댓글 목록 조회 실패--");
	            			console.log(err);
            			}
            		});
            	}
            </script>    
            
            <br><br>
        </div>
    </div>

    <%-- footer --%>
    <jsp:include page="../common/footer.jsp" />    
</body>
</html>