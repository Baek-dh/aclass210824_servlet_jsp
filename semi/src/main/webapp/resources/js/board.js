(function() {
	// 오늘 날짜 출력 
	var today = new Date();
	var month = (today.getMonth() + 1);
	var date = today.getDate();

	var str = today.getFullYear() + "-"
			+ (month < 10 ? "0" + month : month) + "-"
			+ (date < 10 ? "0" + date : date);
	$("#today").html(str);
})();


// 유효성 검사 
function boardValidate() {
	if ($("#boardTitle").val().trim().length == 0) {
		alert("제목을 입력해 주세요.");
		$("#title").focus();
		return false;
	}

	if ($("#boardContent").val().trim().length == 0) {
		alert("내용을 입력해 주세요.");
		$("#content").focus();
		return false;
	}
}

// 이미지 영역을 클릭할 때 파일 첨부 창이 뜨도록 설정하는 함수
$(function() {
	$(".boardImg").on("click", function() {
		var index = $(".boardImg").index(this);
				// 현재 클릭된 요소가 .boardImg 중 몇 번째 인덱스인지 반환



		$("[type=file]").eq(index).click();
		// 타입이 file인 요소 중 몇번째 인덱스 요소를 선택하여 클릭해라
	});

});


// 각각의 영역에 파일을 첨부 했을 경우 미리 보기가 가능하도록 하는 함수
function loadImg(value, num) {
	// 매개변수 value == 클릭된 input 요소


	// 파일이 선택된 경우 true
	if (value.files && value.files[0]) {

		var reader = new FileReader();
		// 자바스크립트 FileReader
		// 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 읽을 파일을 가리키는 File 혹은 Blob객체를 이용해 파일의 내용을 읽고 사용자의 컴퓨터에 저장하는 것을 가능하게 해주는 객체


		// 선택된 파일 읽기 시작
		reader.readAsDataURL(value.files[0]);
		// FileReader.readAsDataURL()
		// 지정된 내용을 읽기 시작합니다. Blob완료되면 result속성 data:에 파일 데이터를 나타내는 URL이 포함 됩니다.



		// FileReader.onload
		// load 이벤트의 핸들러. 이 이벤트는 읽기 동작이 성공적으로 완료 되었을 때마다 발생합니다.

		// 다 읽은 경우
		reader.onload = function(e) {
			//console.log(e.target.result);
			// e.target.result
			// -> 파일 읽기 동작을 성공한 객체에(fileTag) 올라간 결과(이미지 또는 파일)

			$(".boardImg").eq(num).children("img").attr("src", e.target.result);
		}

	}
}



// 수정버튼 클릭 시 동작
function updateForm(){
	document.requestForm.action = "updateForm";
	document.requestForm.method = "POST";
	document.requestForm.submit();
}