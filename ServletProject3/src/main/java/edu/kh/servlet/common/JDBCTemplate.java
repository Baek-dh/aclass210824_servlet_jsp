package edu.kh.servlet.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	/* DB 연결, JDBC 자원 반환 등의 JDBC관련 공통 내용을 모아둔 클래스
	 * getConnection() : 커넥션을 반환하는 메소드
	 * close(stmt | pstmt | rs | conn) : 자원 반환 메소드
	 * commit() / rollback() : 트랜잭션 처리용 메소드
	 * */
	
	private static Connection conn = null;
	
	// DB 연결을 위한 커넥션 반환 메소드
	public static Connection getConnection() {
		
		try {
			
			if( conn == null || conn.isClosed() ) { 
				// isClosed() : 자원이 반환되어 있으면 true, 아니면 false
				
				
				// 외부 파일에서 정보를 읽어와 이를 저장할 Properties 객체 생성
				Properties prop = new Properties();
				// Properties :  K, V가 String으로 제한된 Map
				// --> 파일 입출력에 특화되어 있음.

				
				String filePath = JDBCTemplate.class.getResource("/edu/kh/servlet/sql/driver.xml").getPath();       
				
				// driver.xml 파일을 읽어와서 prop에 저장
				prop.loadFromXML( new FileInputStream(filePath) );
				
				// 중간 확인
				//System.out.println(prop);
				
				
				// 새로운 커넥션 생성
				Class.forName( prop.getProperty("driver") );
				String url = prop.getProperty("url");
				String user = prop.getProperty("user");
				String pass = prop.getProperty("pass");
				conn = DriverManager.getConnection(url, user, pass);
				
				conn.setAutoCommit(false); // SQL 구문 수행 후 자동 Commit 기능 끔
				
				// 드라이버, url, 아이디, 비밀번호는 바뀔 가능성이 많아서
				// 바뀔 때 마다 자바 코드를 다시 컴파일 하는 것은 너무 비효율적임.
				// --> 외부 파일에 내용을 작성해 읽어오는 방법으로 문제 해결 가능
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	// Connection 반환 메소드
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				// conn이 참조하는 Connection 객체가 있고
				// 그 객체가 반환되지 않았을 때 
				conn.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	// Statement 반환 메소드 + (다형성을 이용하여 PreparedStatement도 같이 반환 가능)
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// ResultSet 반환 메소드 
	public static void close(ResultSet rs) {
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// commit용 메소드
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				// conn이 참조하는 Connection 객체가 있고
				// 그 객체가 반환되지 않았을 때 
				conn.commit();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// rollback용 메소드
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				// conn이 참조하는 Connection 객체가 있고
				// 그 객체가 반환되지 않았을 때 
				conn.rollback();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}





