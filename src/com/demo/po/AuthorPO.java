package com.demo.po;

import java.sql.Date;

public class AuthorPO {
	
	private int authorid;
	private AreasPO area;
	private String aname;
	private String bname;
	private int sex;
	private Date birthday;
	private String phone;
	private String adress;
	private int aflag;
	public int getAuthorid() {
		return authorid;
	}
	public void setAuthorid(int authorid) {
		this.authorid = authorid;
	}
	public AreasPO getArea() {
		return area;
	}
	public void setArea(AreasPO area) {
		this.area = area;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public int getAflag() {
		return aflag;
	}
	public void setAflag(int aflag) {
		this.aflag = aflag;
	}
}
