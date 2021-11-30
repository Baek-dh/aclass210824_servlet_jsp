package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/EL/scope")
public class ScopeServlet extends HttpServlet{

	// a태그를 이용한 요청은 무조건 GET 방식
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// page는 패스
		
		// 2. request scope
		req.setAttribute("msg", "request scope 입니다");
		req.setAttribute("reqValue", 100);
		
		
		// 3. session scope
		HttpSession session = req.getSession(); // session 객체 얻어오기
		session.setAttribute("msg", "session scope 입니다");
		session.setAttribute("sessionValue", 200);
		
		// 4. application scope
		ServletContext application = req.getServletContext(); // application 객체 얻어오기
		application.setAttribute("msg", "application scope 입니다");
		application.setAttribute("appValue", 300);
		
		
		
		// JSP로 요청 위임
		String path = "/WEB-INF/views/EL/scopeResult.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		dispatcher.forward(req, resp);
		
		
		
	
	}
	
	
	
	
	
	
}
