package edu.kh.semi.member.model.dao;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
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


	/** 회원 가입 DAO
	 * @param member
	 * @param conn
	 * @return result(1 성공)
	 * @throws Exception
	 */
	public int signUp(Member member, Connection conn) throws Exception{
		
		// 1) 결과 저장용 변수 선언
		int result = 0;
		
		try {
			// 2) SQL 얻어오기
			String sql = prop.getProperty("signUp");
			
			// 3) pstmt 객체 생성 및 sql 적재
			pstmt = conn.prepareStatement(sql);
			
			// 4) 위치홀더에 알맞은 값 세팅
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberPhone());
			pstmt.setString(5, member.getMemberEmail());
			pstmt.setString(6, member.getMemberAddress());
			
			// 5) SQL 수행 후 결과 반환 받기
			result = pstmt.executeUpdate();
			
		}finally {
			// 6) 사용한 JDBC 자원 반환
			close(pstmt);
		}
		
		// 7) 결과 반환
		return result;
	}


	/** 아이디 중복 확인
	 * @param inputId
	 * @param conn
	 * @return result (1 중복, 0 사용 가능)
	 * @throws Exception
	 */
	public int idDupCheck(String inputId, Connection conn) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("idDupCheck");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, inputId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}


	/** 이메일 중복 체크
	 * @param inputEmail
	 * @param conn
	 * @return result(1 중복, 0 사용 가능)
	 * @throws Exception
	 */
	public int emailDupCheck(String inputEmail, Connection conn) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("emailDupCheck");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputEmail);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}


	 
	/** 아이디로 회원 정보 검색
	 * @param inputId
	 * @param conn
	 * @return member
	 * @throws Exception
	 */
	public Member idSearch(String inputId, Connection conn) throws Exception {
		
		Member member = null;
		
		try {
			String sql = prop.getProperty("idSearch");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				member = new Member();
				
				// 매개변수로 전달 받은 ID를 member에 세팅
				member.setMemberId(inputId);
				
				member.setMemberName( rs.getString(1) );
				member.setMemberPhone( rs.getString(2) );
				member.setMemberEmail( rs.getString(3) );
				member.setMemberAddress( rs.getString(4) );
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return member;
	}
	
	
	/** 회원 정보 수정 DAO
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int update(Connection conn, Member member) throws Exception{
		
		int result = 0; // 결과 저장용 변수 선언
		
		try {
			
			// SQL 얻어오기
			String sql = prop.getProperty("update");
			
			// pstmt 얻어오고 SQL 적재
			pstmt = conn.prepareStatement(sql);
			
			// 위치 홀더에 알맞은 값 세팅
			pstmt.setString(1, member.getMemberEmail());
			pstmt.setString(2, member.getMemberPhone());
			pstmt.setString(3, member.getMemberAddress());
			pstmt.setInt(4, member.getMemberNo());
			
			// SQL 수행
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt); // 사용한 JDBC 객체 자원 반환
		}
		
		// 결과 반환
		return result;
	}

	

	/** 비밀번호 변경
	 * @param currentPw
	 * @param newPw1
	 * @param memberNo
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(String currentPw, String newPw1, int memberNo, Connection conn) throws Exception{
		
		int result = 0;
		try {
			String sql = prop.getProperty("updatePw");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPw1);
			pstmt.setString(2, currentPw);
			pstmt.setInt(3, memberNo);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}


	/** 회원 탈퇴
	 * @param map
	 * @param conn
	 * @return result (1 성공, 0 비밀번호 불일치)
	 * @throws Exception
	 */
	public int secession(Map<String, String> map, Connection conn) throws Exception{
		
		// 결과 반환용 변수 선언
		int result = 0;
	
		try {
			// SQL 얻어오기
			String sql = prop.getProperty("secession");
			
			// pstmt 얻어오기 + SQL 적재
			pstmt = conn.prepareStatement(sql);
			
			// 위치 홀더에 알맞은 값 세팅
			//pstmt.setInt( 1,  Integer.parseInt( map.get("memberNo") ) );
			
			// -> 숫자만 작성된 문자열 같은 경우
			//    DBMS에서 숫자로 인식할 수 있으므로 
			//    꼭 int형 파싱을 할 필요가 없다.
			
			pstmt.setString(1, map.get("memberNo") );
			pstmt.setString(2, map.get("currentPw") );
			
			
			// SQL 수행 후 결과 반환 받기
			result = pstmt.executeUpdate();
			
		}finally {
			// 사용한 JDBC 객체 자원 반환
			close(pstmt);
			
		}
		
		// 결과 반환
		return result;
	}
	
	
	

	
	
}
