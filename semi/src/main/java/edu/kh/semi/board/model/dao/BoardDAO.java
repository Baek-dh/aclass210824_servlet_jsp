package edu.kh.semi.board.model.dao;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.semi.board.model.vo.Board;
import edu.kh.semi.board.model.vo.BoardImage;
import edu.kh.semi.board.model.vo.Category;
import edu.kh.semi.board.model.vo.Pagination;

public class BoardDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop;
	
	public BoardDAO() {
		String filePath = BoardDAO.class.getResource("/edu/kh/semi/sql/board-query.xml").getPath();                    
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream(filePath));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	/** 전체 게시글 수 조회
	 * @param conn
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn) throws Exception{
		
		int listCount = 0;
		
		try {

			String sql = prop.getProperty("getListCount");
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}



	/** 게시글 목록 조회
	 * @param pagination
	 * @param conn
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Pagination pagination, Connection conn) throws Exception{

		List<Board> boardList = new ArrayList<Board>();
		
		try {
			String sql = prop.getProperty("selectBoardList");

			// 조회 하려는 범위의 시작/끝 행 계산
			int startRow = (pagination.getCurrentPage() - 1) * pagination.getLimit() + 1  ;
			int endRow = startRow + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				
				// 게시글 번호, 제목, 작성자명, 조회수, 카테고리명, 작성일, 게시글 상태명
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setMemberName(rs.getString("MEMBER_NM"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setCategoryName(rs.getString("CATEGORY_NM"));
				board.setBoardStatusName(rs.getString("BOARD_STATUS_NM"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				
				// 리스트에 담기
				boardList.add(board);
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return boardList;
	}



	/** 게시글 상세 조회
	 * @param boardNo
	 * @param conn
	 * @return board(없으면 null)
	 * @throws Exception
	 */
	public Board selectBoard(int boardNo, Connection conn) throws Exception {
		
		Board board = null;
		
		try {
			String sql = prop.getProperty("selectBoard");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				
				board.setBoardNo(rs.getInt("BOARD_NO"));
				board.setBoardTitle(rs.getString("BOARD_TITLE"));
				board.setBoardContent(rs.getString("BOARD_CONTENT"));
				board.setMemberNo(rs.getInt("MEMBER_NO"));
				board.setMemberName(rs.getString("MEMBER_NM"));
				board.setCreateDate(rs.getString("CREATE_DT"));
				board.setModifyDate(rs.getString("MODIFY_DT"));
				board.setCategoryCode(rs.getInt("CATEGORY_CD"));
				board.setCategoryName(rs.getString("CATEGORY_NM"));
				board.setReadCount(rs.getInt("READ_COUNT"));
				board.setBoardStatusName(rs.getString("BOARD_STATUS_NM"));
				
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return board;
	}



	/** 조회수 증가
	 * @param boardNo
	 * @param conn 
	 * @return result(1 성공, 0 실패)
	 * @throws Exception
	 */
	public int increaseReadCount(int boardNo, Connection conn) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("increaseReadCount");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	/** 카테고리 조회
	 * @param conn
	 * @return category
	 * @throws Exception
	 */
	public List<Category> selectCategory(Connection conn) throws Exception{

		List<Category> category = new ArrayList<Category>();
		
		try {
			
			String sql = prop.getProperty("selectCategory");
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Category c = new Category();
				c.setCategoryCode(rs.getInt(1));
				c.setCategoryName(rs.getString(2));
				
				category.add(c);
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return category;
	}



	/** 다음 게시글 번호 조회
	 * @param conn
	 * @return boardNo
	 * @throws Exception
	 */
	public int nextBoardNo(Connection conn) throws Exception{
		
		int boardNo = 0;
		
		try {
			String sql = prop.getProperty("nextBoardNo");
		
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardNo = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return boardNo;
	}



	/** 게시글 삽입
	 * @param board
	 * @param conn 
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Board board, Connection conn) throws Exception{

		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board.getBoardNo());
			pstmt.setString(2, board.getBoardTitle());
			pstmt.setString(3, board.getBoardContent());
			pstmt.setInt(4, board.getMemberNo());
			pstmt.setInt(5, board.getCategoryCode());
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}



	/** 게시글 이미지 정보 삽입
	 * @param img
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int insertBoardImage(BoardImage img, Connection conn) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("insertBoardImage");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, img.getImgPath());
			pstmt.setString(2, img.getImgName());
			pstmt.setString(3, img.getImgOriginal());
			pstmt.setInt(4, img.getImgLevel());
			pstmt.setInt(5, img.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}



	/** 특정 게시글 이미지 목록 조회
	 * @param boardNo
	 * @param conn
	 * @return imgList
	 * @throws Exception
	 */
	public List<BoardImage> selectBoardImageList(int boardNo, Connection conn) throws Exception{
		List<BoardImage> imgList = new ArrayList<BoardImage>();
		
		try{
			String sql = prop.getProperty("selectBoardImageList");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardImage img = new BoardImage();
				
				img.setImgNo(rs.getInt(1));
				img.setImgPath(rs.getString(2));
				img.setImgName(rs.getString(3));
				img.setImgOriginal(rs.getString(4));
				img.setImgLevel(rs.getInt(5));
				img.setBoardNo(boardNo);
				
				// img를 imgList에 추가
				imgList.add(img);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		
		return imgList;
	}



	/** 게시글 수정
	 * @param board
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Board board, Connection conn) throws Exception {

		int result = 0;
		
		try {
			String sql = prop.getProperty("updateBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getBoardTitle());
			pstmt.setString(2, board.getBoardContent());
			pstmt.setInt(3, board.getCategoryCode());
			pstmt.setInt(4, board.getBoardNo());
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}



	/** 게시글 이미지 수정
	 * @param img
	 * @param conn
	 * @return result
	 * @throws Exception
	 */
	public int updateBoardImage(BoardImage img, Connection conn) throws Exception{
		
		int result = 0;
		
		try {
			String sql = prop.getProperty("updateBoardImage");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, img.getImgName());
			pstmt.setString(2, img.getImgOriginal());
			pstmt.setInt(3, img.getImgLevel());
			pstmt.setInt(4, img.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
	
	
	
}
