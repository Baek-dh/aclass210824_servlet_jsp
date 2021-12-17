package edu.kh.semi.board.model.vo;

public class Reply {
	private int replyNo;
	private String replyContent;
	private String replyCreateDate;
	private int boardNo;
	private int memberNo;
	private String memberName;
	private int replyStatusCode;
	private String replyStatusName;
	
	public Reply() {
		// TODO Auto-generated constructor stub
	}

	public int getReplyNo() {
		return replyNo;
	}

	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getReplyCreateDate() {
		return replyCreateDate;
	}

	public void setReplyCreateDate(String replyCreateDate) {
		this.replyCreateDate = replyCreateDate;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getReplyStatusCode() {
		return replyStatusCode;
	}

	public void setReplyStatusCode(int replyStatusCode) {
		this.replyStatusCode = replyStatusCode;
	}

	public String getReplyStatusName() {
		return replyStatusName;
	}

	public void setReplyStatusName(String replyStatusName) {
		this.replyStatusName = replyStatusName;
	}

	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", replyContent=" + replyContent + ", replyCreateDate=" + replyCreateDate
				+ ", boardNo=" + boardNo + ", memberNo=" + memberNo + ", memberName=" + memberName
				+ ", replyStatusCode=" + replyStatusCode + ", replyStatusName=" + replyStatusName + "]";
	}
	
	
}
