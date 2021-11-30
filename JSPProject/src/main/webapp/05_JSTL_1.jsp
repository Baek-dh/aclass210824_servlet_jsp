<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>05. JSTL 개요</title>
</head>
<body>
	
	<h1>JSTL(Jsp Standard Tag Library)</h1>
	<pre>
		JSP에서 사용하는 커스텀 태그로
		
		JSP 자주 사용하거나 공통적으로 사용되는 코드를
		쉽게 사용하기 위해 태그화 하여 표준으로 제공함.
		(if, for, 변수 선언, 파싱 등)
	</pre>
	
	
	<h3>라이브러리 등록 방법</h3>
	<ol>
		<li> https://tomcat.apache.org/download-taglibs.cgi 접속 </li>
		<li> jar files -> impl, EL, Spec 다운로드 </li>
		<li> WEB-INF/lib 폴더에 추가</li>
	</ol>
	
	
	<hr>
	
	<h1>JSTL 사용을 위한 선언 방법</h1>
	<pre>
		JSTL을 사용하고자 하는 JSP가 있을 경우
		해당 JSP 최상단에 JSTL 라이브러리를 추가하는 지시자 taglib를 작성해야 한다.
		
		prefix : 접두사. 다른 태그와 구별할 수 있는 namespace
				(태그 앞에 붙는 태그명)
				
		url(Uniform Resource Locator) : 인터넷에서 특정 자원 위치 지정(주소)
		uri(Uniform Resource Identifier) : 네트워크 상에서 자원을 구별하는 식별자
											(자원을 구분하는 유일한 주소)
		
		-> uri에 작성하는 주소는 네트워크 상의 주소가 아닌
		   다운로드 받은 라이브러리 내부 구분 주소
	</pre>
	
	
	<hr>
	
	
	<h3> 1. 변수 선언 (  c:set 태그  )  </h3>
	<pre>
		- 변수를 선언하고 초기화를 진행하는 태그 (초기화 무조건 수행)
		- c:set 태그로 선언된 변수는 EL을 이용해서 출력할 수 있다
		
		특징 1 : 별도의 변수 타입 지정을 하지 않는다.
		특징 2 : 변수의 범위(scope)를 지정할 수 있다.
				 (page, request, session, application)
		
		var : 변수명
		value : 저장할 값
		scope : 변수 범위
	</pre>
	
	<% String str = "hello"; %>
	str : <%= str %>
	<br>
	<c:set var="str2" value="world"/>
	str2 : ${str2}
	
	<h4>scope 지정하기</h4>

	<c:set var="num1" value="100"/>	  <!-- scope 기본값은 page -->
	
	<c:set var="num2" value="200" scope="request"/>
	
	num1 + num2 = ${num1 + num2} <br>
	
	<!-- num1 : page,  num2 : request -->
	requestScope.num1 + requestScope.num2 = ${requestScope.num1 + requestScope.num2} <br>
	
	
	
	<hr>
	
	<h3>2. 변수 삭제(제거)  ( c:remove 태그 ) </h3>
	<pre>
		지정한 변수를 특정 scope 또는 모든 scope에서 제거
	
		ex) 
		게시글 작성 -> 오류 발생 -> 다시 작성페이지로 돌아옴 -> 이전 내용이 모두 삭제됨 ㅠㅠ
		
		이를 해결하기 위해 작성 완료 시 session에 글 내용을 임시 저장
		-> 정상적으로 글 등록이 완료되면 임시 저장된 내용을 삭제(c:remove 사용)
	</pre>
	
	
	1) session에 변수 선언 후 제거 확인 <br>
	<c:set var="num3" value="300" scope="session"/>
	num3 삭제 전 : ${sessionScope.num3} <br>
	
	<c:remove var="num3" scope="session"/> <!-- session에 있는 num3 제거 -->
	num3 삭제 후 : ${sessionScope.num3} <br>
	
	
	<br>
	2) 모든 scope에서 제거 확인 <br>
	<c:set var="num4" value="400"/> <!-- page -->
	<c:set var="num4" value="4000" scope="request"/> <!-- request -->
	<c:set var="num4" value="40000" scope="session"/> <!-- session -->
	<c:set var="num4" value="400000" scope="application"/> <!-- application -->
	
	삭제 전 :
	${num4} / ${requestScope.num4} / ${sessionScope.num4} / ${applicationScope.num4} <br>
	
	<c:remove var="num4"/> <!-- 모든 scope 삭제 -->
	삭제 후 :
	${num4} / ${requestScope.num4} / ${sessionScope.num4} / ${applicationScope.num4} <br>
	
	
	<hr>
	
	<h3> 3. 조건문 - if문  ( c:if 태그 )</h3>
	<pre>
		if문은 태그 형식으로 작성한 것.
		별도의 else 구문이 존재하지 않음!
		
		test 속성 : 조건을 작성하는 속성. EL 형식으로만 작성 가능.
	</pre>
	
	<c:set var="test1" value="10"/>
	<c:set var="test2" value="20"/>
	
	<!-- 조건을 만족하지 못하면 화면에 출력되지 않음 -->
	<c:if test="${ test1 > test2 }">
		test1이 더 큽니다.	
	</c:if>
	
	<!-- 별도의 else 구문이 없으므로 필요 시 반대 조건의 c:if를 작성해야함 -->
	<c:if test="${ test1 <= test2 }">
		test2가 더 크거나 같습니다.	
	</c:if>
	
	
	<hr>
	
	<h3>4. 조건문 - if ~ else if ~ else  (c:choose, c:when, c:otherwise)</h3>
	<pre>
		c:choose 태그 내부에
		c:when 태그를 이용하여 조건 작성 (if, else if)
		c:otherwise 태그를 이용해 조건을 만족하지 않는 경우 작성(else)
	</pre>
	
	<!-- c:choose 태그에는 아무런 속성도 없음 -->
	<!-- 주소창에 쿼리스트링을 추가하여 확인  -->
	<!-- http://localhost:8080/JSPProject/05_JSTL_1.jsp?aaa=5 -->
	<c:choose>
		<c:when test="${ param.aaa > 10 }">  <!-- if -->
			10보다 크다
		</c:when>
		
		<c:when test="${ param.aaa == 10 }">  <!-- else if  -->
			10과 같다
		</c:when>
		
		<c:otherwise>	<!-- else -->
			10보다 작다
		</c:otherwise>
	</c:choose>
	
	
	
	
	
	
	
	
</body>
</html>