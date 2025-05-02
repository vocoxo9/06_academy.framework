window.addEventListener('load', function(){

    // 목록 클릭 시 상세페이지 모달
    const todoTr = document.querySelectorAll(".todo-table tbody tr");

    for(const tr of todoTr){
        tr.onclick = function(){
            // 각 셀의 데이터 가져오기
            const tds = this.querySelectorAll("td");

            const todoNo = tds[0].innerText;
            const content = tds[1].innerText;
            //console.log(tds[3]);
            
            // 날짜 변환
            function formatDate(str) {
                return "20" + str.replace(/\//g, '-').trim();
            }
            const rawDeadline = tds[3].innerText;
            const deadline = formatDate(rawDeadline);
            console.log(deadline);
            
            // 모달 입력칸에 값 세팅
            $("#todoNo").val(todoNo);
            $("#message-text").val(content);
            $("#recipient-name").val(deadline);
            
            const modal = new bootstrap.Modal(document.getElementById('todoDetailModal'));
            modal.show();
        }
    }
    
    
        // 수정 버튼 클릭 시
        $("#updateButton").click(function () {
            const todoNo = $("#todoNo").val();
            const content = $("#message-text").val();
            const deadline = $("#recipient-name").val();
            const memberNo = $("#memberNo").val();
            
            $.ajax({
                url: '/todo/update',
                type: "POST",
                data: {
                    todoNo: todoNo,
                    content: content,
                    deadline: deadline,
                    memberNo : memberNo
                },
                success: function (result) {
                    if(result === "success"){
                        location.reload();			
                    } else {
                        alert("TODO 수정에 실패하였습니다.");
                    }
                },
                error: function () {
                    alert("통신 실패");
                }
            });
        });
    
        // 삭제 버튼 클릭 시
        $("#deleteButton").click(function () {
            const todoNo = $("#todoNo").val();
            console.log(todoNo);
            if (!confirm("정말 삭제하시겠습니까?")) return;

            $.ajax({
                url: '/todo/delete',
                type: "POST",
                data: { todoNo: todoNo },
                success: function (result) {
                    if(result == "success"){
                        alert("삭제 성공");
                        location.reload();					
                    } else {
                        alert(" 삭제 실패");
                    }
                },
                error: function () {
                    alert("통신 실패");
                }
            });
        });
});