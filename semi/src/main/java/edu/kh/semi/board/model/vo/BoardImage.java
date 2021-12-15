package edu.kh.semi.board.model.vo;

public class BoardImage {
	private int imgNo;
	private String imgPath;
	private String imgName;
	private String imgOriginal;
	private int imgLevel;
	private int boardNo;
	
	public BoardImage() {
		// TODO Auto-generated constructor stub
	}

	public int getImgNo() {
		return imgNo;
	}

	public void setImgNo(int imgNo) {
		this.imgNo = imgNo;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgOriginal() {
		return imgOriginal;
	}

	public void setImgOriginal(String imgOriginal) {
		this.imgOriginal = imgOriginal;
	}

	public int getImgLevel() {
		return imgLevel;
	}

	public void setImgLevel(int imgLevel) {
		this.imgLevel = imgLevel;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "BoardImage [imgNo=" + imgNo + ", imgPath=" + imgPath + ", imgName=" + imgName + ", imgOriginal="
				+ imgOriginal + ", imgLevel=" + imgLevel + ", boardNo=" + boardNo + "]";
	}
	
	
}
