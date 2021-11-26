package edu.kh.servlet.model.service;

import static edu.kh.servlet.common.JDBCTemplate.*;

import java.sql.Connection;

import edu.kh.servlet.model.dao.MemberDAO;
import edu.kh.servlet.model.vo.Member;

public class MemberService {

	private MemberDAO dao = new MemberDAO();

	
	/** 회원 가입
	 * @param member
	 * @return result (1 성공)
	 * @throws Exception
	 */
	public int signUp(Member member) throws Exception {
		
		// Connectoin 얻어오기
		Connection conn = getConnection();
		
		// DAO 호출 후 결과 반환 받기
		int result = dao.signUp(member, conn);
		
		// 반환 받은 결과에 따라서 트랜잭션 제어
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		// 사용한 Connection 반환
		close(conn);
		
		// 결과 반환
		return result;
	}
	
	
	
	
}
