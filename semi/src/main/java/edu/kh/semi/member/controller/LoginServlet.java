package edu.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;

// MVC Model2 패턴 
// Model View Controller의 약자로
// 백엔드, 프론트엔드를 구분해서 협업을 원할하게 진행할 수 있게 하는 디자인 패턴

// Model : 비즈니스 로직(백엔드 코드, 보이지 않는 곳에서 수행되는 로직)
// View : 모든 화면(html, jsp, 프론트엔트)
// Controller : 클라이언트의 요청을 받아들이고
//              요청에 따라 알맞은 Service를 선택,
//              Service 결과에 따라 응답 화면 제어 
//              (요청, 응답 흐름 제어 역할)

@WebServlet("/member/login")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// POST 방식 요청 -> 문자 인코딩 처리 필요
		req.setCharacterEncoding("UTF-8");
		
		// 전달 받은 파라미터를 변수에 저장
		String memberId = req.getParameter("memberId");
		String memberPw = req.getParameter("memberPw");
		
		// 파라미터 확인
//		System.out.println("memberId : " + memberId ) ;
//		System.out.println("memberPw : " + memberPw ) ;
	
		
		// 아이디, 비밀번호를 DB로 전달하여 일치하는 회원 정보 조회 Service 호출
		try {
			// DB 연결 중 발생하는 예외를 모두 Controller에 모아서 일괄 처리
			
			// 1) Service 객체 생성
			MemberService service = new MemberService();
			
			// 2) 로그인 Service 메소드 호출 후 결과 반환 받기
			Member loginMember = service.login(memberId, memberPw);
			
			// 중간 확인
			//System.out.println(loginMember);
			
			// 로그인 Service 수행 후 받아올 수 있는 결과의 종류
			// 1. 회원 정보가 담겨있는 Member 객체
			//	1-1 : 정상(100)    ,   1-2 : 정지(102) 
			// 2. null
			// 3. Exception
			
			
			// * 로그인 관련 처리 시 Session 객체를 이용할 예정
			HttpSession session = req.getSession();
			
			// Session의 자원 관리는 브라우저가 아닌 서버측에 이루어진다.
			// 요청 시 브라우저(클라이언트)가 보유한 Session ID를 얻어와
			// 서버에서 동일한 ID를 가진 Session 객체를 반환해서 사용한다.
			
			
			// 조회된 회원 정보가 있을 경우
			if(loginMember != null) {
				
				// 조회된 회원 정보 중 상태 코드가 정상(100)일 경우
				if(loginMember.getStatusCode() == 100) {
					
					// 로그인 성공 -> 로그아웃 또는 브라우저를 끄기 전까지 로그인 상태 유지
					
					// Session에 로그인 정보를 추가해두면
					// 브라우저 끄기 전까지 계속 로그인된 회원 정보를
					// Session에서 얻어다 사용할 수 있다. == 로그인
					
					session.setAttribute("loginMember", loginMember);
				
					// Session 유효 시간 지정 (초 단위)
					// -> 요청 시 마다 다시 초기화
					session.setMaxInactiveInterval(1800); // 1800초 == 30분 

					
					// =======================================================
					// 아이디 저장 (Cookie)
					
					// Cookie란 ?
					// 클라이언트(브라우저) 측에서 관리하는 파일.
					
					// 특정 주소 요청 시 마다
					// 알맞은 Cookie파일에 작성된 값을 첨부해서 요청을 보냄.
					
					
					// 쿠키 생성 및 사용 순서
					// 1. 특정 요청에 대한 응답 시
					//    서버측에서 Cookie 파일을 생성하여
					//    응답에 담아서 같이 클라이언트로 내보냄.
					
					// 2. 응답을 받은 클라이언트 컴퓨터에 Cookie 파일이 저장됨.
					
					// 3. 이후 해당 사이트 이용 시
					//    저장된 Cookie 파일의 내용을 자동으로 요청에 실어서 보냄.
					
					
					// 2) 새로운 Cookie 생성
					Cookie cookie = new Cookie("saveId", memberId);
					// -> "saveId" 라는 Key와 
					//    로그인 성공한 memberId를 value로 가지는
					//    Cookie 객체 생성
					
					
					// 1) 아이디 저장 체크박스가 체크 되어 있는 경우
					if( req.getParameter("save") != null ) {
						
						// 3) 쿠키가 유지될 수 있는 유효기간을 설정(초 단위)
						cookie.setMaxAge(60 * 60 * 24 * 30); // 30일
						
					}else { // 아이디 저장 체크 X
						
						// 4) 쿠키를 없애기 위해 유효기간을 0초로 설정
						cookie.setMaxAge(0); // 0초
						// -> 이전에 클라이언트 컴퓨터에 저장되어 있는
						//    동일 쿠키 파일이 있을 경우
						//    새로운 쿠키 파일이 덮어 씌우게 된다.
						
						// -> 덮어 씌우자 마자 수명이 다됨 == 삭제
					}
					
					// 5) 쿠키 파일이 사용되어질 경로(주소) 지정
					cookie.setPath( req.getContextPath() );
					//  req.getContextPath()   == /semi
					// -> /semi + /semi의 하위 주소 모두에서 쿠키파일 사용 가능
					
					
					// 6) 생성 및 설정이 완료된 쿠키를 클라이언트에게 전달
					// -> 응답 객체 resp 사용
					resp.addCookie(cookie);
					
					
					
					// =======================================================
					
					
					
				}else { // 정지 상태(102)인 경우
					
					session.setAttribute("message", "정지된 회원 입니다.");
					
				}
				
				
			} else { // 로그인 실패한 경우
				
				session.setAttribute("message", "아이디 또는 비밀번호를 확인해주세요.");
				
			}
			
			
			// 로그인 서비스 수행 후 응답 화면 지정
			// 1. 메인 페이지로 이동
			// 2. 로그인 이전 페이지로 이동
			
			
			//** Servlet에서 응답 화면을 지정하는 방법 두 가지
			// 1. forward(요청 위임)
			//	 Servlet으로 구현하기 힘든 응답화면을
			//	 JSP로 req, resp를 전달하여 대신 화면을 만들게 한다.
			
			//  * Servlet -> JSP로 요청 위임 시 req, resp 객체가 유지된다.
			
			// 2. redirect(재요청)
			//   직접 응답 화면을 만드는 것이 아닌
			//   응답 화면을 만드는 다른 주소를 재요청하여 
			//   대신 응답 화면을 만들게 함.
			
			//   기존 req, resp 삭제 후 재 생성
			
			// 메인 페이지로 redirect (메인 페이지 요청 주소 작성)
			resp.sendRedirect( req.getContextPath() ); // "/semi"
							//  Servlet/JSP 요청 주소 (주소 경로)
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
}





