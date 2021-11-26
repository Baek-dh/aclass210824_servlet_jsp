package edu.kh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Example1Servlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// form태그에서 전달 받은 input 태그 값을 변수에 저장
		String inputName = request.getParameter("inputName");
		String inputAge  = request.getParameter("inputAge");
		
		System.out.println(inputName);
		System.out.println(inputAge);
	
	}
	
	

}
