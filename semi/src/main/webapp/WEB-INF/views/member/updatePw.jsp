<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<jsp:include page="../common/header.jsp"></jsp:include>
	
	<div class="container" id="content-main"  style="min-height:700px" >

		<div class="row my-5">
			<jsp:include page="sideMenu.jsp"></jsp:include>

			<div class="col-sm-8">
				<h3>비밀번호 변경</h3>
				<hr>
				<div class="bg-white rounded shadow-sm container p-3">
					<form method="POST" action="updatePw" onsubmit="return updatePwValidate();" class="form-horizontal" role="form">
						<!-- 아이디 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>아이디</h6>
							</div>
							<div class="col-md-6">
								<h5 id="id">${loginMember.memberId }</h5>
							</div>
						</div>

						<hr>
						<!-- 현재 비밀번호 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>현재 비밀번호</h6>
							</div>
							<div class="col-md-6">
								<input type="password" class="form-control" id="currentPw" name="currentPw">
							</div>
						</div>

						<!-- 새 비밀번호 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>새 비밀번호</h6>
							</div>
							<div class="col-md-6">
								<input type="password" class="form-control" id="newPw1" name="newPw1">
							</div>
						</div>

						<!-- 새 비밀번호 확인-->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>새 비밀번호 확인</h6>
							</div>
							<div class="col-md-6">
								<input type="password" class="form-control" id="newPw2" name="newPw2">
							</div>
						</div>
						
						<hr class="mb-4">
						<button class="btn btn-primary btn-lg btn-block" type="submit">변경하기</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>


	<script>
		// 비밀번호 유효성 검사
		function updatePwValidate(){
			
			// 새 비밀번호/확인에 작성된 값을 변수에 저장
			const newPw1 = document.getElementById("newPw1").value;
			const newPw2 = document.getElementById("newPw2").value;
			
			// 1) 새 비밀번호가 정규식에 맞지 않은 경우
			const regExp = /^[a-zA-Z\d\!\@\#\-\_]{6,20}$/;
			
			if( !regExp.test(newPw1)  ){
				alert("새 비밀번호가 유효하지 않습니다.");
				return false;
			}
			
			// 2) 새 비밀번호/확인 같지 않은 경우
			if(newPw1 != newPw2){
				alert("새 비밀번호가 일치하지 않습니다.");
				return false;
			}
			
			
			
		}
	</script>


</body>
</html>



