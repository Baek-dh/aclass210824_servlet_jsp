<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- 절대 경로 --%>
<%-- <jsp:include page="/WEB-INF/views/common/header.jsp"/> --%>

<%-- 상대 경로 --%>
<jsp:include page="../common/header.jsp"/>


	<div class="container-xl">

		<div class="py-5 text-center">
			<h2>회원 가입</h2>
		</div>

		<div class="row">
			<div class="col-md-8 offset-md-2">

				<%-- GET/POST 전달 방식에 차이를 두어 주소 중복 사용 --%>
				<form method="POST" action="signup" class="needs-validation" name="signUpForm" onsubmit="return validate();">
					<%-- action="${contextPath}/member/signup" --%>

					<!-- 아이디 -->
					<div class="row mb-5 form-row">
						<div class="col-md-3">
							<label for="id"><span class="required"></span> 아이디</label>
						</div>
						<div class="col-sm-6">
							<input type="text" class="form-control" name="id" id="id" maxlength="12" placeholder="아이디를 입력하세요" autocomplete="off" required>
							<!-- required : 필수 입력 항목으로 지정 -->
							<!-- autocomplete="off" : input 태그 자동완성 기능을 끔 -->

							<!-- 팝업창 중복체크 여부 판단을 위한 hidden 타입 요소 -->
							<input type="hidden" name="idDup" id="idDup" value="false">
						</div>

						<!-- 아이디 중복 검사 버튼 -->
						<div class="col-sm-3">
							<button type="button" class="btn btn-primary" id="idDupCheck">중복검사</button>
						</div>
	
						<!-- 아이디 유효성 검사 결과 출력 -->
						<div class="col-md-6 offset-md-3">
							<span id="checkId" class="validity-msg"></span>
						</div>
					</div>


					<!-- 비밀번호 -->
					<div class="row mb-3 form-row">
						<div class="col-md-3">
							<label for="pwd1"><span class="required"></span> 비밀번호</label>
						</div>
						<div class="col-md-6">
							<input type="password" class="form-control" id="pwd1" name="pwd1" maxlength="12" placeholder="비밀번호를 입력하세요" required>
						</div>
						
						<!-- 비밀번호 유효성 검사 결과 출력 -->	
						<div class="col-md-6 offset-md-3">
							<span id="checkPwd1" class="validity-msg"></span>
						</div>
					</div>

					<!-- 비밀번호 확인 -->
					<div class="row mb-3 form-row">
						<div class="col-md-3">
							<label for="pwd2"><span class="required"></span> 비밀번호 확인</label>
						</div>
						<div class="col-md-6">
							<input type="password" class="form-control" id="pwd2" maxlength="12" placeholder="비밀번호 확인" required>
						</div>

						<!-- 비밀번호 확인 유효성 검사 결과 출력 -->	
						<div class="col-md-6 offset-md-3">
							<span id="checkPwd2" class="validity-msg"></span>
						</div>
					</div>
					<br>

					<!-- 이름 -->
					<div class="row mb-3 form-row">
						<div class="col-md-3">
							<label for="name"><span class="required"></span> 이름</label>
						</div>
						<div class="col-md-6">
							<input type="text" class="form-control" id="name" name="name" required>
						</div>

						<!-- 이름 유효성 검사 결과 출력 -->	
						<div class="col-md-6 offset-md-3">
							<span id="checkName" class="validity-msg"></span>
						</div>
					</div>

					<!-- 전화번호 -->
					<div class="row mb-3 form-row">
						<div class="col-md-3">
							<label for="phone1"><span class="required"></span> 전화번호</label>
						</div>
						<!-- 전화번호1 -->
						<div class="col-md-3">
							<select class="custom-select" id="phone1" name="phone" required>
								<option>010</option>
								<option>011</option>
								<option>016</option>
								<option>017</option>
								<option>019</option>
							</select>
						</div>

						<!-- number타입의 input태그에는 maxlength를 설정할 수 없음 -->
						<!-- 전화번호2 -->
						<div class="col-md-3">
							<input type="number" class="form-control phone" id="phone2" name="phone" required>
						</div>

						<!-- 전화번호3 -->
						<div class="col-md-3">
							<input type="number" class="form-control phone" id="phone3" name="phone" required>
						</div>

						<!-- 전화번호 유효성 검사 결과 출력 -->	
						<div class="col-md-6 offset-md-3">
							<span id="checkPhone" class="validity-msg"></span>
						</div>
					</div>

					<!-- 이메일 -->
					<div class="row mb-3 form-row">
						<div class="col-md-3">
							<label for="email"><span class="required"></span> Email</label>
						</div>
						<div class="col-md-6">
							<input type="email" class="form-control" id="email" name="email" autocomplete="off" required>
						</div>

						<!-- 이메일 유효성 검사 결과 출력 -->	
						<div class="col-md-6 offset-md-3">
							<span id="checkEmail" class="validity-msg"></span>
						</div>
					</div>
					<br>

					<!-- 주소 -->
					<!-- 오픈소스 도로명 주소 API 사용 -->
					<!-- https://www.poesis.org/postcodify/ -->
					<div class="row mb-3 form-row">
						<div class="col-md-3">
							<label for="postcodify_search_button">우편번호</label>
						</div>
						<div class="col-sm-3">
							<input type="text" name="address" class="form-control postcodify_postcode5">
						</div>
						<div class="col-sm-3">
							<button type="button" class="btn btn-primary" id="postcodify_search_button">검색</button>
						</div>
					</div>

					<div class="row mb-3 form-row">
						<div class="col-md-3">
							<label for="address1">도로명 주소</label>
						</div>
						<div class="col-md-9">
							<input type="text" class="form-control postcodify_address" name="address" id="address1">
						</div>
					</div>

					<div class="row mb-3 form-row">
						<div class="col-md-3">
							<label for="address2">상세주소</label>
						</div>
						<div class="col-md-9">
							<input type="text" class="form-control postcodify_details" name="address" id="address2">
						</div>
					</div>


					<hr class="mb-4">
					<button class="btn btn-primary btn-lg btn-block" type="submit">가입하기</button>
				</form>
			</div>
		</div>
		<br>
		<br>

		
	</div>
	
	
	<jsp:include page="../common/footer.jsp"></jsp:include>
	
	<!-- 오픈소스 도로명 주소 검색 API -->
	<!-- https://www.poesis.org/postcodify/ -->
	<!-- postcodify 라이브러리를 CDN 방식으로 추가. -->
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
	
	<script>
		// 검색 단추를 누르면 팝업 레이어가 열리도록 설정한다.
		$(function () {
		    $("#postcodify_search_button").postcodifyPopUp();
		});
	</script>
	
	
	<script src="${contextPath}/resources/js/member.js"></script>
	
	
	
	
	
</body>
</html>
