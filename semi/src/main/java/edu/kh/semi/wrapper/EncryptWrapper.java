package edu.kh.semi.wrapper;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncryptWrapper extends HttpServletRequestWrapper {


	// HttpServletRequestWrapper
	// -> 클라이언트의 요청을 감싸서 가공하는 객체
	
	
	// HttpServletRequestWrapper는 매개변수 1개짜리 생성자만 존재한다.
	public EncryptWrapper(HttpServletRequest request) {
		super(request);
	}


	// getParameter() 메소드를 오버라이딩
	@Override
	public String getParameter(String name) {
		
		// 매개변수로 memberPw가 넘어온 경우 암호화를 진행
		// 아닌 경우 원래 getParameter() 동작을 수행
		
		String value = null;
		
		switch(name) {
		case "memberPw" : 
		case "pwd1" :
			value = getSha512( super.getParameter(name) );
			break;
		
		default : value = super.getParameter(name); 
		}
		
		return value;
	}
	
	
	
	/** SHA-512 해쉬 함수를 이용하여 문자열 암호화를 진행하는 메소드
	 * @param pwd
	 * @return encPwd
	 */
	private String getSha512(String pwd) {
		
		// 1. 암호화된 비밀번호를 저장할 변수 선언
		String encPwd = null;
		
		// 2. 해쉬 함수를 수행하는 객체를 저장할 변수 선언
		// 해쉬 함수 : 특정 값을  여러 단계의 연산을 거쳐 일정 길이의 무작위 값을 얻어내는 함수
		MessageDigest md = null;
		
		try {
			// 3. SHA-512 방식의 해쉬함수를 수행할 수 있는 객체를 얻어옴
			md = MessageDigest.getInstance("SHA-512");
			
			// 4. md를 이용해 문자열 암호를 하기 위해 byte 배열로 변환
			byte[] bytes = pwd.getBytes(Charset.forName("UTF-8"));
			
			
			// 5. md 객체에 바이트로 변환된 비밀번호를 전달하여 암호화를 수행
			md.update(bytes);
			
			// 6. md 객체에서 암호화된 내용을 꺼내옴
			//  Base64 : 바이트 코드를 문자열로 바꾸는 라이브러리
			encPwd = Base64.getEncoder().encodeToString(md.digest());
			// md.digest() : 암호화된 코드를 꺼내옴
			
			System.out.println("암호화 전 : " + pwd);
			System.out.println("암호화 후 : " + encPwd);
			
		}catch (NoSuchAlgorithmException e) {
			// SHA-512 해쉬함수가 없는 경우 발생
			e.printStackTrace();
		}
		
		return encPwd;
	}
		
	
	
	
	
	
	
	
}
