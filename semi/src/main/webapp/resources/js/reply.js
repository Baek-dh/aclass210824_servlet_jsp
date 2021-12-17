// 댓글 목록 조회
function selectReplyList() {

    // ajax를 이용해 비동기로 댓글 목록 조회 및 출력
    $.ajax({
        url : contextPath + "/reply/select",
        data : {"boardNo": boardNo},
        type : "GET",
        dataType : "JSON",  // 반환되는 데이터 형식 지정 -> 응답 받은 후 형변환 진행
       
        success : function(rList){
            console.log(rList);

            $("#replyListArea").empty(); // 기존 댓글 내용 모두 삭제

            $.each( rList , function(index, reply){
                //console.log(reply.replyContent);

                //const li = $("<li>").addClass("reply-row");
                const li = $('<li class="reply-row">');

                const div = $('<div>');

                const rWriter = $('<p class="rWriter">').text(reply.memberName)  ;
                const rDate = $('<p class="rDate">').text('작성일 : ' + reply.replyCreateDate);

                // div에 자식으로 rWriter, rDate 추가
                div.append(rWriter, rDate);

                // 댓글 내용
                const rContent = $('<p class="rContent">').html(reply.replyContent);

                li.append(div, rContent);

                // 댓글 작성자 == 로그인 멤버 -> 버튼 영역 생성
                if(reply.memberNo == loginMemberNo){

                    const replyBtnArea = $('<div class="replyBtnArea">');

                    const showUpdate = $('<button>').addClass('btn btn-primary btn-sm ml-1').text('수정');
                    showUpdate.attr("onclick", "showUpdateReply("+reply.replyNo+", this)");

                    const deleteReply = $('<button>').addClass('btn btn-primary btn-sm ml-1').text('삭제');      
                    deleteReply.attr("onclick", "deleteReply("+reply.replyNo+")");

                    replyBtnArea.append(showUpdate, deleteReply);
                    li.append(replyBtnArea);
                }


                $("#replyListArea").append(li);

            });

        },

        error : function(req, status, error){
            console.log("댓글 목록 조회 실패");
            console.log(req.responseText);
        }
    });
    

    
}

// -----------------------------------------------------------------------------------------
// 댓글 등록
function addReply() {
    
    // 게시글 번호(boardNo), 로그인한 회원 번호(loginMemberNo), 댓글 내용

    if(loginMemberNo == ""){ // 로그인이 되어 있지 않은 경우
        alert("로그인 후 이용해 주세요.");
  
    }else{ // 로그인한 경우

        // 댓글 미작성한 경우
        if(  $("#replyContent").val().trim().length == 0  ){
            alert("댓글을 작성한 후 버튼을 클릭해주세요.");
            $("#replyContent").focus();
     
        }else{ // 댓글을 작성한 경우

            $.ajax({
                url : contextPath + "/reply/insert",
                data : {"memberNo":loginMemberNo,  
                        "boardNo":boardNo, 
                        "replyContent":$("#replyContent").val()   },     
                type : "POST",
                success : function(result){
                    console.log(result);

                    if(result > 0){
                        alert("댓글 삽입 성공");
                        $("#replyContent").val(""); // 작성한 댓글 내용 삭제

                        selectReplyList(); // 댓글 조회 함수를 호출하여 댓글 화면 다시 만들기
                    }else{
                        alert("댓글 삽입 실패");

                    }

                },

                error : function(req, status, error){
                    console.log("댓글 삽입 실패");
                    console.log(req.responseText);
                }
            });

        }



    }


}


// -----------------------------------------------------------------------------------------
// 댓글 수정 폼으로 전환
function showUpdateReply(replyNo, el) {

   


}

//-----------------------------------------------------------------------------------------
//댓글 수정폼에서 취소 시 원래대로 돌아가기

function updateCancel(el) {

}


//-----------------------------------------------------------------------------------------
// 댓글 수정

function updateReply(replyNo, el) {



}




//-----------------------------------------------------------------------------------------
//댓글 삭제
function deleteReply(replyNo) {


}

