package edu.kh.semi.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.semi.member.model.service.MemberService;
import edu.kh.semi.member.model.vo.Member;

@WebServlet("/member/updatePw")
public class UpdatePwServlet extends HttpServlet{

	// GET 방식 요청 시 비밀번호 변경 JSP로 요청 위임
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// JSP 요청 위임 -> jsp 파일 경로
		req.getRequestDispatcher("/WEB-INF/views/member/updatePw.jsp").forward(req, resp);
		
	}
	
	// POST 방식 요청 시 DB 비밀번호 변경
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 암호화된 파라미터 얻어오기
		String currentPw = req.getParameter("currentPw");
		String newPw1 = req.getParameter("newPw1");
		
		// 중간확인
		//System.out.println(currentPw);
		//System.out.println(newPw1);
		
		// 로그인한 회원의 회원 번호 얻어오기
		int memberNo =  ((Member)req.getSession().getAttribute("loginMember")).getMemberNo();
		
		try {
			
			// 비밀번호 변경 서비스 호출 후 결과 반환 받기
			int result = new MemberService().updatePw(currentPw, newPw1, memberNo);

			String message = null; // 성공/실패 메세지
			String path = null; // 마이페이지/비밀번호 변경 페이지 주소
			
			if(result > 0) {
				message = "비밀번호가 변경되었습니다.";
				path = "myPage";
			}else{
				message = "현재 비밀번호가 일치하지 않습니다.";
				path = "updatePw";
			}
			
			req.getSession().setAttribute("message", message);
			resp.sendRedirect(path);
			
		}catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("errorMessage", "비밀번호 수정 중 문제가 발생했습니다.");
			req.setAttribute("e", e);
			
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
	
		}
		
		
		
		
	
	}
	
}
