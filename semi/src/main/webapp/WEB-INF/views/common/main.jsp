<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<!-- header include -->
	<jsp:include page="header.jsp"/>
	<%-- include할 jsp 파일 경로 작성 --%>
	
	<!-- 메인 화면 이미지 -->
	<div class="py-5 bg-image-full" style="background-image: url('${contextPath}/resources/images/main/main_bg.jpg');">
	    <h1>Servlet/JSP를 이용한<br>Semi Project</h1>
	</div>
	
	<!-- 내용 작성 부분 -->
	<section class="py-5">
	  <div class="container">
	    <h1>${today}</h1>
	    <p class="lead">로그인한 회원 정보</p>
	    <p>
	    	아이디 : ${sessionScope.loginMember.memberId} <br>
	    	이름 : ${loginMember.memberName} <br>
	    	전화번호 : ${loginMember.memberPhone} <br>
	    	이메일 : ${loginMember.memberEmail} <br>
	    	주소 : ${loginMember.memberAddress} <br>
	    </p>
	  </div>
	</section>
	
	<section class="py-5">
	  <div class="container">
	    <h1>아이디가 일치하는 회원정보 조회(AJAX)</h1>
	    <p class="lead">
	    	검색할 아이디 : <input type="text" id="searchId">
	    	<button type="button" id="idSearchBtn">아이디 검색</button>
	    </p>
	    
	    <table>
	    	<tbody id="idSearchResult">
				<!-- 
				<tr>
					<td> user01이라는 회원이 존재하지 않습니다.</td>
				</tr>
				 -->
				
				<tr>
					<th>아이디</th>
					<td>user01</td>
				</tr>
				
				<tr>
					<th>이름</th>
					<td>유저일</td>
				</tr>
				
				<tr>
					<th>전화번호</th>
					<td>010-1234-1234</td>
				</tr>
				
				<tr>
					<th>이메일</th>
					<td>user01@kh.or.kr</td>
				</tr>
				
				<tr>
					<th>주소</th>
					<td>서울시 중구 ....</td>
				</tr>
				
				
				
	    	</tbody>
	    </table>
	    
	  
	  </div>
	</section>
	
	<!-- footer include -->
	<jsp:include page="footer.jsp"/>
	
	
</body>
</html>