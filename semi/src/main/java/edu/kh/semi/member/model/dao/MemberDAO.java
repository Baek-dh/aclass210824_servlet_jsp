package edu.kh.semi.member.model.dao;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.semi.member.model.vo.Member;

// DAO(Database Access Object)
public class MemberDAO {

	// JDBC 객체 참조 변수
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// XML에 작성된 SQL을 얻어와 저장할 Collection 객체 참조 변수 선언
	private Properties prop;
	// Properties : key, value가 모두 String인 Map
	
	
	public MemberDAO() { // 기본 생성자
		
		// DAO 객체 생성 시 특정 위치에 있는 XML 파일을 읽어와 prop에 저장
		try {
			prop = new Properties();
			
			String filePath 
			= MemberDAO.class.getResource("/edu/kh/semi/sql/member-query.xml").getPath();     
			// -> SQL이 작성된 XML 파일의 경로
			
			prop.loadFromXML( new FileInputStream( filePath ) );
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	/** 로그인 DAO
	 * @param memberId
	 * @param memberPw
	 * @param conn
	 * @return loginMember (실패 시 null)
	 * @throws Exception
	 */
	public Member login(String memberId, String memberPw, Connection conn) throws Exception{
		
		// 1) 결과 저장용 변수 선언
		Member loginMember = null;
		
		try {
			// 2) SQL 얻어오기
			String sql = prop.getProperty("login");
			
			// 3) preparedStatement 객체 생성 및 SQL 적재
			pstmt = conn.prepareStatement(sql);
			
			// 4) 위치 홀더에 알맞은 값 세팅
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			// 5) SQL 수행 후 결과 집합(ResultSet) 반환 받기
			rs = pstmt.executeQuery();
			
			// 6) 조회 결과가 있을 경우 Member 객체를 생성하여 값 세팅
			if(rs.next()) {
				loginMember = new Member();
				
				loginMember.setMemberNo( rs.getInt("MEMBER_NO") );
				loginMember.setMemberId( memberId ); // 전달 받은 매개변수
				loginMember.setMemberName( rs.getString("MEMBER_NM") );
				loginMember.setMemberPhone( rs.getString("MEMBER_PHONE") );
				loginMember.setMemberEmail( rs.getString("MEMBER_EMAIL") );
				loginMember.setMemberAddress( rs.getString("MEMBER_ADDR") );
				loginMember.setEnrollDate( rs.getDate("ENROLL_DT") );
				loginMember.setStatusCode( rs.getInt("STATUS_CD") );
				loginMember.setGradeCode( rs.getInt("GRADE_CD") );
				
			}
			
			
		}finally {
			// 7) 사용한 JDBC 객체 자원 반환
			
			close(rs); // rs.close();
			close(pstmt); // pstmt.close();
		}
		
		// 8) 결과 반환
		return loginMember;
	}
	
	
	
	

	
	
}