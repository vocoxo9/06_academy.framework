<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EnrollForm</title>
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
    </style>
</head>
<body>
    <%-- header --%>
	<jsp:include page="../common/header.jsp" />
	
    <div class="outer">
        <br><br>
        <div class="inner-area">
            
            <h2>마이페이지</h2>
            <br>
            <form action="/member/updateMember" method="post">

                <div class="form-group">
                    <label for="userId">* ID </label>
                    <input type="text" class="form-control mb-3" name="userId" id="userId" value="${loginUser.userId}" readonly>
                    
                    <label for="userName">* Name </label>
                    <input type="text" class="form-control mb-3" name="userName" id="userName"  value="${loginUser.userName }" required>

                    <label for="email"> &nbsp; Email </label>
                    <input type="email" class="form-control mb-3" name="email" id="email" value="${loginUser.email }" placeholder="Enter Email..">  
                    
                    <label for="age"> &nbsp; Age </label>
                    <input type="number" class="form-control mb-3" name="age" id="age" value="${loginUser.age }" placeholder="Enter Age..">  
                    
                    <label for="phone"> &nbsp; Phone </label>
                    <input type="tel" class="form-control mb-3" name="phone" id="phone" value="${loginUser.phone }" placeholder="Enter Phone(-제외)..">  
                    
                    <label for="address"> &nbsp; Address </label>
                    <input type="text" class="form-control mb-3" name="address" id="address" value="${loginUser.address }" placeholder="Enter Address..">

                    <label for=""> &nbsp; Gender</label> &nbsp;&nbsp;
                    <input type="radio" class="mb-3" name="gender" id="Male" value="M" checked>
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" class="mb-3" name="gender" id="Female" value="F">
                    <label for="Female">여자</label><br>

                </div>
                <br>
                <div class="btns"  align="center">
                    <button type="submit" class="btn btn-primary">수정하기</button>
                    <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">회원탈퇴</button>
                </div>
            </form>
        </div>
        <br><br>
    </div>
    <!-- 회원탈퇴 모달 -->
    <div class="modal fade" id="deleteModal">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h1 class="modal-title fs-5">회원탈퇴</h1>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="/member/deleteMember" method="post">
                <div class="modal-body" >
                    <div align="center">
                        <b>
                            탈퇴 후 복구가 불가능합니다. <br>
                            정말로 탈퇴하시겠습니까?
                        </b>
                    </div>
                    <div class="mb-3">
                        <label for="userPwd" class="col-form-label">비밀번호</label>
                        <input type="password" class="form-control" name="userPwd" placeholder="Enter Password.." id="userPwd" />
                        <input type="hidden" name="userId" value="${ loginUser.userId }"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-danger">탈퇴하기</button>
                </div>
            </form>
          </div>
        </div>
      </div>
      
    <%-- footer --%>
    <jsp:include page="../common/footer.jsp" />
</body>
</html>