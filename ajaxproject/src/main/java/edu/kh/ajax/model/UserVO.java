package edu.kh.ajax.model;

public class UserVO {
	private int userNo;
	private String userName;
	private String enrollDate;
	
	public UserVO() {}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "UserVO [userNo=" + userNo + ", userName=" + userName + ", enrollDate=" + enrollDate + "]";
	}
	
	
}
