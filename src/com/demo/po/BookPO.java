package com.demo.po;

import java.sql.Date;

public class BookPO {
	
	private int bookid;
	private PublisherPO publish;
	private String bname;
	private String border;
	private int bnum;
	private Date pdate;
	private int cnum;
	private double price;
	private String pubnum;
	private int bflag;
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public PublisherPO getPublish() {
		return publish;
	}
	public void setPublish(PublisherPO publish) {
		this.publish = publish;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getBorder() {
		return border;
	}
	public void setBorder(String border) {
		this.border = border;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPubnum() {
		return pubnum;
	}
	public void setPubnum(String pubnum) {
		this.pubnum = pubnum;
	}
	public int getBflag() {
		return bflag;
	}
	public void setBflag(int bflag) {
		this.bflag = bflag;
	}
}
