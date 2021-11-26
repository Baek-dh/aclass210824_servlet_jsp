package edu.kh.servlet.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// @WebServlet("url")
// 기존에 web.xml에 작성했던 url 패턴에 따른 servlet 연결 구문을 간소화 하는 어노테이션
@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	// post 요청 처리
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// post 방식 요청 -> 한글이 깨질것이다 ...
		// -> 요청 객체 문자 인코딩 변경
		req.setCharacterEncoding("UTF-8");
		
		String inputId = req.getParameter("inputId");
		String inputPw = req.getParameter("inputPw");
		
		
		// 체크박스가 1개인 경우 ==> 배열로 받을 필요 없다
		
		// 체크박스는 체크가 된 경우 value 값을 전달하는데
		// value값 없으면 == "on"
		// 체크 안하면 == null
		String saveId = req.getParameter("saveId");
	
		System.out.println(inputId);
		System.out.println(inputPw);
		System.out.println(saveId);
		
		
		// 응답 화면을 만들기 위해 요청 발송자를 이용해서 jsp로 전송
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/loginResult.jsp");
		dispatcher.forward(req, resp);
		
	
	}

	
	
	
}
