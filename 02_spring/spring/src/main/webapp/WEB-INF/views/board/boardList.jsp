<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, 
				com.kh.spring.board.model.vo.Board,
				com.kh.spring.common.PageInfo" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판 목록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
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

        #boardList {text-align: center;}
        #boardList>tbody>tr:hover{cursor:pointer;}

        #pagingArea {width:fit-content; margin:auto;}

        #searchForm {width:80%; margin: auto;}
        #searchForm>* {float:left; margin:5px;}
        
        .select {width:25%;}
        .text {width:48%;}
        .searchBtn{width:20%;}
    </style>
</head>
<body>
    <%-- header --%>
    <%-- 
    	<jsp:include page="../common/header.jsp" /> 
    	include 지시어 자체로 loginUser까지 포함시키기 (header session scope에 저장되어 있음)
    --%>
	<%@ include file="../common/header.jsp" %>
    <div class="outer">
        <br><br>
        <div class="innerOuter" style="padding: 5% 10%">
            <h2>자유 게시판</h2>
            <br>

            <%-- 로그인 시에만 글쓰기 버튼 표시 --%>
            <% 
            	if(loginUser != null){
            %>
            <a href="/board/enrollForm" class="btn btn-secondary" style="float:right">글쓰기</a>
            <% } %>
            <br>
            
            <br>
            <table id="boardList" class="table table-hover" align="center">
                <thead>
                    <th>글번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>조회수</th>
                    <th>작성일</th>
                    <th>첨부파일</th>
                </thead>

                <tbody>
                	<%
                		ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
                	%>
                	<%-- 조회된 목록 표시 --%>
                	<% for (Board b : list) { %>
	                    <tr>
	                        <td><%= b.getBoardNo() %></td>
	                        <td><%= b.getBoardTitle() %></td>
	                        <td><%= b.getBoardWriter() %></td>
	                        <td><%= b.getCount() %></td>
	                        <td><%= b.getCreateDate() %></td>
	                        <td>
	                        	<% if (b.getOriginName() != null) { %>
	                        	■
	                        	<% } %>
	                        </td>
	                    </tr>
                    <% } %>                  
                </tbody>
            </table>
            <br>
            
			<%
				PageInfo pi = (PageInfo)request.getAttribute("pi");
			    int currPage = 0, startPage = 0, endPage = 0, maxPage = 0;
			    if (pi != null) {
			    	currPage = pi.getCurrPage();
			    	startPage = pi.getStartPage();
			    	endPage = pi.getEndPage();
			    	maxPage = pi.getMaxPage();
			    }
			%>
            <div id="pagingArea">
                <ul class="pagination">
                
                    <li class="page-item">
                	<% if (currPage == 1) { %>
                		<a class="page-link disabled">Prev</a>
                	<% } else { %>
                    	<a data-curr="<%= currPage - 1 %>" class="page-link" >Prev</a>
                   	<% } %>
                    </li>
                    
                    <% for(int n=startPage; n<=endPage; n++) { %>
                    	<li class="page-item <% if (currPage == n) { %>active<% } %>">
                    		<a data-curr="<%= n %>" class="page-link"><%= n %></a>
                    	</li>
                    <% } %>
                    
                    <li class="page-item">
                    <% if (currPage == maxPage) { %>
                    	<a class="page-link disabled">Next</a>
                    <% } else {%>
                    	<a data-curr="<%= currPage + 1 %>" class="page-link">Next</a>
                    <% } %>
                    </li>
                </ul>
            </div>

            <br clear="both">

            <form action="/board/list" id="searchForm">
                <div class="select">
                    <select name="condition" id="" class="custom-select form-select">
                        <option value="writer">작성자</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                    </select>
                </div>
                <div class="text">
                    <input type="text" class="form-control" name="keyword" value="${ keyword }">
                </div>
                <button class="searchBtn btn btn-secondary">검색</button>
            </form>
            <br><br>
        </div>
    </div>

    <%-- footer --%>
    <jsp:include page="../common/footer.jsp" />
    
    <script>
    	window.addEventListener('load', function(){
    	
    		/* 검색 조건 초기화 */
    		const condition = "${condition}";
    		console.log("condition::" + condition);
    		
    		if(condition != ""){
    			const options = document.querySelectorAll("#searchForm select[name=condition] option");
    			for(const ele of options){
    				//console.log("option.value::" + ele.value);
    				if(condition == ele.value){
    					ele.setAttribute("selected", true);
    					break;
    				}
    			}
    		}
    		
    		
    		/* 페이징바 클릭 이벤트 추가 */
	    	const pageLink = document.querySelectorAll("#pagingArea a[data-curr]");
	    		    	
	    	for(const ele of pageLink){
	    		ele.onclick = function(){
	    			// * 키워드, 검색 조건 --> request 영역에서 가져오거나, 해당 요소에 접근해서 가져오기
 
	    			const keyword = "${ keyword }";
	    			
	    			let requestUrl = "/board/list?cpage=" + ele.getAttribute("data-curr");
	    				    		
	    			if(keyword != ""){	// 검색 정보가 있을 경우
	    				requestUrl += "&keyword=" + keyword
	    							+ "&condition=" + document.querySelector("select[name=condition]").value;
	    			}
	    			ele.href = requestUrl;
	    			
	    		}
	    	}
	    	
    	// 자유게시판 목록 클릭 시 상세페이지로 이동
    	const boardTr = document.querySelectorAll("#boardList tbody tr");
    	
    	for(const ele of boardTr){
    		ele.onclick = function(){
    			location.href = "/board/detail?bno=" + ele.children[0].innerText;
    		}
    	}	    	
   	});
    	
    </script>
</body>
</html>