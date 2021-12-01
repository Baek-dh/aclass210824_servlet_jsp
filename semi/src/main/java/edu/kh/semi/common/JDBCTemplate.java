package edu.kh.semi.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
			
			// Connection Pool
			// 미리 DB와 연결되어있는 Connection 객체를 일정 개수 이상 만들어 두고
			// 요청 시 만들어둔 Connection을 빌려주고
			// 요청 완료 시 다시 반환 받아오는 방법.
			
			// 항상 일정 개수 이상의 Connection 객체가 존재
			// 요청이 많을 경우 지정된 범위 내에서 추가적인 Connection 객체 생성할 수 있음.
			// Connection 개수에 제한이 있기 때문에 DB에 과도한 요청을 보내는 경우를 방지
			
			
		      // JNDI(Java Naming and Directory Interface API)
		      /*디렉터리 서비스에 접근하는데 사용하는 API
		      어플리케이션은 JNDI를 사용하여 서버의 resource를 찾는다.
		      특히 JDBC resource를 data source라고 부른다.
		      
		      Resource를 서버에 등록할 때 고유한 JNDI 이름을 붙이는데, JNDI 이름은 디렉터리 경로 형태를 가진다.
		      예를 들어 data source의 JNDI 이름은 'jdbc/mydb' 형식으로 짓는다.
		      
		       서버에서 'jdbc/oracle'라는 DataSource를 찾으려면 
		      'java:comp/env/jdbc/oracle'라는 JNDI 이름으로 찾아야 한다. 
		      즉 lookup() 메소드에 'java:comp/env/jdbc/oracle'를 인자값으로 넘긴다.
		      
		      */

			
			
	         // Servers에 존재하는 context.xml 파일을 찾는 작업
	         Context initContext = new InitialContext();
	         Context envContext  = (Context)initContext.lookup("java:/comp/env");  // java:comp/env   응용 프로그램 환경 항목
	         
	         // context.xml 파일에서 name이 "jdbc/oracle"인 DataSource를 얻어옴
	         // DataSource : DriverManager를 대체하는 객체로 
	         // Connection 생성, Connectoin pool을 구현하는 객체
	         DataSource ds = (DataSource)envContext.lookup("jdbc/oracle");

	         conn = ds.getConnection(); // DataSource에 의해 미리 만들어진 Connection 중 하나를 얻어옴.
	         conn.setAutoCommit(false);

			
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
