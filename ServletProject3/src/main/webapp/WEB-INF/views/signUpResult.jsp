<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<% // 스크립틀릿 : JSP에서 자바 코드 작성하는 영역
	String msg = (String)request.getAttribute("msg");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 결과 화면</title>
</head>
<body>
	<h1><%= msg %></h1>
</body>
</html>