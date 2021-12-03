package edu.kh.practice.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.practice.model.Book;
import edu.kh.practice.model.BookDAO;

@WebServlet("/ncstest/bookRegister.do")
public class BookRegisterCtrl extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req, resp);
	}
	

	// GET/POST 전달 방식 관계 없이 모두 처리하는 메소드
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// POST 문자 인코딩 처리
		req.setCharacterEncoding("UTF-8");
		
		
		String method = req.getMethod(); // 데이터 전달 방식을 변수에 저장
		
	
		if("GET".equals(method)) { // 데이터 전달 방식이 "GET"인 경우
			
			// 책 등록 화면 JSP로 요청 위임
			String path = "/WEB-INF/views/bookRegister.jsp";
			RequestDispatcher dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
	
			
		} else { // POST 방식일 경우
			
			String bookTitle = req.getParameter("bookTitle");
			String bookAuthor = req.getParameter("bookAuthor");
			int bookPrice = Integer.parseInt(req.getParameter("bookPrice"));
			int sale = Integer.parseInt(req.getParameter("sale"));
			
			
			// Book 객체 생성 후 파라미터 세팅
			Book book = new Book();
			book.setBookTitle(bookTitle);
			book.setBookAuthor(bookAuthor);
			book.setBookPrice(bookPrice);
			book.setSale(sale);
			
			
			try {
				
				BookDAO dao = new BookDAO();
				
				// DB에 데이터 삽입 후 결과 반환 받기
				int result = dao.bookRegister(book); 
				
				// 중간 확인
				//System.out.println("result : " + result);
				
				if(result > 0) {
					
					//   /practice/ncstest/boardList.do 를 재요청
					resp.sendRedirect("/practice/ncstest/boardList.do");
					// redirect 시 절대경로 작성하는 경우
					// 프로젝트명(contextPath)부터 모두 작성
					
				}
				
				
				
				
			}catch (SQLException e) {
				e.printStackTrace();
				
				// SQLException 발생 시
				// 에러 메세지를 출력하는 jsp로 요청 위임
				
				String errorMessage = "SQL 관련 예외가 발생했습니다!!!";
				req.setAttribute("errorMessage", errorMessage);
				
				String path = "/WEB-INF/views/error.jsp";
				RequestDispatcher dispatcher = req.getRequestDispatcher(path);
				dispatcher.forward(req, resp);
				
			}
		}
		
	}
	
	
	
	
	
	
	
}
