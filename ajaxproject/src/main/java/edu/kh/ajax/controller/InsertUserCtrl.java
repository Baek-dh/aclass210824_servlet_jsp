package edu.kh.ajax.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.ajax.model.UserDAO;

@WebServlet("/user/insertUser.do")
public class InsertUserCtrl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req, resp);
	}
	
	
	
	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 사용자 등록
		String userName = req.getParameter("userName");
		
		if(userName != null) { // 작성된 userName이 있을 때
			userName = replaceParameter(userName); // XSS 방지 처리
			
			
			// 사용자 이름 DAO로 전달
			try {
				new UserDAO().insertUser(userName);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
	}
	
	
	private String replaceParameter(String param) {
		String result = param;

		if (param != null) {
			result = result.replaceAll("&", "&amp;");
			result = result.replaceAll("<", "&lt;");
			result = result.replaceAll(">", "&gt;");
			result = result.replaceAll("\"", "&quot;");
		}

		return result;
	}
	
	
	
}
