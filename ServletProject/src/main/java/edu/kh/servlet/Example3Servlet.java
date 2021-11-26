package edu.kh.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Example3Servlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String size = req.getParameter("size");
		String amount = req.getParameter("amount");
		String color = req.getParameter("color");
		
		System.out.println(size + "사이즈 / " + color + " / " + amount + "개" );
		
		
		
	
	
	}
	
}
