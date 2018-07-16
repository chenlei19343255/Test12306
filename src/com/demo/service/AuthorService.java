package com.demo.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.demo.dao.AuthorDao;
import com.demo.db.DBUtil;
import com.demo.po.AreasPO;
import com.demo.po.AuthorPO;
import com.demo.po.Book_Author;
import com.demo.util.PageUtil;
import com.demo.util.TransferUtil;
import com.demo.vo.AddAuthorVO;
import com.demo.vo.AuthorSearchVO;

public class AuthorService {
	private AuthorDao dao = new AuthorDao();
	public List<AuthorPO> getAuthorList(AuthorSearchVO vo){
		int page = Integer.parseInt(vo.getPagenum());
		int start = PageUtil.getIndex(page);
		Connection conn = DBUtil.getConnection();
		List<AuthorPO> list = new ArrayList<AuthorPO>();
		try {
			list = dao.getAuthorList(conn,vo,start);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return list;
	}
	public int getAuthorListTotalCount(AuthorSearchVO vo){
		Connection conn = DBUtil.getConnection();
		int count = 0;
		try {
			count = dao.getAuthorListTotalCount(conn,vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return count;
	}
	public List<AreasPO> getAreasPOList(){
		List<AreasPO> list = new ArrayList<AreasPO>();
		Connection conn = DBUtil.getConnection();
		try {
			list = dao.getAreasPOList(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return list;
	}
	public void addAuthor(AddAuthorVO vo){
		AuthorPO po = new AuthorPO();
		AreasPO ap = new AreasPO();
		ap.setAname(vo.getArname());
		po.setArea(ap);
		po.setAname(vo.getAname());
		po.setBname(vo.getBname());
		po.setSex(Integer.parseInt(vo.getSex()));
		po.setBirthday(TransferUtil.String2SqlDate(vo.getBirthday(), "yyyy-MM-dd"));
		po.setPhone(vo.getPhone());
		po.setAdress(vo.getAdress());
		
		int areaid = dao.getAreaid(po);
		Connection conn = DBUtil.getConnection();
		try {
			dao.addAuthor(conn, po, areaid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
	}
	public Book_Author getBook_AuthorPOByAuthorID(String authorid){
		int aid = Integer.parseInt(authorid);
		Connection conn = DBUtil.getConnection();
		Book_Author po = null;
		try {
			po = dao.getBook_AuthorPOByAuthorID(conn, aid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return po;
	}
	public void updateAuthorByAuthorid(AddAuthorVO vo){
		AuthorPO po = new AuthorPO();
		AreasPO ap = new AreasPO();
		ap.setAname(vo.getArname());
		po.setArea(ap);
		po.setAuthorid(Integer.parseInt(vo.getAuthorid()));
		po.setAname(vo.getAname());
		po.setBname(vo.getBname());
		po.setSex(Integer.parseInt(vo.getSex()));
		po.setBirthday(TransferUtil.String2SqlDate(vo.getBirthday(), "yyyy-MM-dd"));
		po.setPhone(vo.getPhone());
		po.setAdress(vo.getAdress());
		int areaid = dao.getAreaid(po);
		Connection conn = DBUtil.getConnection();
		try {
			dao.updateAuthorByAuthorid(conn, po, areaid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
	}
	public void delAuthorByAuthorid(String authorid){
		int aid = Integer.parseInt(authorid);
		Connection conn = DBUtil.getConnection();
		try {
			dao.delAuthorByAuthorid(conn, aid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
	}
	public List<String> getBnameListByAuthorid(String authorid){
		int aid = Integer.parseInt(authorid);
		Connection conn = DBUtil.getConnection();
		List<String> list = null;
		try {
			list = dao.getBookNameListByAuthorid(conn, aid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return list;
		
	}
}
