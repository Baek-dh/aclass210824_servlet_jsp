<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<title>게시판</title>
<link rel="stylesheet" href="${contextPath}/resources/css/board-style.css">

	<jsp:include page="../common/header.jsp"></jsp:include>
	
	<div class="container my-5">
		<h1>게시판</h1>
		<div class="list-wrapper">
			<table class="table table-hover table-striped my-5" id="list-table">
				<thead>
					<tr>
						<th>글번호</th>
						<th>카테고리</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>작성일</th>
					</tr>
				</thead>


				<%-- 게시글 목록 출력 --%>
				<tbody>
				
					<c:choose>
						
						<c:when test="${empty boardList}"> 
							<%-- 조회된 게시글 목록이 없을 때 --%>
							<tr>
								<td colspan="6">게시글이 존재하지 않습니다.</td>
							</tr>
						</c:when>
						
						<c:otherwise>
							<c:forEach items="${boardList}" var="board">
							
								<%-- 조회된 게시글 목록이 있을 때 --%>
								<tr>
									<%-- 글 번호 --%>
									<td>${board.boardNo}</td>
									
									<%-- 카테고리명 --%>
									<td>${board.categoryName}</td>
									
									<%-- 글 제목 --%>
									<td class="boardTitle">
										<a href="#">
										
											<c:choose>
												<c:when test="${board.boardStatusName eq '블라인드' }">                                                          
													<strong style="color:red; font-size:11px">
														관리자에 의해 블라인드 처리된 게시글입니다.
													</strong>
												</c:when>
												<c:otherwise> 
													${board.boardTitle}
												</c:otherwise>
											</c:choose>
										
										</a>
									</td>
									
									
									<%-- 작성자 --%>
									<td>${board.memberName }</td>
								
									<%-- 조회수 --%>
									<td>${board.readCount}</td>
									
									<%-- 작성일 --%>
									<td>${board.createDate }</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					
					</c:choose>
				</tbody>
			</table>
		</div>


		<%-- 로그인이 되어있는 경우에만 글쓰기 버튼 노출 --%>
		<c:if test="${!empty loginMember }">
			<button type="button" class="btn btn-primary float-right" id="insertBtn" onclick="location.href = '${contextPath}/board/insertForm';">글쓰기</button>
		</c:if>


		<%---------------------- Pagination ----------------------%>
		
		

		<div class="my-5">
			<ul class="pagination">
				
				
				<c:if test="${pagination.startPage != 1 }">
					<li><a class="page-link" href="list?cp=1">&lt;&lt;</a></li>
					<li><a class="page-link" href="list?cp=${pagination.prevPage}">&lt;</a></li>
				</c:if>
				
				<%-- 페이지네이션 번호 목록 --%>
				<c:forEach begin="${pagination.startPage}" end="${pagination.endPage}" step="1"  var="i">
					<c:choose>
						<c:when test="${i == pagination.currentPage}">
							<li><a class="page-link" style="color:black; font-weight:bold;">${i}</a></li>   
						</c:when>
						
						<c:otherwise>
							<li><a class="page-link" href="list?cp=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<c:if test="${pagination.endPage != pagination.maxPage }">
					<li><a class="page-link" href="list?cp=${pagination.nextPage}">&gt;</a></li>
					<li><a class="page-link" href="list?cp=${pagination.maxPage }">&gt;&gt;</a></li>
				</c:if>
			</ul>
		</div>


		<!-- 검색창 -->
		<div class="my-5">

			<form action="list" method="GET" class="text-center" id="searchForm">

				<select name="sk" class="form-control" style="width: 100px; display: inline-block;">
					<option value="title">글제목</option>
					<option value="content">내용</option>
					<option value="titcont">제목+내용</option>
					<option value="writer">작성자</option>
				</select> <input type="text" name="sv" class="form-control" style="width: 25%; display: inline-block;">
				<button class="form-control btn btn-primary" style="width: 100px; display: inline-block;">검색</button>
			</form>

		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>


</body>
</html>
