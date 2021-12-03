package edu.kh.practice.model;

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

public class BookDAO {
	private DataSource ds; // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool) 이다.
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	// DBCP
	public BookDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}

			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}

			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	/** 책 등록 DAO
	 * @param book
	 * @return result (1 성공)
	 * @throws SQLException
	 */
	public int bookRegister(Book book) throws SQLException{
		
		// 결과 저장용 변수 선언
		int result = 0;
		
		try {
			String sql = "INSERT INTO BOOK VALUES(?,?,?,?, DEFAULT)";
			
			// ** 커넥션을 얻어오기
			conn = ds.getConnection();
			
			// pstmt 생성 후 sql 적재
			pstmt = conn.prepareStatement(sql);
			
			// 위치 홀더에 알맞은 값 세팅
			pstmt.setString(1, book.getBookTitle());
			pstmt.setString(2, book.getBookAuthor());
			pstmt.setInt(3, book.getBookPrice());
			pstmt.setInt(4, book.getSale());
			
			// SQL 수행 후 결과 반환 받기
			result = pstmt.executeUpdate();

			// 자동커밋 관련 설정이 없으므로
			// SQL 수행 후 자동적으로 커밋된다 
			// 그래서 별도의 트랜잭션 제어 처리가 필요없다.
			
			
		}finally {
			
			close();
			
		}
		
		// 결과 반환
		return result;
	}

	
	
	
	
	/** 책 정보 모두 조회
	 * @return bookList
	 * @throws Exception
	 */
	public List<Book> selectBookList() throws Exception {
		
		// 결과 저장용 변수 선언
		List<Book> bookList = new ArrayList<Book>();
		
		try {
			// SQL
			String sql = "SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_PRICE, SALE FROM BOOK";
			
			// 커넥션 얻어오기
			conn = ds.getConnection();
			
			// pstmt 생성 후 SQL 적재
			pstmt = conn.prepareStatement(sql);
			
			// SQL 수행 후 결과 반환 받기
			rs = pstmt.executeQuery();
			
			// while문을 이용하여 조회 결과 1행씩 접근
			while( rs.next() ) {
				
				Book book = new Book();
				
				book.setBookTitle( rs.getString("BOOK_TITLE")  );
				book.setBookAuthor( rs.getString("BOOK_AUTHOR")  );
				book.setBookPrice( rs.getInt("BOOK_PRICE")  );
				book.setSale( rs.getInt("SALE")  );
				
				// list에 추가
				bookList.add(book);
			}
			
			
		}finally {
			close();
			
		}
		
		return bookList;
	}
	
	
	
}
