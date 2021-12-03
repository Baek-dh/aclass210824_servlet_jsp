package edu.kh.practice.model;

import java.sql.Date;

public class Book {
	private String bookTitle;
	private String bookAuthor;
	private int bookPrice;
	private int sale;
	private Date registerDate;
	
	public Book() {	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getSale() {
		return sale;
	}

	public void setSale(int sale) {
		this.sale = sale;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@Override
	public String toString() {
		return "Book [bookTitle=" + bookTitle + ", bookAuthor=" + bookAuthor + ", bookPrice=" + bookPrice + ", sale="
				+ sale + ", registerDate=" + registerDate + "]";
	}
	
	
}
