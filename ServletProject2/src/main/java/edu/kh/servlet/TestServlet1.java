package edu.kh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet1 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// HttpServletRequest req : 요청 관련 데이터, 정보를 가지고 있는 객체
		// HttpServletResponse resp : 응답 관련 정보, 스트림을 가지고 있는 객체
		
		
		// Parameter : 클라이언트(고객) 요청 시 전달 받은 값
		
		String inputName = req.getParameter("inputName");
		// -> 요청 시 전달 받은 값 중 name 속성값이 "inputName"인 input 태그의 value를 얻어옴
	
		String gender = req.getParameter("gender");
		
		
		
		// 요청 시 전달 받은 값 중 중복되는 name 속성이 있는 경우
		// ==> 이름이 같은 파라미터들을 하나의 배열로 저장
		
		String[] hobby = req.getParameterValues("hobby");
		String[] hashtag = req.getParameterValues("hashtag");
		
		System.out.println(inputName);
		System.out.println(gender);
		System.out.println(  Arrays.toString(hobby)   );
		System.out.println(  Arrays.toString(hashtag)   );
		
		
		// *** 응답(Response) ***
		// - 클라이언트의 요청에 따라 처리된 내용을 
		//  서버 -> 클라이언트로 전달
		
		// ** Servlet을 이용하여 응답하는 경우
		// 요청에 따라 응답화면 모양을 변경할 수 있다.
		// - 가능한 이유 : servlet에서 상황에 따라 html 코드를 새로 만들기 때문
		
		// Servlet을 이용한 응답 화면 만들기
		// 1. 응답 화면(HTML)의 형식 지정
		resp.setContentType("text/html; charset=UTF-8");
		
		// 2. 만들어진 응답 화면을 전달 받을 클라이언트와의 연결 통로(스트림) 얻어오기
		PrintWriter out = resp.getWriter();
						// response 객체에서 클라이언트와 연결된 문자기반 스트림을 얻어옴
		
		// 3. HTML 코드를 작성하여 스트림을 이용해 출력
		out.print("<!DOCTYPE html>");
		out.print("<html>");
		
		out.print("<head>");
		out.print("<title>" + inputName + "님 응답화면</title>");
		out.print("</head>");
		
		out.print("<body>");
		
		out.print("<h3>이름 : " + inputName + "</h3>");
		out.print("성별은 <span style='color:red'>" + gender + "자</span> 입니다.");
		
		out.print("<h3>선택한 취미</h3>");
		out.print("<p>");
		if(hobby == null) { // 선택한 취미가 없을 경우
			out.print("없음");
		}else {
			// 취미 한 줄씩 추가
			for(String h : hobby) {
				out.print(h + "<br>");
			}
		}
		out.print("</p>");

		
		// 해시태그
		out.print("<h4 style='color:gray'>");
		
		for(String tag : hashtag) {
			if(!tag.equals("")) { // 해시태그가 작성되어 있는 경우
				out.print("#"+tag+"&nbsp;");
			}
		}
		
		out.print("</h4>");
		
		out.print("</body>");
		 
		
		out.print("</html>");
		
	}
	
	
	
	
	
	
}
