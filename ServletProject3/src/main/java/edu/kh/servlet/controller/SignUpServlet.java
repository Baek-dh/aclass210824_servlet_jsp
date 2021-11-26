package edu.kh.servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpServlet extends HttpServlet {

	// get 방식 요청을 처리하는 메소드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// HttpServletRequest req : 요청과 관련된 정보, 데이터(값)을 가지고 있는 객체
		// HttpServletResponse resp : 응답과 관련된 정보, 스트림을 가지고 있는 객체

		// input 태그에 작성되어 전달 받은 값을 변수에 저장
		// == 파라미터(Parameter)

		String memberId = req.getParameter("memberId");
		// input의 name 속성 값

		String memberPw = req.getParameter("memberPw");
		String memberName = req.getParameter("memberName");
		String memberEmail = req.getParameter("memberEmail");

		System.out.println(memberId);
		System.out.println(memberPw);
		System.out.println(memberName);
		System.out.println(memberEmail);

	}

	// post 방식을 처리하는 메소드
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// POST 방식으로 데이터(값)를 전달하게 되면
		// 문자 인코딩이 지정되어 있지 않아 2byte 범위의 문자(한글, 한자, 이모티콘 등등)
		// 들이 모두 깨져서 보이게 된다
		
		// -> 문자 인코딩 지정하면 해결!
		req.setCharacterEncoding("UTF-8");
		// -> 요청 관련 객체에 담긴 값의 문자 인코딩을 변경
		
		String memberId = req.getParameter("memberId");
		String memberPw = req.getParameter("memberPw");
		String memberName = req.getParameter("memberName");
		String memberEmail = req.getParameter("memberEmail");

		System.out.println(memberId);
		System.out.println(memberPw);
		System.out.println(memberName);
		System.out.println(memberEmail);

		
		// 요청에 대한 응답 화면 만들어 내보내기
		/*
		// 1. 응답 화면의 문서 형식 지정
		resp.setContentType("text/html; charset=UTF-8");
		
		// 2. 응답 화면을 클라이언트에게 전달할 통로(스트림) 얻어오기
		PrintWriter out = resp.getWriter();
		
		// 3. 클라이언트에게 응답 화면 코드(HTML) 내보내기(출력)
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		out.print("<head>");
		out.print("<title>회원 가입 결과</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<h1>" + memberName + "님의 가입을 환영합니다.</h1>");
		out.print("</body>");
		out.print("</html>");*/
		
		
		// ************************************************
		// JSP를 이용하여 응답하기
		
		// Dispatcher : 발송자, 필요 정보를 제공하는자
		// RequestDispatcher : 요청 발송자 , 요청 발송 객체(요청 위임 객체)
		
		// RequestDispatcher는 무엇을 발송(위임)하는가?
		// -> Servlet에서는 응답 화면(HTML)코드를 만들기가 힘들다
		//    그래서 응답 화면을 더 쉽게 만들 수 있는 JSP에게
		//    응답화면을 만들어 달라고 요청을 발송(위임)하는 역할을 하는 객체이다.
		
		//   이 때, 매개변수로 
		//   요청 관련 정보를 담고있는 객체 HttpServletRequest를 전달하여
		//   파라미터, 클라이언트 정보를 이용해 응답 화면을 구성할 수 있고,
		
		//   응답 관련 정보를 담고있는 객체 HttpServletResponse를 전달하여
		//   클라이언트와 스트림으로 연결하여 자동으로 응답 화면을 출력해준다.
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/result.jsp");
		
		dispatcher.forward(req, resp);
		// forward : 전송하다, 보내다
		
		
		
		
		
	}

}
