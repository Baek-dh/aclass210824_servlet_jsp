<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<header>
		<h1>Header 입니다</h1>
		<%--  request.getContextPath() : 최상위 주소 반환 --%>
		<a href="<%= request.getContextPath() %>">메인 페이지로 이동</a>
	</header>
	<hr>	