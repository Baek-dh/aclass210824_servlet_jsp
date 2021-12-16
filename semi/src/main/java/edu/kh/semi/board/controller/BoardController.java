package edu.kh.semi.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import edu.kh.semi.board.model.service.BoardService;
import edu.kh.semi.board.model.vo.Board;
import edu.kh.semi.board.model.vo.BoardImage;
import edu.kh.semi.board.model.vo.Category;
import edu.kh.semi.board.model.vo.Pagination;
import edu.kh.semi.common.MyRenamePolicy;
import edu.kh.semi.member.model.vo.Member;

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
			
			
			// 게시글 상세 조회
			else if(command.equals("view")) {
				
				// 상세 조회 시 쿼리스트링에 작성된 값 : no, cp
				
				// 쿼리스트링에 작성된 게시글 번호 얻어오기
				int boardNo = Integer.parseInt( req.getParameter("no") );  
				
				
				// 로그인한 회원의 번호를 조회
				Member loginMember = (Member)req.getSession().getAttribute("loginMember");
				
				int memberNo = 0;
				if(loginMember != null)  memberNo = loginMember.getMemberNo();
				
				
				// 게시글 상세 조회 서비스 호출 후 결과 반환 받기
				Board board = service.selectBoard(boardNo, memberNo);
				
				
				if(board != null) { // 조회 성공(게시글 있음)
					
					req.setAttribute("board", board);
					path = "/WEB-INF/views/board/boardView.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
							
				}else { // 조회 실패(게시글 없음)
					
					// 게시글 목록 1페이지로 이동 -> list 재요청 (redirect)
					// "게시글이 존재하지 않습니다"를 경고창에 출력
					
					
					req.getSession().setAttribute("message", "게시글이 존재하지 않습니다.");
					
					// 절대 경로 : /semi/board/list
					//resp.sendRedirect(req.getContextPath()+"/board/list"); 
					
					// 상대 경로 : /semi/board/view -> /semi/board/list
					resp.sendRedirect("list");
					
				}
				
			}
			
			
			
			// 게시글 등록
			else if(command.equals("insert")) {
				
				// GET 방식 요청 -> 게시글 등록 화면 전환
				if(method.equals("GET")) {
					
					// 카테고리 테이블 내용 조회하기
					List<Category> category = service.selectCategory();
					
					req.setAttribute("category", category);
					
					path = "/WEB-INF/views/board/boardInsert.jsp";
					dispatcher = req.getRequestDispatcher(path);
					dispatcher.forward(req, resp);
					
				}
				
				// POST 방식 요청 -> 게시글 등록 서비스 수행
				else {
					
					// ***** 주의 *****
					// encType="multipart/form-data" 형식의 form태그에서
					// 전달된 파라미터는
					// HttpServletRequest 객체로는 다룰 수 없다!
					// --> cos.jar에서 제공하는 MultipartRequest를 사용해야 한다.
					//System.out.println(  req.getParameter("boardTitle") );
					//System.out.println(  req.getParameter("boardContent") );
					
					
					// ***** MultipartRequest 사용을 위한 준비 *****
					
					// 1.업로드 되는 파일 전체 합의 최대 용량 지정(byte 단위)
					int maxSize = 1024 * 1024 * 100; // 100MB
					
					// 2.업로드 되는 파일을 서버 컴퓨터 어디에 저장할지 경로 지정
					// -> 특정 폴더의 컴퓨터 내부 절대 경로
					
					HttpSession session = req.getSession();
					
					// 프로젝트의 webapp폴더의 컴퓨상 실제 절대 경로
					String root = session.getServletContext().getRealPath("/");
					
					// 나머지 파일 경로 (DB에 저장되어 주소 경로로 사용할 예정)
					String filePath = "/resources/images/board/";
					
					// 실제 경로
					String realPath = root + filePath;
					
					// 3. 저장되는 파일의 이름을 변경
					//   -> 중복되는 파일명을 방지하기 위해서
					//   --> MyRenamePolicy 클래스
					
					
					//************************************************************
					// MultipartRequest 객체 생성
					
					MultipartRequest mReq 
						= new MultipartRequest(req, realPath, maxSize, "UTF-8", new MyRenamePolicy());                                               
					
					// !!!!!!!!!!!! MultipartRequest 객체가 성공적으로 생성된 경우
					//				지정된 파일 경로에 파일이 바로 업로드 된다!!!!!!!!!!!!!
					
					// 만약 객체는 생성되었지만 파일 저장이 안되는 경우
					// Servers탭 -> 사용하는 서버 더블클릭 -> Overview
					// -> Server Options 메뉴 -> Serve Modules Without Publishing 체크!
					
					//************************************************************
					
					// MultipartRequest 다루기
					
					// 1) 텍스트 형식의 파라미터
					//System.out.println(mReq.getParameter("boardTitle"));
					
					String boardTitle = mReq.getParameter("boardTitle");
					String boardContent = mReq.getParameter("boardContent");
					int categoryCode = Integer.parseInt( mReq.getParameter("categoryCode") );
					
					// 로그인한 회원 번호
					int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
					
					Board board = new Board();
					board.setBoardTitle(boardTitle);
					board.setBoardContent(boardContent);
					board.setCategoryCode(categoryCode);
					board.setMemberNo(memberNo);
					
					
					// 2) 파일 형식의 파라미터 
					
					
					Enumeration<String> files = mReq.getFileNames();
					// Enumeration == iterator (ResultSet과 비슷)
					// -> 폼에서 전달된 모든 input type="file" 요소의 name 속성을 반환
					//   -> 파일이 업로드되지 않아도 모든 요소를 얻어옴!
					
					
					// 업로된 이미지 정보를 담을 List 생성
					List<BoardImage> imgList = new ArrayList<BoardImage>();
					
					while(files.hasMoreElements()) {
						// 다음 요소(name)가 있으면 true
						
						String name = files.nextElement(); // 다음 요소값(name) 얻어오기
						
						System.out.println("얻어온 name : " + name);
						
						System.out.println("변환된 파일명 : " + mReq.getFilesystemName(name));
						System.out.println("원본 파일명 : " + mReq.getOriginalFileName(name));
						
						
						// 현재 요소에 업로드된 파일이 있을 경우
						if(mReq.getFilesystemName(name) != null) {
							
							BoardImage temp = new BoardImage();
							
							temp.setImgName(mReq.getFilesystemName(name));
							temp.setImgOriginal(mReq.getOriginalFileName(name));
							temp.setImgPath(filePath);
							
							// name(img0~img3)에서 숫자를 제외한 "img" 문자열을 제거
							temp.setImgLevel(  Integer.parseInt( name.replace("img", "")  ) );
							
							
							// imgList에 추가
							imgList.add(temp);
						
						} // end if
						
					} // end while
					
					
					// board, imgList를 DB에 삽입하는 서비스 호출 후 결과 반환
					int result = service.insertBoard(board, imgList);
					
					if(result > 0) { // 성공
						message = "게시글이 등록 되었습니다.";
						
						// 상세 조회 redirect 주소
						path = "view?no="+result+"&cp=1";
					}else { // 실패
						
						message = "게시글 등록 중 문제가 발생했습니다.";
						
						// 다시 게시글 작성 화면 redirect 주소
						path = "insert";
						
					}
					
					session.setAttribute("message", message);
					resp.sendRedirect(path);
				}
				
				
			}
			
			
			// 게시글 수정 화면 전환
			else if(command.equals("updateForm")) {
				// POST 방식 요청 -> cp, no hidden으로 담겨있음
				
				int boardNo = Integer.parseInt(req.getParameter("no"));
				
				// 게시글 등록 화면에 이전 내용이 작성되어 있는 형태
				// --> 게시글 수정 화면 == 상세조회 + 게시글 삽입
				
				// 1. 게시글 조회
				Board board = service.updateView(boardNo);
				
				// 2. 카테고리 목록 조회
				List<Category> category = service.selectCategory();
				
				
				req.setAttribute("board", board);
				req.setAttribute("category", category);
				
				path = "/WEB-INF/views/board/boardUpdate.jsp";
				dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
				
			}
			
			
			
			// 게시글 수정
			else if(command.equals("update")) {
				
				// ***** MultipartRequest 사용을 위한 준비 *****
				
				// 1.업로드 되는 파일 전체 합의 최대 용량 지정(byte 단위)
				int maxSize = 1024 * 1024 * 100; // 100MB
				
				// 2.업로드 되는 파일을 서버 컴퓨터 어디에 저장할지 경로 지정
				// -> 특정 폴더의 컴퓨터 내부 절대 경로
				
				HttpSession session = req.getSession();
				
				// 프로젝트의 webapp폴더의 컴퓨상 실제 절대 경로
				String root = session.getServletContext().getRealPath("/");
				
				// 나머지 파일 경로 (DB에 저장되어 주소 경로로 사용할 예정)
				String filePath = "/resources/images/board/";
				
				// 실제 경로
				String realPath = root + filePath;
				
				// 3. 저장되는 파일의 이름을 변경
				//   -> 중복되는 파일명을 방지하기 위해서
				//   --> MyRenamePolicy 클래스
				
				
				// MultipartRequest 객체 생성
				
				MultipartRequest mReq 
					= new MultipartRequest(req, realPath, maxSize, "UTF-8", new MyRenamePolicy());
				
				
				// 1) 텍스트 형식의 파라미터
				//System.out.println(mReq.getParameter("boardTitle"));
				
				String boardTitle = mReq.getParameter("boardTitle");
				String boardContent = mReq.getParameter("boardContent");
				int categoryCode = Integer.parseInt( mReq.getParameter("categoryCode") );
				
				// 수정할 게시글 번호 얻어오기 (insert와의 차이점)
				int boardNo = Integer.parseInt( mReq.getParameter("no"));
				
				// 로그인한 회원 번호
				int memberNo = ((Member)session.getAttribute("loginMember")).getMemberNo();
				
				Board board = new Board();
				board.setBoardTitle(boardTitle);
				board.setBoardContent(boardContent);
				board.setCategoryCode(categoryCode);
				board.setMemberNo(memberNo);
				board.setBoardNo(boardNo);
				
				
				// 2) 파일 형식의 파라미터
				
				Enumeration<String> files = mReq.getFileNames();
				// -> 요청 시 전달 받은 모든 input type="file" 요소의 name 속성 값
				
				
				// 이미지 정보 저장용 List
				List<BoardImage> imgList = new ArrayList<BoardImage>();
				
				while(files.hasMoreElements()) {
					// files.hasMoreElements() : 다음 가져올 name 속성 값이 있다면 true
					 
					String name = files.nextElement(); 
					// files.nextElement() : 다음 name 속성 값을 얻어옴
					// -> img0 ,img1, img2, img3
					
					
					// mReq.getOriginalFileName(name) 
					// -> 매개변수 name과   name 속성 값이 같은 input type="file" 요소에
					//    업로드된 파일 원본명
					// -> 파일이 있으면 이름, 없으면 null 반환
					
					// mReq.getFilesystemName(name) 
					// -> 매개변수 name과   name 속성 값이 같은 input type="file" 요소에
					//    업로드된 파일의 변경된 이름.
					// -> 파일이 있으면 이름, 없으면 null 반환
					
					if(mReq.getFilesystemName(name) != null) { // 업로드된 파일이 있으면
						
						// 이미지 파일 정보 저장용 객체 BoardImage 객체 생성
						BoardImage temp = new BoardImage();
						
						temp.setImgPath(filePath); // images/board 폴더 경로
						temp.setImgName(mReq.getFilesystemName(name)); // 변경된 파일명
						temp.setImgOriginal(mReq.getOriginalFileName(name)); // 원본 파일명
						temp.setImgLevel(  Integer.parseInt( name.replace("img", "")  )  );
						
						temp.setBoardNo(boardNo);
						
						imgList.add(temp);
						
					} // end if
					
				} // end while
				
				
				
				// 게시글 수정 서비스 호출 후 결과 반환 받기
				int result = service.updateBoard(board, imgList);
				
				
				if(result > 0) { // 수정 성공
					message = "게시글이 수정되었습니다.";
					path = "view?no=" + boardNo + "&cp=" + mReq.getParameter("cp");
							
				}else { // 수정 실패
					
					message = "게시글 수정 실패";
					path = req.getHeader("referer") + "?no=" + boardNo;
					// referer -> 현재 요청 전 주소  (이전 페이지 요청)
					
				}
				
				session.setAttribute("message", message);
				resp.sendRedirect(path);
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
