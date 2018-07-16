package com.demo.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.demo.dao.PublishDAO;
import com.demo.db.DBUtil;
import com.demo.po.AreasPO;

import com.demo.po.PublisherPO;
import com.demo.util.PageUtil;
import com.demo.vo.AddPublishVO;
import com.demo.vo.PublishSerachVO;

public class PublishService {
	private PublishDAO dao = new PublishDAO();
	
	public List<PublisherPO> getPublishList(PublishSerachVO vo){
		int page = Integer.parseInt(vo.getPagenum());
		int start = PageUtil.getIndex(page);
		Connection conn = DBUtil.getConnection();
		List<PublisherPO> list = new ArrayList<PublisherPO>();
		try {
			list = dao.getPublisherList(conn, vo,start);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return list;
	}
	public int getPublishListTotalCount(PublishSerachVO vo){
		int count = 0;
		Connection conn = DBUtil.getConnection();
		try {
			count = dao.getPublisherListTotalCount(conn, vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return count;
	}
	public void addPublish(AddPublishVO vo){
		PublisherPO po = new PublisherPO();
		AreasPO ap = new AreasPO();
		ap.setAname(vo.getArname());
		po.setArea(ap);
		po.setPname(vo.getPname());
		po.setSname(vo.getSname());
		po.setTel(vo.getTel());
		po.setAdress(vo.getAdress());
		int areaid = dao.getAreaid(po);
		Connection conn = DBUtil.getConnection();
		try {
			dao.addPublish(conn, po, areaid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		
	}
	public PublisherPO getPublishPOByPubid(String pubid){
		int pid = Integer.parseInt(pubid);
		Connection conn = DBUtil.getConnection();
		PublisherPO po = null;
		try {
			po = dao.getPublisherPOByPubid(conn, pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return po;
	}
	public void updatePublishByPubid(AddPublishVO vo){
		PublisherPO po = new PublisherPO();
		AreasPO ap = new AreasPO();
		ap.setAname(vo.getArname());
		po.setArea(ap);
		po.setPubid(Integer.parseInt(vo.getPubid()));
		po.setPname(vo.getPname());
		po.setSname(vo.getSname());
		po.setTel(vo.getTel());
		po.setAdress(vo.getAdress());
		int areaid = dao.getAreaid(po);
		Connection conn = DBUtil.getConnection();
		try {
			dao.updatePublishByPubid(conn, po, areaid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		
	}
	public void delPublishByPubid(String pubid){
		int pid = Integer.parseInt(pubid);
		Connection conn = DBUtil.getConnection();
		try {
			dao.delPublishByPubid(conn, pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
	}
	public List<String> getBnameListByPubid(String pubid){
		int pid = Integer.parseInt(pubid);
		Connection conn = DBUtil.getConnection();
		List<String> list = null;
		try {
			list = dao.getBnameListByPubid(conn, pid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return list;		
	}
	
}
