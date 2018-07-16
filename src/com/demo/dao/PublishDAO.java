package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.demo.db.DBUtil;
import com.demo.po.AreasPO;

import com.demo.po.PublisherPO;
import com.demo.util.PageUtil;
import com.demo.vo.PublishSerachVO;

public class PublishDAO {
	
	public List<PublisherPO> getPublisherList(Connection conn,PublishSerachVO vo,int start) throws SQLException{
		
		String sql = "SELECT * FROM publisher p left JOIN areas a ON (p.`areaid`=a.`areaid`) WHERE p.pflag=1 ";
		if(vo.getPname() != null && !vo.getPname().isEmpty()){
			sql += "AND p.`pname` LIKE '%"+vo.getPname()+"%'";
		}
		sql += "limit "+start+" ,"+PageUtil.PAGE_ITEMS;
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
	public int getPublisherListTotalCount(Connection conn,PublishSerachVO vo) throws SQLException{
		String sql = "select count(pubid) from publisher where pflag=1 ";
		if(vo.getPname() != null && !vo.getPname().isEmpty()){
			sql += "and pname like '%"+vo.getPname()+"%'";
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
	public void addPublish(Connection conn,PublisherPO po,int areaid) throws SQLException{
		String sql = "INSERT INTO publisher (areaid,pname,sname,tel,adress,pflag) VALUES(?,?,?,?,?,1)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, areaid);
			pst.setString(2, po.getPname());
			pst.setString(3, po.getSname());
			pst.setString(4, po.getTel());
			pst.setString(5, po.getAdress());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}
	public int getAreaid(PublisherPO po){
		Connection conn = DBUtil.getConnection();
		String sql = "select areaid from areas where arname=?";
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
	public PublisherPO getPublisherPOByPubid(Connection conn,int pubid) throws SQLException{
		String sql = "SELECT * FROM publisher p LEFT JOIN areas ar ON (p.`areaid`=ar.`areaid`) WHERE p.`pubid`=? ";
		PublisherPO po = null;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pubid);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				po = new PublisherPO();
				AreasPO ap = new AreasPO();
				ap.setAname(rs.getString("arname"));
				ap.setAreaid(rs.getInt("areaid"));
				po.setArea(ap);
				po.setPname(rs.getString("pname"));
				po.setSname(rs.getString("sname"));
				po.setTel(rs.getString("tel"));
				po.setAdress(rs.getString("adress"));
				
			}
		} catch (SQLException e) {
			throw e;
		}
		return po;
	}
	public void updatePublishByPubid(Connection conn,PublisherPO po,int areaid) throws SQLException{
		String sql = "UPDATE publisher SET areaid=?,pname=?,sname=?,tel=?,adress=? WHERE pubid=? ";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, areaid);
			pst.setString(2, po.getPname());
			pst.setString(3, po.getSname());
			pst.setString(4, po.getTel());
			pst.setString(5, po.getAdress());
			pst.setInt(6, po.getPubid());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
	}
	public void delPublishByPubid(Connection conn,int pubid) throws SQLException{
		String sql = "update publisher set pflag=0 where pubid=?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pubid);
			pst.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}
		
	}
	public List<String> getBnameListByPubid(Connection conn,int pubid) throws SQLException{
		String sql = "SELECT b.bname FROM publisher p LEFT JOIN books b ON(p.`pubid`=b.`pubid`) WHERE p.`pubid`=?";
		List<String> list = new ArrayList<String>();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, pubid);
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
