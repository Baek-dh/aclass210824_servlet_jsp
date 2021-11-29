<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 범위 결과</title>
</head>
<body>

	<pre>
		pageScope : EL에서 page 범위만을 접근할 때 사용
		requestScope : EL에서 request 범위만을 접근할 때 사용
		sessionScope : EL에서 session 범위만을 접근할 때 사용
		applicationScope : EL에서 application 범위만을 접근할 때 사용
	
	
		request - msg : ${requestScope.msg} 
		request - reqValue : ${requestScope.reqValue} 
		
		session - msg : ${sessionScope.msg}
		session - sessionValue : ${sessionScope.sessionValue }
		
		application - msg : ${applicationScope.msg }
		application - appValue : ${applicationScope.appValue }
		
		
		** scope 우선 순위 확인
		-> scope는 좁은 범위가 높은 우선순위를 가진다!
		
		1. page
		2. request
		3. session
		4. application
		
		msg : ${msg}
		
	
	</pre>







</body>
</html>