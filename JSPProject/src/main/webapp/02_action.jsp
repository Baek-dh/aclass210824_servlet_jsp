<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02. JSP Action 태그</title>
</head>
<body>
	
	<%-- header.jsp  include (파일 경로 작성) --%>
	<jsp:include page="/common/header.jsp"/>

	<h1>JSP Action Tag란?</h1>
	<pre>
		JSP Action Tag는 
		XML 기술을 이용하여
		기존의 JSP문법을 확장하는 메커니즘을 제공하는 태그로써,
		기본적으로 JSP에 내장되어 있다.
		
		JSP Action Tag는 기존 HTML이 아닌
		Java코드를 태그 형식으로 작성해둔 것으로
		브라우저가 아닌 Servlet Container(WAS == Tomcat)이 해석함.
		
	</pre>
	
	<h1>jsp:include 태그</h1>
	<pre>
		현재 jsp에 다른 jsp를 포함시키는 태그
		-> jsp의 모듈화가 가능해짐.
	</pre>


	<%-- footer include --%>
	<jsp:include page="common/footer.jsp"/>
	
	<script>
		$(document).ready(function(){
			alert("jQuery 사용 가능");
		});
	</script>



</body>
</html>