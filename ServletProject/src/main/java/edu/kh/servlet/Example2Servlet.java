package edu.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Example2Servlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 전달 받은 값 (== 파라미터)을 얻어와 변수에 저장
		// -> 파라미터는 모두 String이다!!!
		String food = req.getParameter("food");
		String amount = req.getParameter("amount");
		String message = req.getParameter("message");
		
		System.out.println(food);
		System.out.println(amount);
		System.out.println(message);
		
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String result = food + " / " + amount + " / " + message;
		
		out.print("<!DOCTYPE html>");
		out.print("<html lang='ko'>");
		out.print("<head>");
		out.print("<title>결과 페이지</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<h1>" + result + "</h1>");
		out.print("<h3>결과 출력 완료</h3>");
		out.print("</body>");
		out.print("</html>");
		
		
		
		
		
	}
	
	
	
	
}
