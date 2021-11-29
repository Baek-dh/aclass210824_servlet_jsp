<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- 페이지 지시자 --%>
<%-- <%@ page import="edu.kh.jsp.model.vo.Person"%>   --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL Result</title>
</head>
<body>

	<h3>파라미터 얻어오기</h3>
	<pre>
		\${param.name속성값} : 요청 객체(request)에 담겨 있는 파라미터를 얻어와 출력
	</pre>
	
	
	1) JSP 표현식 : 
	<%= request.getParameter("inputName") %> /
	<%= request.getParameter("inputAge") %>  /
	<%= request.getParameter("inputAddr") %>
	 <br>
	
	2) EL : ${param.inputName} / ${param.inputAge} / ${param.inputAddr}


	<hr>
	
	<h3>속성(Attribute) 얻어오기</h3>
	<pre>
		Servlet에서 추가된 속성을 표현할 경우
		기존 JSP 방식은 import, 다운 캐스팅, getter를 이용한 출력 등
		여러 가지 코드를 작성해야만 했다.
		
		하지만 EL은 이러한 귀찮은 요소들을 모두 개선
		import, 다운 캐스팅, getter 작성 없이
		오로지 \${ 속성Key },  \${속성key.필드명 } 으로 출력 가능
		
		단, toString(), getter가 작성되어 있어야지만 출력이 가능하다.
		
		
	</pre>
	
	
	<%-- 
	1) JSP 표현식 <br>
	<% Person person = (Person)request.getAttribute("person"); %>
	<%= person.toString() %> <br>
	이름 : <%= person.getName() %> <br>
	나이 : <%= person.getAge() %> <br>
	주소 : <%= person.getAddr() %> <br> 
	--%>
	
	<br>
	2) EL <br>
	${person} <br>
	이름 : ${person.name } <br>
	나이 : ${person.age } <br>
	주소 : ${person.addr } <br>
	

	<hr>
	
	<h3>null 처리 방법</h3>
	<pre>
		EL은 null 값을 출력 해야되는 경우에 대신 ""(빈문자열)을 출력한다.
		
		+ EL은 NullPointerException 발생 시에도 ""(빈문자열)을 출력한다.
		
		+ EL은 null과 비어있다(isEmpty()) 구분이 없음. 둘다 비어있다로 인식
	</pre>


	1) JSP <br>
	<% String str = request.getParameter("str"); %>
	str : <%= str %> <br>
	<%-- <%= str.length() %>  --%> <!-- NullPointerException -->
	
	조건문 결과 :   <%= str == null %> <br>
	
	<br><br>
	
	2) EL <br>
	str : ${param.str} <br>
	길이 : ${param.str.length } <br>
	
	조건문 결과 : ${empty param.str} <br>
	조건문 결과 : ${!empty param.str}

	<br><br>
	* 추가 확인	 <br>
	<% List<String> list = new ArrayList<String>(); %>
	1) jsp로 List가 비어있는지 확인 : <%= list.isEmpty() %> <br>
	2) EL 로 List가 비어있는지 확인 : ${empty list} <br>


	<hr>
	
	request 확인 : ${requestScope.reqValue }   <br>
	session 확인 : ${sessionScope.sessionValue }  <br>
	application 확인 : ${applicationScope.appValue }




</body>
</html>





