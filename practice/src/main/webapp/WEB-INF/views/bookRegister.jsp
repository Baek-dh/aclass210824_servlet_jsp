<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>책 등록</title>
	
	<script> 
		// head, body 관계 없이 모두 작성 가능
		
		function goRegister(){
			
			// 문서 내에서 name 속성이 bookRegForm인 form 요소 얻어오기
			var frm = document.bookRegForm;
			
			// action, method 속성 추가
			frm.action = "/practice/ncstest/bookRegister.do";
			frm.method = "POST";
			
			// form 태그 제출
			frm.submit();
			
		}
	
	</script>
</head>
<body>
	<h2>책 등록 하기</h2>
	
	<form name="bookRegForm">
		<ul>
			<li>
				제목<br>
				<input type="text" name="bookTitle" required>
			</li>
			
			<li>
				작가<br>
				<input type="text" name="bookAuthor" required>
			</li>
			
			<li>
				가격<br>
				<input type="number" name="bookPrice" required>
			</li>
			
			<li> 판매 <input type="radio" name="sale"  value="1" checked > </li>
			<li> 품절 <input type="radio" name="sale"  value="2" > </li>
			
			<li>
				<button type="button" onclick="goRegister();">확인</button>
			</li>
			
		</ul>
	</form>
	
</body>
</html>