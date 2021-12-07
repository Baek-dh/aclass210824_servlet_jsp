package edu.kh.semi.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.semi.member.model.service.MemberService;

@WebServlet("/member/idDupCheck")
public class IdDupCheckServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 비동기로 전달 받은 파라미터(inputId) 얻어오기
		String inputId = req.getParameter("inputId");
		
		// 아이디가 입력 될 때 마다 비동기로 서버에 값이 넘어오는지 확인
		// System.out.println("inputId : " + inputId ); // -> 확인 완료
		
		try {
			MemberService service = new MemberService();
			
			// DB에 중복되는 아이디가 있는지 조회하는 서비스 요청 후 결과 반환 받기
			int result = service.idDupCheck(inputId);
			
			
			// AJAX는 화면의 부분적인 갱신을 하는데 사용하기 때문에
			// 응답화면 전체를 다시 만드는 코드를 작성하지 않음.
			// --> forward, redirect  구문 X
			
			PrintWriter out = resp.getWriter();
			// 클라이언트와 연결된 스트림
			
			out.print(result); // 아이디 중복 조회 결과를 클라이언트에게 출력
								// -> 현재 비동기 통신 중
								// -> 화면 재구성이 아닌 ajax 코드의 success 매개변수로 전달됨.
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
	
	}
}
