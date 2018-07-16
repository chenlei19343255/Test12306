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
import com.demo.po.AuthorTypePO;
import com.demo.po.BookAuthorTypePO;
import com.demo.po.BookPO;
import com.demo.po.Book_Author;
import com.demo.po.PublisherPO;
import com.demo.po.UserinfoPO;
import com.demo.util.PageUtil;
import com.demo.vo.BookSearchVO;
import com.demo.vo.UserSearchVO;

public class BookDAO {
	//登录验证
	public UserinfoPO Login(Connection conn,String lname,String lpass) throws SQLException{
		String sql="select * from userinfo where lname = ? and lpass = ?";
		 
		UserinfoPO po =null;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, lname);
			pst.setString(2, lpass);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				po = new UserinfoPO();
				po.setUserid(rs.getInt(1));
				po.setLname(rs.getString(2));
				po.setLpass(rs.getString(3));
				po.setUname(rs.getString(4));
				po.setUflag(rs.getInt(5));
				po.setPower(rs.getInt(6));
			}
		} catch (SQLException e) {
			throw e;
		}
		return po;
	}
	public List<UserinfoPO> getUserList(Connection conn,UserSearchVO vo,int start) throws SQLException{
		String sql = "SELECT * FROM userinfo where uflag = 1 ";
		if(vo.getUname() != null && !vo.getUname().isEmpty()){
			sql += "and uname LIKE '%"+vo.getUname()+"%'";
		}
		sql += "limit "+start+" ,"+PageUtil.PAGE_ITEMS;
		List<UserinfoPO> list = new ArrayList<UserinfoPO>();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				UserinfoPO po = new UserinfoPO();
				po.setUserid(rs.getInt(1));
				po.setLname(rs.getString(2));
				po.setLpass(rs.getString(3));
				po.setUname(rs.getString(4));
				po.setUflag(rs.getInt(5));
				po.setPower(rs.getInt(6));
				list.add(po);
				
			}
		} catch (SQLException e) {
			throw e;
		}
		return list;
	}
	public int getUserTotalCount(Connection conn,UserSearchVO vo) throws SQLException{
		String sql = "SELECT COUNT(userid) FROM userinfo WHERE uflag = 1 ";
		if(vo.getUname() != null && !vo.getUname().isEmpty()){
			sql += "and uname LIKE '%"+vo.getUname()+"%'";
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
	
	
	
	
	//书籍列表，条件分页查询
	public List<Book_Author> getBookList(Connection conn,BookSearchVO vo,int start) throws SQLException{
		
		String sql = "SELECT * FROM books b "
				+ "LEFT JOIN publisher p ON (b.`pubid`=p.`pubid`) "
				+ "LEFT JOIN book_author  ba ON (b.`bookid`=ba.`bookid`) "
				+ "LEFT JOIN authortype aut ON (ba.`atid`=aut.`atid`) "
				+ "LEFT JOIN AUTHORS a ON (ba.`authorid`=a.`authorid`) where aut.atid=1 and b.bflag=1 ";
		if(vo.getAname() != null && !vo.getAname().isEmpty()){
			sql += "AND a.`aname` LIKE '%"+vo.getAname()+"%' ";
		}
		if(vo.getBname() != null && !vo.getBname().isEmpty()){
			sql += "AND b.`bname` LIKE '%"+vo.getBname()+"%' ";
		}
		if(vo.getPname() != null && !vo.getPname().isEmpty()){
			if(vo.getPname().equals("全部")){
				
			}else{
				sql += "AND p.`pname` = '"+vo.getPname()+"'";
			}
			
		}
		sql += "limit "+start+", "+PageUtil.PAGE_ITEMS;
		
		List<Book_Author> list = new ArrayList<Book_Author>();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				Book_Author ba =new Book_Author();
				BookPO bp = new BookPO();
				PublisherPO pp = new PublisherPO();
				bp.setBookid(rs.getInt("bookid"));
				bp.setBname(rs.getString("bname"));
				bp.setBorder(rs.getString("porder"));
				bp.setBnum(rs.getInt("bnum"));
				bp.setPdate(rs.getDate("pdate"));
				bp.setCnum(rs.getInt("cnum"));
				bp.setPrice(rs.getDouble("price"));
				bp.setPubnum(rs.getString("pubnum"));
				bp.setBflag(rs.getInt("bflag"));
				pp.setPubid(rs.getInt("pubid"));
				pp.setPname(rs.getString("pname"));
				pp.setSname(rs.getString("sname"));
				pp.setTel(rs.getString("tel"));
				pp.setAdress(rs.getString("adress"));
				pp.setPflag(rs.getInt("pflag"));
				bp.setPublish(pp);
				AuthorPO ap = new AuthorPO();
				ap.setAuthorid(rs.getInt("authorid"));
				ap.setAname(rs.getString("aname"));
				ap.setBname(rs.getString("bname"));
				ap.setSex(rs.getInt("sex"));
				ap.setBirthday(rs.getDate("birthday"));
				ap.setPhone(rs.getString("phone"));
				ap.setAdress(rs.getString("adress"));
				ap.setAflag(rs.getInt("aflag"));
				AuthorTypePO at = new AuthorTypePO();
				at.setAtid(rs.getInt("atid"));
				at.setTname(rs.getString("tname"));
				ba.setAuthor(ap);
				ba.setBook(bp);
				ba.setType(at);
				list.add(ba);
			}
		} catch (SQLException e) {
			throw e;
		}
		
		return list;
	}
	//获取查询书籍总条数
	public int getBookListTotalCount(Connection conn,BookSearchVO vo) throws SQLException{
		String sql = "SELECT count(b.bookid) FROM books b "
				+ "LEFT JOIN publisher p ON (b.`pubid`=p.`pubid`) "
				+ "LEFT JOIN book_author  ba ON (b.`bookid`=ba.`bookid`) "
				+ "LEFT JOIN authortype aut ON (ba.`atid`=aut.`atid`) "
				+ "LEFT JOIN AUTHORS a ON (ba.`authorid`=a.`authorid`) where b.bflag=1 ";
		if(vo.getAname() != null && !vo.getAname().isEmpty()){
			sql += "AND a.`aname` LIKE '%"+vo.getAname()+"%' ";
		}
		if(vo.getBname() != null && !vo.getBname().isEmpty()){
			sql += "AND b.`bname` LIKE '%"+vo.getBname()+"%' ";
		}
		if(vo.getPname() != null && !vo.getPname().isEmpty()){
			if(vo.getPname().equals("全部")){
				
			}else{
				sql += "AND p.`pname` = '"+vo.getPname()+"'";
			}
			
		}
		/*
		if(vo.getPname() != null && !vo.getPname().isEmpty()){
			sql += "AND p.`pname` = '"+vo.getPname()+"'";
		}*/
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
	
	//获取作者集合
	public List<AuthorPO> getAList(Connection conn) throws SQLException{
		String sql = "SELECT * FROM AUTHORS a LEFT JOIN areas ar ON (a.`areaid`=ar.`areaid`) ";
		
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
	//获取出版社集合
	public List<PublisherPO> getPList(Connection conn) throws SQLException{
		String sql = "SELECT * FROM publisher p LEFT JOIN areas a ON (p.`areaid`=a.`areaid`) WHERE 1=1 ";
		List<PublisherPO> list = new ArrayList<PublisherPO>();
		 
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				PublisherPO po = new PublisherPO();
				AreasPO ap = new AreasPO();
				ap.setAname(rs.getString("arname"));
				po.setArea(ap);
				po.setPubid(rs.getInt("pubid"));
				po.setPname(rs.getString("pname"));
				po.setSname(rs.getString("sname"));
				po.setTel(rs.getString("tel"));
				po.setAdress(rs.getString("adress"));
				po.setPflag(rs.getInt("pflag"));
				list.add(po);
			}
		} catch (SQLException e) {
			throw e;
		}
		return list;
	} 
	//根据新增书籍作者名获取作者ID
	public int getAuthorId(Book_Author po){
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT authorid FROM AUTHORS WHERE aname = ? ";
		int authorid = 0;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, po.getAuthor().getAname());
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				authorid = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		
		return authorid;
	}
	//获取新增书籍出版社ID
	public int getPubId(Book_Author po){
		Connection conn = DBUtil.getConnection();
		String sql = "SELECT pubid FROM publisher WHERE pname = ? ";
		 int pubid = 0;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, po.getBook().getPublish().getPname());
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				pubid = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		
		return pubid;
	}
	
	//新增书籍
	public void addBook(Connection conn, Book_Author po,int pubid) throws SQLException{
		String sql = "INSERT INTO books (pubid,bname,porder,bnum,pdate,cnum,price,pubnum,bflag) VALUES(?,?,?,?,?,?,?,?,1)";
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pubid);
			pst.setString(2, po.getBook().getBname());
			pst.setString(3, po.getBook().getBorder());
			pst.setInt(4, po.getBook().getBnum());
			pst.setDate(5, po.getBook().getPdate());
			pst.setInt(6, po.getBook().getCnum());
			pst.setDouble(7, po.getBook().getPrice());
			pst.setString(8, po.getBook().getPubnum());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
		
		return;
	}
	//获取新增书籍ID
	public int getBookId(Connection conn,Book_Author po) throws SQLException{
		
		String sql = "SELECT bookid FROM books WHERE bname = ? ";
		 int bookid = 0;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, po.getBook().getBname());
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				bookid = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw e;
		}
		return bookid;
	}
	//新增书籍作者表
	public void addBook_Author(Connection conn,Book_Author po,int authorid,int bookid) throws SQLException{
		String sql = "INSERT INTO book_author (bookid,authorid,atid) VALUES(?,?,1)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, bookid);
			pst.setInt(2, authorid);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
		return;
	}
	//根据书籍ID获取书籍对象
	public Book_Author getBookAuthorPOById(Connection conn,int bookid) throws SQLException{
		String sql = "SELECT * FROM books b "
				+ "LEFT JOIN publisher p ON (b.`pubid`=p.`pubid`) "
				+ "LEFT JOIN book_author  ba ON (b.`bookid`=ba.`bookid`) "
				+ "LEFT JOIN authortype aut ON (ba.`atid`=aut.`atid`) "
				+ "LEFT JOIN AUTHORS a ON (ba.`authorid`=a.`authorid`) where b.bookid=? and aut.atid=1 ";
		Book_Author po = null;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, bookid);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				po = new Book_Author();
				AuthorPO ap = new AuthorPO();
				ap.setAname(rs.getString("aname"));
				PublisherPO pp = new PublisherPO();
				pp.setPname(rs.getString("pname"));
				BookPO bp = new BookPO();
				bp.setPublish(pp);
				bp.setBookid(bookid);
				bp.setBname(rs.getString("bname"));
				bp.setBnum(rs.getInt("bnum"));
				bp.setBflag(rs.getInt("bflag"));
				bp.setBorder(rs.getString("porder"));
				bp.setCnum(rs.getInt("cnum"));
				bp.setPdate(rs.getDate("pdate"));
				bp.setPrice(rs.getDouble("price"));
				bp.setPubnum(rs.getString("pubnum"));
				AuthorTypePO atp = new AuthorTypePO();
				atp.setAtid(rs.getInt("atid"));
				atp.setTname(rs.getString("tname"));
				po.setAuthor(ap);
				po.setBook(bp);
				po.setType(atp);
			}
			
		} catch (SQLException e) {
			throw e;
		}
		return po;
	}
	public void updateBookById(Connection conn,Book_Author po,int pubid) throws SQLException{
		String sql = "UPDATE books SET pubid = ?,bname = ?,porder = ?,bnum=?,pdate=?,cnum=?,price=?,pubnum=? WHERE bookid = ?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pubid);
			pst.setString(2, po.getBook().getBname());
			pst.setString(3, po.getBook().getBorder());
			pst.setInt(4, po.getBook().getBnum());
			pst.setDate(5, po.getBook().getPdate());
			pst.setInt(6, po.getBook().getCnum());
			pst.setDouble(7, po.getBook().getPrice());
			pst.setString(8, po.getBook().getPubnum());
			
			pst.setInt(9, po.getBook().getBookid());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
		
	}
	public void updateBookAuthorById(Connection conn,Book_Author po,int authorid) throws SQLException{
		String sql = "UPDATE book_author SET authorid = ? where bookid = ?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, authorid);
			pst.setInt(2, po.getBook().getBookid());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
		
	}
	public void delBookById(Connection conn,int bookid) throws SQLException{
		String sql = "UPDATE books SET bflag = 0 where bookid = ?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, bookid);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
		
				
	}
	public List<BookAuthorTypePO> getBookAuthorTypeListByBookid(Connection conn,int bookid) throws SQLException{
		String sql = "SELECT a.`aname`,aut.`tname` FROM books b "
				+ "LEFT JOIN book_author ba ON b.`bookid`=ba.`bookid` "
				+ "LEFT JOIN AUTHORS a ON ba.`authorid` = a.`authorid` "
				+ "LEFT JOIN authortype aut ON ba.`atid`=aut.`atid` "
				+ "WHERE b.`bookid`=?";
		List<BookAuthorTypePO> list = new ArrayList<BookAuthorTypePO>();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, bookid);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				BookAuthorTypePO po = new BookAuthorTypePO();
				po.setAname(rs.getString("aname"));
				po.setTname(rs.getString("tname"));
				list.add(po);
			}
		} catch (SQLException e) {
			throw e;
		}
		return list;
	}
	
}
