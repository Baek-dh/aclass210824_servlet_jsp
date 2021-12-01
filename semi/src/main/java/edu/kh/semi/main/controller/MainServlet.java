package edu.kh.semi.main.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메인페이지 요청 시 필요한 비즈니스 로직 처리
		
		Date today = new Date(); // 현재 날짜, 시간을 기록한 Date 객체 생성
		
		// 년,월,일만 출력할 수 있도록 출력 형태 지정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		
		req.setAttribute("today", sdf.format(today));
		
		// jsp로 요청 위임
		String path = "/WEB-INF/views/common/main.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		dispatcher.forward(req, resp);
	}
	
	
}
