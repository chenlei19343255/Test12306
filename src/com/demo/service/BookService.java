package com.demo.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.demo.dao.BookDAO;
import com.demo.db.DBUtil;
import com.demo.po.AuthorPO;
import com.demo.po.BookAuthorTypePO;
import com.demo.po.BookPO;
import com.demo.po.Book_Author;
import com.demo.po.PublisherPO;
import com.demo.po.UserinfoPO;
import com.demo.util.PageUtil;
import com.demo.util.TransferUtil;
import com.demo.vo.AddBookVO;
import com.demo.vo.BookSearchVO;
import com.demo.vo.UserSearchVO;

public class BookService {
	
	private BookDAO dao = new BookDAO();
	
	public UserinfoPO login(String lname,String lpass){
		UserinfoPO po = null;
		Connection conn = DBUtil.getConnection();
		try {
			po = dao.Login(conn, lname, lpass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return po;
	}
	public List<UserinfoPO> getUserList(UserSearchVO vo){
		int page = Integer.parseInt(vo.getPagenum());
		int start = PageUtil.getIndex(page);
		Connection conn = DBUtil.getConnection();
		List<UserinfoPO> list = null;
		try {
			list = dao.getUserList(conn,vo,start);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return list;
	}
	
	public int getUserTotalCount(UserSearchVO vo){
		int count = 0;
		Connection conn = DBUtil.getConnection();
		try {
			count = dao.getUserTotalCount(conn, vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return count;
	}
	
	
	public List<Book_Author> getBookList(BookSearchVO vo){
		List<Book_Author> list = null;
		Connection conn = DBUtil.getConnection();
		int page = Integer.parseInt(vo.getPagenum());
		int start = PageUtil.getIndex(page);
		
		try {
			list = dao.getBookList(conn,vo,start);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return list;
	}
	
	public int getBookListTotalCount(BookSearchVO vo){
		Connection conn = DBUtil.getConnection();
		int count=0;
		try {
			count = dao.getBookListTotalCount(conn, vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return count;
	}
	
	
	
	public List<PublisherPO> getPList(){
		List<PublisherPO> list = new ArrayList<PublisherPO>();
		Connection conn = DBUtil.getConnection();
		try {
			list = dao.getPList(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return list;
	}
	public List<AuthorPO> getAlist(){
		List<AuthorPO> list = null;
		Connection conn = DBUtil.getConnection();
		try {
			list = dao.getAList(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return list;
	}
	
	
	
	
	public void addBook(AddBookVO vo){
		Book_Author po = new Book_Author();
		AuthorPO ap = new AuthorPO();
		ap.setAname(vo.getAname());
		po.setAuthor(ap);
		PublisherPO pp =new PublisherPO();
		pp.setPname(vo.getPname());
		
		BookPO bp = new BookPO();
		bp.setPublish(pp);
		bp.setBname(vo.getBname());
		bp.setBorder(vo.getPorder());
		bp.setPubnum(vo.getPubnum());
		bp.setBnum(Integer.parseInt(vo.getBnum()));
		bp.setCnum(Integer.parseInt(vo.getCnum()));
		bp.setPrice(Double.parseDouble(vo.getPrice()));
		bp.setPdate(TransferUtil.String2SqlDate(vo.getPdate(), "yyyy-MM-dd"));
		po.setBook(bp);
		
		int pubid = dao.getPubId(po);
		
		int authorid = dao.getAuthorId(po);
		
		Connection conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			dao.addBook(conn, po,pubid);
			int bookid = dao.getBookId(conn,po);
			
			dao.addBook_Author(conn, po,authorid,bookid);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			DBUtil.close(conn);
		}
		return;
	}
	
	public Book_Author getBookAuthorPOById(String bid){
		int bookid = Integer.parseInt(bid);
		Connection conn = DBUtil.getConnection();
		Book_Author po = null;
		try {
			po = dao.getBookAuthorPOById(conn, bookid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return po;
	}
	
	public void updateBook(AddBookVO vo){
		Book_Author po = new Book_Author();
		AuthorPO ap = new AuthorPO();
		ap.setAname(vo.getAname());
		po.setAuthor(ap);
		PublisherPO pp =new PublisherPO();
		pp.setPname(vo.getPname());
		
		BookPO bp = new BookPO();
		bp.setBookid(Integer.parseInt(vo.getBookid()));
		bp.setPublish(pp);
		bp.setBname(vo.getBname());
		bp.setBorder(vo.getPorder());
		bp.setPubnum(vo.getPubnum());
		bp.setBnum(Integer.parseInt(vo.getBnum()));
		bp.setCnum(Integer.parseInt(vo.getCnum()));
		bp.setPrice(Double.parseDouble(vo.getPrice()));
		bp.setPdate(TransferUtil.String2SqlDate(vo.getPdate(), "yyyy-MM-dd"));
		
		po.setBook(bp);
		
		int pubid = dao.getPubId(po);
		int authorid = dao.getAuthorId(po);
		Connection conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			dao.updateBookById(conn, po, pubid);
			dao.updateBookAuthorById(conn, po, authorid);
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			DBUtil.close(conn);
		}
	}
	public void delBookById(String bookid){
		int bid = Integer.parseInt(bookid);
		Connection conn = DBUtil.getConnection();
		try {
			dao.delBookById(conn, bid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
	}
	public List<BookAuthorTypePO> getBookAuthorTypeListByBookid(String bookid){
		int bid = Integer.parseInt(bookid);
		Connection conn = DBUtil.getConnection();
		List<BookAuthorTypePO> list = null;
		try {
			list = dao.getBookAuthorTypeListByBookid(conn, bid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return list;
	}
}
