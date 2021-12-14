package edu.kh.semi.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.semi.board.model.service.BoardService;
import edu.kh.semi.board.model.vo.Board;
import edu.kh.semi.board.model.vo.Pagination;

//   /semi/board/list
//   /semi/board/update
//   /semi/board/insert
//   /semi/board/select


// Front Controller 패턴
// - 클라이언트의 요청을 한 곳으로 집중시켜 개발 및 유지보수의 효율을 증가시킨 패턴
// - 요청 별 Servlet 클래스를 작성하지 않아 클래스 관리에 효율적.

// /board/ 로 시작하는 모든 요청을 처리하는 컨트롤러
@WebServlet("/board/*")
public class BoardController extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 데이터 전달 방식 저장용 변수
		String method = req.getMethod();
		
		// 요청 주소 뒷부분을 잘라내어 구분 방법 만들기
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		
		String command = uri.substring(  (contextPath + "/board/").length()  );
		// -> 요청 주소에서 /semi/board/ 의 길이만큼 잘라낸 후 
		//    나머지 문자열을 command 변수에 저장
				
		
		String path = null;
		RequestDispatcher dispatcher = null;
		String message = null;
		
		
		try {
			// command에 저장된 값에 따라 서로 다른 동작을 수행
			
			
			BoardService service = new BoardService();
			
			// ***(중요)***
			// 게시판 관련 기능 수행 시 "현재 페이지"는 여러 의미로 많이 사용되기 때문에
			// 미리 얻어와서 준비 시켜두는 것이 좋다
			int cp = req.getParameter("cp") == null ? 1 : Integer.parseInt(req.getParameter("cp"));              
			// (조건식) ? 참 인 경우 : 거짓인 경우
			
			
			// 게시글 목록 조회 Controller
			if(command.equals("list")) {
				
				// 목록 조회 + 페이징 처리
				
				// 목록 조회 순서
				// 1. DB에 저장된 조회 가능한 게시글 전체 수 카운트
				// 2. 전체 게시글 수 + 현재 페이지를 이용해서
				//	  페이징 처리에 필요 숫자들을 만들어냄
				
				Pagination pagination = service.getPagination(cp);
				
				// 3. 조회 되어지는 게시글의 번호를 계산하여 DB에서 조회해옴
				List<Board> boardList = service.selectBoardList(pagination);
				
				// 4. 화면 출력
				
				req.setAttribute("pagination", pagination);
				req.setAttribute("boardList", boardList);
				
				// 요청 위임
				path = "/WEB-INF/views/board/boardList.jsp";
				dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
				
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		// POST 방식으로 요청이 오게 되면 이를 doGet() 메소드로 전달
		// -> GET/POST 모두 doGet()에서 처리
	}
	
	
	
	
	
	
	
	
	
	
}
