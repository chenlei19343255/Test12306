package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.demo.db.DBUtil;
import com.demo.po.AreasPO;
import com.demo.po.AuthorPO;

import com.demo.po.BookPO;
import com.demo.po.Book_Author;
import com.demo.util.PageUtil;
import com.demo.vo.AuthorSearchVO;

public class AuthorDao {
	public List<AuthorPO> getAuthorList(Connection conn,AuthorSearchVO vo,int start) throws SQLException{
		String sql = "SELECT * FROM AUTHORS a LEFT JOIN areas ar ON (a.`areaid`=ar.`areaid`) where a.aflag=1 ";
		if(vo.getAname() != null && !vo.getAname().isEmpty()){
			sql += "AND a.`aname` LIKE '%"+vo.getAname()+"%'";
		}
		sql += "limit "+start+","+PageUtil.PAGE_ITEMS;
		List<AuthorPO> list = new ArrayList<AuthorPO>();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				AuthorPO po = new AuthorPO();
				AreasPO ap =new AreasPO();
				ap.setAname(rs.getString("arname"));
				po.setArea(ap);
				po.setAuthorid(rs.getInt("authorid"));
				po.setAname(rs.getString("aname"));
				po.setBname(rs.getString("abname"));
				po.setSex(rs.getInt("sex"));
				po.setBirthday(rs.getDate("birthday"));
				po.setAdress(rs.getString("adress"));
				po.setPhone(rs.getString("phone"));
				po.setAflag(rs.getInt("aflag"));
				list.add(po);
			}
		} catch (SQLException e) {
			throw e;
		}
		return list;
		
	}
	public int getAuthorListTotalCount(Connection conn,AuthorSearchVO vo) throws SQLException{
		String sql = "SELECT count(a.authorid) FROM AUTHORS a LEFT JOIN areas ar ON (a.`areaid`=ar.`areaid`) where a.aflag=1 ";
		if(vo.getAname() != null && !vo.getAname().isEmpty()){
			sql += "AND a.`aname` LIKE '%"+vo.getAname()+"%'";
		}
		int count = 0;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw e;
		}
		return count;
	}
	public List<AreasPO> getAreasPOList(Connection conn) throws SQLException{
		String sql = "SELECT * FROM areas";
		List<AreasPO> list = new ArrayList<AreasPO>();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			AreasPO po = null;
			while(rs.next()){
				po = new AreasPO();
				po.setAreaid(rs.getInt(1));
				po.setAname(rs.getString(2));
				po.setAflag(rs.getInt(3));
				list.add(po);
			}
		} catch (SQLException e) {
			throw e;
		}
		return list;
	}
	public void addAuthor(Connection conn,AuthorPO po,int areaid) throws SQLException{
		String sql = "INSERT INTO AUTHORS (areaid,aname,abname,sex,birthday,phone,adress,aflag) VALUES (?,?,?,?,?,?,?,1)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, areaid);
			pst.setString(2, po.getAname());
			pst.setString(3, po.getBname());
			pst.setInt(4, po.getSex());
			pst.setDate(5, po.getBirthday());
			pst.setString(6, po.getPhone());
			pst.setString(7, po.getAdress());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
		
	}
	public int getAreaid(AuthorPO po){
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT areaid FROM areas WHERE arname = ?";
		int areaid = 0;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, po.getArea().getAname());
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				areaid = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return areaid;
	}
	public Book_Author getBook_AuthorPOByAuthorID(Connection conn,int authorid) throws SQLException{
		String sql = "SELECT * FROM AUTHORS a "
				+ "LEFT JOIN areas ar ON (a.`areaid`=ar.`areaid`) "
				+ "LEFT JOIN book_author ba ON (a.`authorid`=ba.`authorid`) "
				+ "LEFT JOIN books b ON (ba.`bookid`=b.`bookid`) "
				+ "LEFT JOIN publisher p ON (b.`pubid`=p.`pubid`) "
				+ "WHERE a.`authorid`=? ";
		
		Book_Author po = null;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, authorid);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				po = new Book_Author();
				BookPO bp = new BookPO();
				bp.setBname(rs.getString("bname"));
				AreasPO arp = new AreasPO();
				arp.setAname(rs.getString("arname"));
				AuthorPO ap = new AuthorPO();
				ap.setArea(arp);
				ap.setAname(rs.getString("aname"));
				ap.setBname(rs.getString("abname"));
				ap.setSex(rs.getInt("sex"));
				ap.setBirthday(rs.getDate("birthday"));
				ap.setPhone(rs.getString("phone"));
				ap.setAdress(rs.getString("adress"));
				po.setAuthor(ap);
				po.setBook(bp);
				
			}
		} catch (SQLException e) {
			throw e;
		}
		return po;
	}
	public void updateAuthorByAuthorid(Connection conn,AuthorPO po,int areaid) throws SQLException{
		String sql = "UPDATE AUTHORS SET areaid=?,aname=?,abname=?,sex=?,birthday=?,phone=?,adress=? WHERE authorid=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, areaid);
			pst.setString(2, po.getAname());
			pst.setString(3, po.getBname());
			pst.setInt(4, po.getSex());
			pst.setDate(5, po.getBirthday());
			pst.setString(6, po.getPhone());
			pst.setString(7, po.getAdress());
			pst.setInt(8, po.getAuthorid());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}
	public void delAuthorByAuthorid(Connection conn,int authorid) throws SQLException{
		String sql = "update authors set aflag = 0 where authorid = ?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, authorid);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
		
	}
	public List<String> getBookNameListByAuthorid(Connection conn,int authorid) throws SQLException{
		String sql = "SELECT b.`bname` FROM AUTHORS a "
				+ "LEFT JOIN book_author  ba ON a.`authorid`=ba.`authorid`"
				+ " LEFT JOIN books b ON ba.`bookid`=b.`bookid` "
				+ "WHERE a.`authorid` = ? ";
		List<String> list = new ArrayList<String>();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, authorid);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				String bname = rs.getString(1);
				list.add(bname);
			}
		} catch (SQLException e) {
			throw e;
		}
		return list;
	}
}
