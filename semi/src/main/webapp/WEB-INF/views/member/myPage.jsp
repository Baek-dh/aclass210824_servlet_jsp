<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<jsp:include page="../common/header.jsp"></jsp:include>
	
	
	<div class="container"  style="min-height:700px">
		
		<div class="row my-5">
		
			<jsp:include page="sideMenu.jsp"></jsp:include>		
				
			<div class="col-sm-8">
				<h3>My Page</h3>
				<hr>
				<div class="bg-white rounded shadow-sm container p-3">
					<form method="POST" action="update" onsubmit="return memberUpdateValidate();" class="form-horizontal" role="form">
						<!-- 아이디 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>아이디</h6>
							</div>
							<div class="col-md-6">
								<h5 id="id"> ${sessionScope.loginMember.memberId } </h5>
							</div>
						</div>
	
						<!-- 이름 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>이름</h6>
							</div>
							<div class="col-md-6">
								<h5 id="name">${loginMember.memberName}</h5>
							</div>
						</div>
	
						<!-- 전화번호 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<label for="phone1">전화번호</label>
							</div>
							<!-- 전화번호1 -->
							<div class="col-md-3">
								<select class="custom-select" id="phone1" name="phone">
									<option>010</option>
									<option>011</option>
									<option>016</option>
									<option>017</option>
									<option>019</option>
								</select>
							</div>
							
	
							<!-- 전화번호2 -->
							<div class="col-md-3">
								<input type="number" class="form-control phone" id="phone2" name="phone" value=" ">
							</div>
	
							<!-- 전화번호3 -->
							<div class="col-md-3">
								<input type="number" class="form-control phone" id="phone3" name="phone" value=" ">
							</div>
						</div>
	
						<!-- 이메일 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<label for="memberEmail">Email</label>
							</div>
							<div class="col-md-6">
								<input type="email" class="form-control" id="email" name="email" value="${loginMember.memberEmail}">
							</div>
						</div>
						<br>
	
						<!-- 주소 -->
						<!-- 오픈소스 도로명 주소 API -->
						<!-- https://www.poesis.org/postcodify/ -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<label for="postcodify_search_button">우편번호</label>
							</div>
							<div class="col-md-3">
								<input type="text" name="address" class="form-control postcodify_postcode5" value=" ">
							</div>
							<div class="col-md-3">
								<button type="button" class="btn btn-primary" id="postcodify_search_button">검색</button>
							</div>
						</div>
	
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<label for="address1">도로명 주소</label>
							</div>
							<div class="col-md-9">
								<input type="text" class="form-control postcodify_address" name="address" id="address1"  value=" ">
							</div>
						</div>
	
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<label for="address2">상세주소</label>
							</div>
							<div class="col-md-9">
								<input type="text" class="form-control postcodify_details" name="address" id="address2"  value=" ">
							</div>
						</div>
	
						<hr class="mb-4">
						<button class="btn btn-primary btn-lg btn-block" type="submit">수정</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<br><br>
	
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
	
	
	
	
</body>
</html>
