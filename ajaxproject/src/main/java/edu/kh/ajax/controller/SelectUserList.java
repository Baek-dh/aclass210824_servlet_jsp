package edu.kh.ajax.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.kh.ajax.model.UserDAO;
import edu.kh.ajax.model.UserVO;

@WebServlet("/user/selectUserList.do")
public class SelectUserList extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req, resp);
	}

	protected void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		try {
			// 사용자 목록 조회
			List<UserVO> userList = new UserDAO().selectUserList();
			
			if(userList.size() > 0) { // !userList.isEmpty(), 조회된 사용자가 있다면
				
				// userList 를 JSON으로 변환 하기 위한 객체 생성
				Gson gson = new Gson();
				
				// gson을 이용해서 userList -> JSON(자바스크립트 객체 모양 문자열)으로 변환
				String result = gson.toJson(userList);
				
				// 변환된 JSON 문자열을 req 속성으로 세팅
				req.setAttribute("result", result);
			}
			
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/result.jsp");
			dispatcher.forward(req, resp);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	
}
