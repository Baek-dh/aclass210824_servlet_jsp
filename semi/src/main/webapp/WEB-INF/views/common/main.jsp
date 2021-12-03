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
	    <h1>Section Heading</h1>
	    <p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
	    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, suscipit, rerum quos facilis repellat architecto commodi officia atque nemo facere eum non illo voluptatem quae delectus odit vel itaque amet.</p>
	  </div>
	</section>
	
	<!-- footer include -->
	<jsp:include page="footer.jsp"/>
	
	
</body>
</html>