package edu.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 문자인코딩 처리 -> 필요없음! -> 왜? 필터 처리 되어있음
		
		// 파라미터 얻어오기
		String memberEmail = req.getParameter("email");
		
		String[] phone = req.getParameterValues("phone");
		String memberPhone = String.join("-", phone);
		
		String[] address = req.getParameterValues("address");
		
		String memberAddress = null;
		if( !address[0].equals("") ) { // 우편번호가 비어있지 않은 경우
			memberAddress = String.join(",,", address);
		}
		
		
		// Member 객체를 생성하여 파라미터를 하나의 객체에 저장
		Member member = new Member();
		member.setMemberEmail(memberEmail);
		member.setMemberPhone(memberPhone);
		member.setMemberAddress(memberAddress);
		
		
		// 어떤 회원 정보를 수정할지 구분하기 위한 "회원번호"를
		// session에 있는 loginMember에서 얻어오기
		
		// 1) session 객체 얻어오기
		HttpSession session = req.getSession();
		
		// 2) session에서 loginMember 얻어오기
		Member loginMember =  (Member)session.getAttribute("loginMember");
		
		// 3) loginMember에서 회원번호를 얻어와 member에 세팅
		member.setMemberNo(  loginMember.getMemberNo()   );
		// -> member에 이메일, 주소, 전화번호, 회원번호가 모두 담긴 상태
		
		
		try {
			
			int result = new MemberService().update(member);
			
			
			if(result > 0) { // 성공
				session.setAttribute("message", "회원 정보가 수정 되었습니다.");
				
				// update 성공 == DB 내용 수정
				// 하지만 ... 로그인된 정보-> session에 있는 loginMember는 수정 안됨.
				
				// loginMember 변수에는 Session에 있는 "loginMember" 객체의 주소가 담겨있음(얕은 복사)
				// -> 복사본을 수정해도 원본이 수정된다!
				// -> 이를 이용해 세션 정보를 최신화!
				loginMember.setMemberEmail(  memberEmail );
				loginMember.setMemberPhone(  memberPhone );
				loginMember.setMemberAddress(  memberAddress );
				
			}else { // 실패
				session.setAttribute("message", "회원 정보 수정 실패");
			}
			
			
			// 내 정보 페이지 재요청
			resp.sendRedirect("myPage");
			
		}catch(Exception e){
			e.printStackTrace();
			
			req.setAttribute("errorMessage", "회원 정보 수정 과정에서 오류 발생");
			req.setAttribute("e", e);
			
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
		
		
	}
	
	
	
	
	
}
