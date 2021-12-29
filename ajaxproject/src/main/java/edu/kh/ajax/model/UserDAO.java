package edu.kh.ajax.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAO {
	
	private DataSource ds;	// DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool) 이다.
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() { 
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env"); 
			ds = (DataSource)envContext.lookup("jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}
	
	// === 사용한 자원을 반납하는 close() 메소드 생성하기 === //
	private void close() {
		try {
			if(rs != null) {rs.close();	rs=null;}
			if(pstmt != null) {pstmt.close(); pstmt=null;}
			if(conn != null) {conn.close(); conn=null;}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void insertUser(String userName) throws Exception{
		
		try {
			conn = ds.getConnection(); // 서비스가 없어서 DAO에서 conn 얻어오기
			
			String sql = "INSERT INTO TB_USER VALUES(SEQ_USER_NO.NEXTVAL, ?, DEFAULT)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userName);
			
			pstmt.executeUpdate();
			
		}finally {
			close();
		}
		
		
		
	}

	// 사용자 목록 조회
	public List<UserVO> selectUserList() throws Exception {
		
		List<UserVO> userList = new ArrayList<UserVO>(); // 결과 저장용 변수
		
		try {
			String sql = "SELECT USER_NO, USER_NM, TO_CHAR(ENROLL_DT, 'YYYY-MM-DD HH24:MI:SS') ENROLL_DT FROM TB_USER ORDER BY USER_NO DESC";  
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				UserVO user = new UserVO();
				
				user.setUserNo( rs.getInt("USER_NO") );
				user.setUserName( rs.getString("USER_NM") );
				user.setEnrollDate( rs.getString("ENROLL_DT") );
				
				userList.add(user);
			}
			
			
		}finally {
			close();
		}
		
		return userList;
	}
	
	
	
}
