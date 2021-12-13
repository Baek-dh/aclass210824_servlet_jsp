
// 모든 요소에 유효한 값이 작성 되어있는지 확인하는 용도의 객체
const updateCheckObj = {
    phone3 : true,
    email : true
}
// -> 회원 가입 시 이미 유효한 값으로만 데이터가 저장되어 있기 때문에
//    내 정보 페이지에 작성된 모든 값은 유효한 상태이다!


let temp; // 이전에 입력한 전화번호 값 임시 저장

// 전화번호 입력되는 글자 수 제한
$(".phone").on("input", function(e){
    
    // 현재 입력 중인 번호 자리에 작성된 값의 길이가 4를 초과할 경우
    // -> 전화번호를 4글자 초과해서 작성함
    if(  $(this).val().length > 4  ){
        // 글자수 제한 처리
        // -> 초과된 부분을 잘라서 없앰
        const num = $(this).val().slice(0,4); // 4자리만 남음

        // 잘라서 4자리만 남은 값을 
        // 현재 입력중인 input 태그의 value로 대입
        $(this).val(num);
    }

    // e 입력 방지
    if(e.originalEvent.data == "e"){
        $(this).val(temp);
    }else{
        temp = $(this).val();
    }

});


// 전화번호가 변했을 경우 유효성 검사
$(".phone").on("change", function(){

    // 각각 입력된 번호 얻어오기
    const inputPhone2 = document.getElementById("phone2").value;
    const inputPhone3 = document.getElementById("phone3").value;

    // 정규 표현식
    const regExp2 = /^\d{3,4}$/;
    const regExp3 = /^\d{4}$/;

    if( inputPhone2.length == 0  || inputPhone3.length == 0){ // 둘 중 하나라도 빈칸일 경우
        updateCheckObj.phone3 = false;

    }else if(regExp2.test(inputPhone2) && regExp3.test(inputPhone3) ){ // 둘다 유효
        updateCheckObj.phone3 = true;

    }else{ // 둘 중 하나라도 유효 X
        alert("전화번호가 유효하지 않습니다.");
        updateCheckObj.phone3 = false;
        this.focus();
    }

});



// 기존 회원 이메일을 저장하는 변수
const existingEmail = document.getElementById("email").value;

// 이메일 값이 변할 때 유효성 검사 진행
// - 아이디가 4글자 이상인 이메일 주소 형식
//  const regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/;
document.getElementById("email").addEventListener("change", (e) => {

    const inputEmail = e.target.value; // 입력 받은 이메일
    const regExp = /^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/; // 정규식

    // 입력된 이메일이 기존 이메일인 경우
    if(inputEmail == existingEmail){
        updateCheckObj.email = true;

    }else if(inputEmail.length == 0){ // 빈칸
        updateCheckObj.email = false;
    
    }else if(regExp.test(inputEmail)){ // 유효할 때
        //checkEmail.innerText = "유효한 이메일 입니다.";
        //checkEmail.style.color = "green";
        //signUpCheckObj.email = true;

        // **************************************************
        // 이메일 중복 검사(AJAX)

        $.ajax({
            url : "emailDupCheck", // 필수 속성!
            type : "GET",
            data : {"inputEmail" : inputEmail}, // 파라미터
            success : function(result){
                
                if(result  ==  0){ // 사용 가능
                    updateCheckObj.email = true;
              
                }else{ // 중복
                    alert("이미 사용중인 이메일 입니다.");
                    updateCheckObj.email = false;
                }

            },

            error : function(request, status, error){
                //console.log(request);
                //console.log(status);

                if( request.status == 404 ){
                    console.log("ajax 요청 주소가 올바르지 않습니다.");
              
                } else if( request.status == 500){
                    console.log("서버 내부 에러 발생");
                    console.log(request.responseText);
                }

            },

            complete : function(){ 
                // success , error가 수행된 후
                // ajax 요청/응답 처리가 완료 되었을 때 수행
                // (마지막에 무조건 수행)
                
                console.log("complete 수행");

            }
        });



        // **************************************************



    }else{
        alert("유효하지 않은 이메일 입니다.");
        updateCheckObj.email = false;
    }
});


// 수정 버튼 클릭 시 모든 값이 유효하지 않으면 submit 이벤트 제거
function memberUpdateValidate(){

    for( key in updateCheckObj ){

        if(!updateCheckObj[key]){
            // updateCheckObj에 저장된 value중 false가 있는 경우

            let message;

            switch(key){
            case "phone3" : message = "전화번호가 유효하지 않습니다.";   break;
            case "email"  : message = "이메일이 유효하지 않습니다.";  break;
            }

            alert(message);

            // 유효하지 않은 input요소로 포커스 이동
            document.getElementById(key).focus(); 

            return false; // submit 이벤트 제거

        }
    }
}



