package edu.kh.semi.member.model.service;

import static edu.kh.semi.common.JDBCTemplate.*;
// -> 해당 클래스 내 static 요소 호출 시 클래스명 생략

import java.sql.Connection;

import edu.kh.semi.member.model.dao.MemberDAO;
import edu.kh.semi.member.model.vo.Member;

// Service : 데이터 가공 
//		   + 트랜잭션 제어(commit/rollback) 
//			-> 이 때 Connection 필요하기 때문에 Service에서 Connection 얻어오기 수행
public class MemberService {

	private MemberDAO dao = new MemberDAO();
	
	// alt + shift + j  : 클래스/메소드 설명 작성 주석
	
	/** 로그인 서비스
	 * @param memberId
	 * @param memberPw
	 * @return loginMember (실패 시 null 반환)
	 * @throws Exception
	 */
	public Member login(String memberId, String memberPw) throws Exception {
		
		// 1) Connection 얻어오기
		Connection conn = getConnection();
						// DBCP에 생성 되어있는 커넥션 객체 하나를 빌려오기.
		
		// 2) DAO 메소드 호출해서 결과 반환 받기
		Member loginMember = dao.login(memberId, memberPw, conn);
		
		// 3) 사용한 Connection 반환하기
		close(conn);  // == conn.close();
		
		// 4) 결과 반환
		return loginMember;
	}
	
	
	
	
	
	
}
