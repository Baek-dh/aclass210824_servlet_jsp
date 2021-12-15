<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<title>게시글 등록</title>
<link rel="stylesheet" href="${contextPath}/resources/css/board-style.css">
		<jsp:include page="../common/header.jsp"></jsp:include>

		<div class="container my-5">

			<h3>게시글 등록</h3>
			<hr>
			<!-- 파일 업로드를 위한 라이브러리 cos.jar 라이브러리 다운로드(http://www.servlets.com/) -->
			
			<!-- 
				- enctype : form 태그 데이터가 서버로 제출 될 때 인코딩 되는 방법을 지정. (POST 방식일 때만 사용 가능)
				- application/x-www-form-urlencoded : 모든 문자를 서버로 전송하기 전에 인코딩 (form태그 기본값)
				- multipart/form-data : 모든 문자를 인코딩 하지 않음.(원본 데이터가 유지되어 이미지, 파일등을 서버로 전송 할 수 있음.) 
			-->
			<form action="insert" method="post" 
				  enctype="multipart/form-data" role="form" onsubmit="return boardValidate();">
				  
				<%-- 카테고리 --%>
				<div class="mb-2">
					<label class="input-group-addon mr-3 insert-label">카테고리</label> 
					<select	class="custom-select" id="categoryCode" name="categoryCode" style="width: 150px;">
						
						<c:forEach items="${category}"  var="c">
							<option value="${c.categoryCode}">${c.categoryName }</option>
						</c:forEach>
						
					</select>
				</div>
				
				
				<div class="form-inline mb-2">
					<label class="input-group-addon mr-3 insert-label">제목</label> 
					<input type="text" class="form-control" id="boardTitle" name="boardTitle" size="70">
				</div>

				<div class="form-inline mb-2">
					<label class="input-group-addon mr-3 insert-label">작성자</label>
					<h5 class="my-0" id="writer">${loginMember.memberName }</h5>
				</div>


				<div class="form-inline mb-2">
					<label class="input-group-addon mr-3 insert-label">작성일</label>
					<h5 class="my-0" id="today"></h5>
				</div>

				<hr>

				<div class="form-inline mb-2">
					<label class="input-group-addon mr-3 insert-label">썸네일</label>
					<div class="boardImg thubnail">
						<img>
					</div>
				</div>

				<div class="form-inline mb-2">
					<label class="input-group-addon mr-3 insert-label">업로드<br>이미지</label>
					<div class="mr-2 boardImg">
						<img>
					</div>

					<div class="mr-2 boardImg">
						<img>
					</div>

					<div class="mr-2 boardImg">
						<img>
					</div>
				</div>


				<!-- 파일 업로드 하는 부분 -->
				<div id="fileArea">
					<input type="file" name="img0" onchange="loadImg(this,0)"> 
					<input type="file" name="img1" onchange="loadImg(this,1)"> 
					<input type="file" name="img2" onchange="loadImg(this,2)"> 
					<input type="file" name="img3" onchange="loadImg(this,3)">
				</div>

				<div class="form-group">
					<div>
						<label for="content">내용</label>
					</div>
					<textarea class="form-control" id="boardContent" name="boardContent" rows="15" style="resize: none;"></textarea>
				</div>


				<hr class="mb-4">

				<div class="text-center">
					<button type="submit" class="btn btn-primary">등록</button>
					<button type="button" class="btn btn-primary">목록으로</button>
				</div>

			</form>
		</div>

		<jsp:include page="../common/footer.jsp"></jsp:include>
		
		<script src="${contextPath}/resources/js/board.js"></script>
		
		
		
</body>
</html>
