<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 가입 결과</title>

	<style>
		.red{ color : red; }
	</style>
	
</head>
<body>
	
	<h1 class="red"> <%= request.getParameter("memberName") %>님 가입을 환영합니다.</h1>
	
	<p>
		ID : <%=request.getParameter("memberId") %>  <br>
		PW : <%=request.getParameter("memberPw") %>  <br>
		Email : <%=request.getParameter("memberEmail") %>  <br>
		
	</p>
	
	
	

</body>
</html>