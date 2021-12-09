<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container" id="content-main" style="min-height:830px">

		<div class="row my-5">
			<jsp:include page="sideMenu.jsp"></jsp:include>

			<div class="col-sm-offset-2 col-sm-8">
				<h3>회원 탈퇴</h3>
				<div class="bg-white rounded shadow-sm container p-3">
					<form method="POST" action="secession" onsubmit="return secessionValidate();" 
						class="form-horizontal" role="form">
						<!-- 아이디 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>아이디</h6>
							</div>
							<div class="col-md-6">
								<h5 id="id">${loginMember.memberId }</h5>
							</div>
						</div>

						<!-- 비밀번호 -->
						<div class="row mb-3 form-row">
							<div class="col-md-3">
								<h6>비밀번호</h6>
							</div>
							<div class="col-md-6">
								<input type="password" class="form-control" id="currentPw"
									name="currentPw">
							</div>
						</div>

						<hr>
						<div class="panel panel-default">

							<div class="panel-body">
								<div class="form-group pull-left">
									<label class="control-label"> 회원 탈퇴 약관 </label>
									<div class="col-xs-12">
										<textarea class="form-control" readonly rows="10" cols="100">
제1조
이 약관은 샘플 약관입니다.

① 약관 내용 1

② 약관 내용 2

③ 약관 내용 3

④ 약관 내용 4


제2조
이 약관은 샘플 약관입니다.

① 약관 내용 1

② 약관 내용 2

③ 약관 내용 3

④ 약관 내용 4
</textarea>
									</div>
									<div class="checkbox pull-right">
										<div class="custom-checkbox">
											<div class="form-check">
												<input type="checkbox" name="agree" id="agree"
													class="form-check-input custom-control-input"> <br>
												<label class="form-check-label custom-control-label"
													for="agree">위 약관에 동의합니다.</label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<hr class="mb-4">
						<button class="btn btn-primary btn-lg btn-block" id="btn"
							type="submit">탈퇴</button>
					</form>
				</div>
			</div>
		</div>
	</div>
<jsp:include page="../common/footer.jsp"></jsp:include>

	<script>
		// 비밀번호 입력 + 약관 동의가 체크 되었을 때에만 탈퇴 진행
		function secessionValidate(){

			// 1) 비밀번호가 입력되어 있지 않으면 false 리턴
			const currentPw = document.getElementById("currentPw");
			
			if(currentPw.value.trim().length == 0){
				alert("비밀번호를 입력해주세요");
				currentPw.focus();
				return false;
			}
			
			// 2) 약관 동의가 체크 되어있지 않으면 false 리턴 
			if( !document.getElementById("agree").checked  ){
				alert("약관에 동의해주세요.");
				return false;
			}
			
			
			// 3) confirm을 이용하여 정말 탈퇴할 것인지 물어보기
			
			return confirm("정말로 탈퇴 하시겠습니까???");
			
			
			
		}
	</script>
	
	
	
	

</body>
</html>
