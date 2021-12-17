package edu.kh.semi.board.model.service;

import static edu.kh.semi.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import edu.kh.semi.board.model.dao.ReplyDAO;
import edu.kh.semi.board.model.vo.Reply;
import edu.kh.semi.common.XSS;

public class ReplyService {

	private ReplyDAO dao = new ReplyDAO();

	/** 댓글 조회
	 * @param boardNo
	 * @return rList
	 * @throws Exception
	 */
	public List<Reply> selectReplyList(int boardNo) throws Exception{
		Connection conn = getConnection();
		
		List<Reply> rList = dao.selectReplyList(boardNo, conn);
		
		close(conn);
		
		return rList;
		
	}

	/** 댓글 삽입
	 * @param reply
	 * @return result
	 * @throws Exception
	 */
	public int insertReply(Reply reply) throws Exception{
		Connection conn = getConnection();
		
		// XSS 방지 처리
		reply.setReplyContent( XSS.replaceParameter(reply.getReplyContent())  );
		
		// 개행 문자 처리
		reply.setReplyContent(  reply.getReplyContent().replaceAll("(\r\n|\r|\n|\n\r)", "<br>")   );
		
		int result = dao.insertReply(reply, conn);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);

		close(conn);
		return result;
	}
	
	
	
}
