package edu.kh.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 어떤 주소로 요청이 왔을 때 해당 서블릿으로 처리할 것인지 지정
// (주의) 서로 다른 Servlet이라도 요청 주소는 같을 수 없다 -> 요청 주소는 무조건 달라야 한다
@WebServlet("/order")
public class OrderServlet extends HttpServlet{

	// Servlet : 웹 서비스를 위한 자바 클래스
	// 요청에 따른 응답 화면을 만드는 클래스
	
	// get방식 요청 처리 메소드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// HttpServletRequest  : 요청(클라이언트 -> 서버)
		// -> 요청과 관련된 데이터, 정보 등을 가지고 있는 객체
		//   ex) 파라미터, 요청방식, Context Root, 요청 주소, 요청자 ip, RequestDispatcher
		
		// HttpServletResponse : 응답(서버 -> 클라이언트)
		// -> 응답과 관련된 정보, 스트림 등을 가지고 있는 객체
		//   ex) getWriter() (스트림), Cookie
	
		
		// 파라미터 중 가격, 수량의 곱연산 값을 total 변수에 저장
		String price = req.getParameter("price");
		String amount = req.getParameter("amount");
		System.out.println(price);
		System.out.println(amount);
		
		// Integer.parseInt("숫자만 작성된 문자열")  : String 값을 Integer 값으로 변환
		//  Integer   ->     int      오토 언박싱
		// (Wrapper)    (기본 자료형)
		int total = Integer.parseInt(price) * Integer.parseInt(amount);
		
		// total을 JSP로 전달하기 위하여 HttpServletRequest에 속성으로 추가
		req.setAttribute("total", total);
		// -> 이 때, 세팅된 값의 자료형은 Object로 업캐스팅된다
		
		
		// 응답 화면을 JSP를 이용하여 만들기
		// 1) Servlet -> JSP로 요청 정보를 전달할 발송자 RequestDispatcher 생성
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/orderResult.jsp");
		
		// 2) RequestDispatcher를 이용하여 요청, 응답 관련 객체 전달(forward)
		dispatcher.forward(req, resp);
		
		
	} // doGet() 끝
	
	
	// POST 방식 요청 시 처리 메소드
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// POST 방식은 문자인코딩 문제가 발생함 
		// -> 문자 인코딩 변환 작업 필요
		req.setCharacterEncoding("UTF-8"); // 대소문자 구분 X
		
		// 파라미터를 얻어와 int로 변환
		int price = Integer.parseInt( req.getParameter("price") );
		int amount = Integer.parseInt( req.getParameter("amount") );
		
		// price, amount 곱연산 결과를 total이라는 키값으로 req 속성에 추가
		req.setAttribute("total", price * amount);
	
		// req, resp를 JSP로 전달(forward)
		req.getRequestDispatcher("/WEB-INF/views/orderResult.jsp").forward(req, resp);
	
	}
	
	
	
	
	
	
	
	
	
	
	
}
