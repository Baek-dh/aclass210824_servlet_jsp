package edu.kh.semi.board.model.service;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.semi.board.model.dao.BoardDAO;
import edu.kh.semi.board.model.vo.Board;
import edu.kh.semi.board.model.vo.Category;
import edu.kh.semi.board.model.vo.Pagination;

public class BoardService {

	private BoardDAO dao = new BoardDAO();

	
	
	/** 페이징 처리용 객체 생성
	 * @param cp
	 * @return pagination
	 * @throws Exception
	 */
	public Pagination getPagination(int cp) throws Exception{
		
		Connection conn = getConnection();
		
		// 전체 게시글 수 조회 DAO 호출
		int listCount = dao.getListCount(conn);
		
		close(conn);
		
		// 전체 게시글 수 + 현패 페이지를 이용하여 
		// 페이징 처리 관련 값 생성
		return new Pagination(listCount, cp);
	}



	/** 게시글 목록 조회
	 * @param pagination
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Pagination pagination) throws Exception {
		
		Connection conn = getConnection();
		
		List<Board> boardList = dao.selectBoardList(pagination, conn);
		
		close(conn);
		
		return boardList;
	}



	/** 게시글 상세 조회
	 * @param boardNo
	 * @param memberNo 
	 * @return board(없으면 null)
	 * @throws Exception
	 */
	public Board selectBoard(int boardNo, int memberNo) throws Exception{
		
		Connection conn = getConnection();

		Board board = dao.selectBoard(boardNo, conn);
		
		// 조회된 게시글이 있고, 해당 게시글의 작성자와 로그인된 회원이 같지 않으면
		// 조회수 증가
		if(board != null && board.getMemberNo() != memberNo ) {
			
			int result = dao.increaseReadCount(boardNo, conn);
			
			if(result > 0) {	
				commit(conn);
				
				// 먼저 조회되었던 게시글 정보에서 조회수를 1증가
				board.setReadCount( board.getReadCount() + 1  );
			}
			
			else			rollback(conn);
			
		}
		
		close(conn);
		
		return board;
		
		
	}



	/** 카테고리 조회
	 * @return category
	 * @throws Exception
	 */
	public List<Category> selectCategory() throws Exception{
		Connection conn = getConnection();
		
		List<Category> category = dao.selectCategory(conn);
		
		close(conn);
		
		return category;
	}
	
	
	
	
	
	
	
}
