package edu.kh.semi.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.semi.wrapper.EncryptWrapper;

// 필터 (Filiter)
// 클라이언트 요청 시 생성되는  HttpServletRequest, HttpServletResponse
// 두 객체가 요청, 응답을 처리하는 Servlet / JSP에 도달하기 전에 전/후 처리를 하는 클래스

// 필터는 여러 개를 연쇄적으로 연결할 수 있다. (FilterChain)

// 필터 생명주기
// init() -> doFilter() -> destory() -> init() ... 반복

// init() : 서버 실행 시 필터가 생성될 때 수행되는 메소드
// doFilter() : 필터 역할을 수행하는 메소드
// destory() : 필터 코드가 변경되어 이전 필터가 필요 없어 졌을때 수행되는 메소드


// @WebFilter : 어떤 요청을 필터링할지 주소를 작성하고
//				필터에 이름을 부여하여 필터링 순서를 지정할 수 있게하는 어노테이션

@WebFilter(filterName = "encryptFilter", 
			urlPatterns = {"/member/login" , "/member/signup", "/member/updatePw" , "/member/secession"   })
public class EncryptFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("암호화 필터 생성");
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		// ServletRequest, ServletResponse
		// -> HttpServletRequest, HttpServletResponse의 부모 타입
		// --> 부모 부분만 사용 가능 
		// ---> 다운 캐스팅하여 자식 부분 사용
		
		HttpServletRequest req = (HttpServletRequest)request;
		//HttpServletResponse resp = (HttpServletResponse)response;
		
		
		// 필터링 되는지 확인
		//System.out.println(req.getParameter("memberPw"));
		
		// POST 방식의 요청일 경우 (비밀번호 입력이 포함되면 무조건 POST방식)
		if( req.getMethod().equals("POST") ) { 
			
			
			// req 요청 객체를 EncryptWrapper로 감싸기
			EncryptWrapper encWrapper = new EncryptWrapper(req);
			// -> 오버라이딩된 getParameter 사용 가능
			
			// 기존 req 대신 encWrapper를 Servlet으로 전달
			chain.doFilter(encWrapper, response);
			
		} else {
			
			// chain.doFilter(request, response) : 다음 필터로 요청/응답 전달
			// 다음 필터가 없으면 Servlet/JSP로 전달
			chain.doFilter(request, response);
		}
		
		
	}

	
	
	public void destroy() {
		System.out.println("암호화 필터 제거 후 새로 생성");
	}

}
