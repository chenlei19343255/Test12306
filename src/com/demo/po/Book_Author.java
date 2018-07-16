package com.demo.po;

public class Book_Author {
	
	private int baid;
	private BookPO book;
	private AuthorPO author;
	private AuthorTypePO type;
	
	public int getBaid() {
		return baid;
	}
	public void setBaid(int baid) {
		this.baid = baid;
	}
	public BookPO getBook() {
		return book;
	}
	public void setBook(BookPO book) {
		this.book = book;
	}
	public AuthorPO getAuthor() {
		return author;
	}
	public void setAuthor(AuthorPO author) {
		this.author = author;
	}
	public AuthorTypePO getType() {
		return type;
	}
	public void setType(AuthorTypePO type) {
		this.type = type;
	}
}
