package edu.kh.servlet.model.dao;

import static edu.kh.servlet.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.servlet.common.JDBCTemplate;
import edu.kh.servlet.model.vo.Member;

public class MemberDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	
	public MemberDAO() {
		try {
			prop = new Properties();
			
			String filePath = MemberDAO.class.getResource("/edu/kh/servlet/sql/member-query.xml").getPath();       
		
			prop.loadFromXML( new FileInputStream(filePath) );
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	/** 회원 가입
	 * @param member
	 * @param conn
	 * @return result(1 성공) 
	 * @throws Exception
	 */
	public int signUp(Member member, Connection conn) throws Exception{
		
		// 결과 저장용 변수
		int result = 0;
		
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("signUp");
			
			// SQL을 DB에 전달하고 결과 받아오는 객체 생성하기
			pstmt = conn.prepareStatement(sql);
			
			// SQL 빈자리에 알맞은 값 세팅
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberEmail());
			
			// SQL 수행 후 결과 반환 받기
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
			// 사용한 JDBC 객체 자원 반환
		}
		
		// 결과 반환
		return result;
	}
	
	
	
	
	
	
	
	
	
	
}
