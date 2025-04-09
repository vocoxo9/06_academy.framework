<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, 
				com.kh.spring.board.model.vo.Board,
				com.kh.spring.common.PageInfo" %>
<%
	PageInfo pi = (PageInfo)request.getAttribute("pi");
%>
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
    <jsp:include page="../common/header.jsp" />

    <div class="outer">
        <br><br>
        <div class="innerOuter" style="padding: 5% 10%">
            <h2>자유 게시판</h2>
            <br>

            <%-- 로그인 시에만 글쓰기 버튼 표시 --%>
            <a href="" class="btn btn-secondary" style="float:right">글쓰기</a>
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
                    <% for (Board b : list) {%>                    
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
				int currPage = 0, startPage = 0, endPage = 0, maxPage = 0;
			
				if( pi != null){
					currPage = pi.getCurrPage();
					startPage = pi.getStartPage();
					endPage = pi.getEndPage();
					maxPage = pi.getMaxPage();
				}
			%>
			
            <div id="pagingArea">
                <ul class="pagination">
                <% if (currPage == 1){ %>
                    <li class="page-item disabled"><a href="" class="page-link">Prev</a></li>
                <% } else { %>
                	<li class="page-item"><a href="" class="page-link">Prev</a></li>
                <% } %>
                
                
                <% for (int p = startPage; p<=endPage; p++) { %>
                    <li class="page-item">
                    	<a href="/notice/list?cpage=<%= p %>" class="page-link"><%= p %></a>
                    </li>
                <% } %>
                
                
                <% if (currPage == maxPage) {%>
                    <li class="page-item disabled"><a href="" class="page-link">Next</a></li>
                <% } else { %>
                	<li class="page-item"><a href="" class="page-link">Next</a></li>
                <% } %>
                </ul>
            </div>

            <br clear="both">

            <form action="" id="searchForm">
                <div class="select">
                    <select name="condition" id="" class="custom-select form-select">
                        <option value="writer">작성자</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                    </select>
                </div>
                <div class="text">
                    <input type="text" class="form-control" name="keyword">
                </div>
                <button class="searchBtn btn btn-secondary">검색</button>
            </form>
            <br><br>
        </div>
    </div>

    <%-- footer --%>
    <jsp:include page="../common/footer.jsp" />
</body>
</html>