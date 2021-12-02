package edu.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;


@WebServlet("/member/signup")
public class SignUpServlet extends HttpServlet {

	// 데이터 전송 방식
	// POST : 삽입
	// GET : 조회(검색)
	// PUT :  수정
	// DELETE : 삭제
	
	
	// GET 방식 요청 시 회원 가입 화면으로 응답
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/member/signUp.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	
	
	// POST 방식 요청 시 회원 정보 삽입 수행
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// POST 방식의 경우 문자 인코딩 처리가 필요하지만
		// EncodingFilter를 생성하였기 때문에 별도 처리가 필요 없다!
	
		// 회원 가입 시 입력 받은 파라미터를 모두 변수에 저장
		String memberId = req.getParameter("id");
		String memberPw = req.getParameter("pwd1");
		String memberName = req.getParameter("name");
		String memberEmail = req.getParameter("email");
		
		String[] phone = req.getParameterValues("phone");
		String[] address = req.getParameterValues("address");
		
		// 필수 입력인 전화번호를 하나의 문자열로 합치기
		String memberPhone = String.join("-", phone);
		// String.join(구분자, 배열) : 배열요소를 하나의 문자열로 합치되 
		//							   요소 사이사이에 구분자를 추가
		
	
		// 선택적 입력인 주소를 하나의 문자열로 합치기
		// -> 입력 안되면 모든 요소가 빈칸
		String memberAddress = null;
		
		if(!address[0].equals("")) { // 우편 번호가 있음 == 주소를 작성함
			memberAddress = String.join(",,", address);
		}
		
		
		// 저장된 파라미터를 하나의 Member 객체에 저장
		Member member = new Member(memberId, memberPw, memberName, memberPhone, memberEmail, memberAddress);
		
		
		try {
			// MemberService 객체 생성
			MemberService service = new MemberService();
			
			// 회원 가입 Service 수행 후 결과 반환 받기
			int result = service.signUp(member);
			
			// 중간확인
			System.out.println("result : " + result);
			
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	
}
