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

	/** 댓글 수정
	 * @param replyNo
	 * @param replyContent
	 * @return result
	 * @throws Exception
	 */
	public int updateReply(int replyNo, String replyContent) throws Exception{
		
		Connection conn = getConnection();

		// 개행문자, XSS 처리 
		replyContent = XSS.replaceParameter(replyContent);
		replyContent = replyContent.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
		
		
		int result= dao.updateReply(replyNo, replyContent, conn);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	
	/** 댓글 삭제
	 * @param replyNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteReply(int replyNo) throws Exception {
		Connection conn = getConnection();
		
		int result= dao.deleteReply(replyNo, conn);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	
	
}
