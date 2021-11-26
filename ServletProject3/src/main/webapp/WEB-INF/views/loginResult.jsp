<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 결과</title>
</head>
<body>

	<h1>
		<%= request.getParameter("inputId")  %>이 로그인 되었습니다.
	</h1>

	<!-- HTML 주석 -->

	<%-- <%= "JSP 주석" %> --%>

	<%-- JSP는 Servlet(Java) 코드로 변형되어 컴파일된다
		이 때 JSP 주석은 해석 조차 되지 않는다.
		
		+ JSP에 작성된 Java 코드는 HTML 주석으로 처리할 수 없다.
		  반드시 JSP 주석 사용
	 --%>

	<%-- <%= request.getParameter("inputId")  %> --%>



	<%--
		  <% %>   : 스크립틀릿(Scriptlet) -> JSP에서 자바 코드 작성에 사용
		  <%= %>  : 표현식(Expression) -> 자바 코드 중 값이나 데이터를 HTML 코드 자체로 출력
	 --%>


	<%  if( request.getParameter("saveId") != null ) { %>

	<%-- 아이디 저장을 체크한 경우 --%>
	<h3>아이디 저장이 체크 되었습니다.</h3>

	<% } else  { %>

	<%-- 아이디 저장을 체크하지 않은 경우 --%>
	<h3>아이디 저장이 체크되지 않았습니다.</h3>

	<% } %>



	<% for(int i=1 ; i<=6 ; i++){ %>
		
		<h<%=i%>>반복문으로 출력중...</h<%=i%>>

	<% }%>





</body>
</html>