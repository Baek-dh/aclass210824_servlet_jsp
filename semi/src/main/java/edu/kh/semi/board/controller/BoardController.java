package edu.kh.semi.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//   /semi/board/list
//   /semi/board/update
//   /semi/board/insert
//   /semi/board/select


@WebServlet("/board/*")
public class BoardController extends HttpServlet{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		
		
		
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
		// POST 방식으로 요청이 오게 되면 이를 doGet() 메소드로 전달
		// -> GET/POST 모두 doGet()에서 처리
	}
	
	
	
	
	
	
	
	
	
	
}
