// 로그인 시 유효성 검사
function loginValidate(){

    const memberId = document.getElementById("memberId");
    const memberPw = document.querySelector("#memberPw");

    // 아이디가 입력되지 않은 경우
    // "아이디를 입력해주세요." 경고창 출력 후 
    // 아이디 input으로 focus 이동
    if(memberId.value.trim().length == 0){
        alert("아이디를 입력해주세요.");
        memberId.focus();
        return false;
    }

    if(memberPw.value.trim().length == 0){
        alert("비밀번호를 입력해주세요.");
        memberPw.focus();
        return false;
    }

}