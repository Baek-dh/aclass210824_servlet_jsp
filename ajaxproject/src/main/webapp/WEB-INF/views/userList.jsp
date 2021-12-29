<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>사용자 목록</title>
</head>
<body>

	<h3>사용자 목록</h3>

	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>가입일</th>
			</tr>
		</thead>

		<tbody id="userListView">
			<tr>
				<td>0</td>
				<td>홍길동</td>
				<td>2021-12-29 14:33:20</td>
			</tr>
		</tbody>
	</table>



	<hr>

	<form name="insertForm">
		<h3>사용자 등록</h3>
		<input type="text" id="userName" name="userName">
		<button type="button" id="btn">사용자 등록 하기</button>
	</form>


	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>


	<script>
		func_selectUserList();
	
	
		// 사용자 등록
		$("#btn").on("click", function() {

			// serialize() : form태그 내부 input요소를 모두 쿼리스트링으로 반환
			const qs = $("form[name='insertForm']").serialize();
			// userName=김박사

			$.ajax({
				url : "user/insertUser.do",
				type : "post",
				data : qs,
				success : function() {
					console.log("성공");
					func_selectUserList();
					$("#userName").val("").focus();

				},
				error : function() {
					console.log("에러");
				}
			});
		});

		
		// 사용자 목록 조회
		function func_selectUserList() {

			$.ajax({
				url : "user/selectUserList.do",
				type : "get",
				dataType : "json",
				success : function(result) {
					console.log(result);
					
					let html = "";
					
					// List 형태인 result를 순서대로 반복 접근
					$.each(result, function(index,item){ 
						
						console.log(item.userNo);
						console.log(item.userName);
						console.log(item.enrollDate);
						
						html += '<tr>'
							+		'<td>'+item.userNo+'</td>'
							+		'<td>'+item.userName+'</td>'
							+		'<td>'+item.enrollDate+'</td>'
							+	'</tr>';
					});
					
					// tbody
					$("#userListView").html(html);
					
				},

				error : function() {
					console.log("에러");
				}
			});

		}
		
	</script>









</body>
</html>