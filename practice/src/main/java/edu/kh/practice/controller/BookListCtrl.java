package edu.kh.practice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.practice.model.Book;
import edu.kh.practice.model.BookDAO;

@WebServlet("/ncstest/boardList.do")
public class BookListCtrl extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req, resp);
	}
	
	
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// DB에 저장된 모든 책 정보를 List로 얻어와 
		// JSP에 위임 후 출력하기
		
		req.setCharacterEncoding("UTF-8");
		
		try {
			
			BookDAO dao = new BookDAO();
			
			List<Book> bookList = dao.selectBookList();
			
			// 조회 결과를 req 속성으로 추가한 후 jsp로 요청 위임
			req.setAttribute("bookList", bookList);
			
			String path = "/WEB-INF/views/bookList.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		
			String errorMessage = "책 정보 조회 중 문제발생!!";
			req.setAttribute("errorMessage", errorMessage);
			
			String path = "/WEB-INF/views/error.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
		
		}
		
		
		
		
		
	}
	
	
	
	
	
}
