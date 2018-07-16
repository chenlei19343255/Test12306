package com.demo.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	
	private static String url ="jdbc:mysql://localhost/mydb";
	private static String db_lname = "root";
	private static String db_lpass = "1234";
	
	private DBUtil(){}
	
	static{
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Properties p = new Properties();
		try {
			p.load(DBUtil.class.getResourceAsStream("DBConfig.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String ip = p.getProperty("IP");
		String port = p.getProperty("port");
		String schema = p.getProperty("schema");
		
		url ="jdbc:mysql://"+ip+":"+port+"/"+schema;
		
		db_lname = p.getProperty("user");
		db_lpass = p.getProperty("pass");
		
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, db_lname, db_lpass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
	
	

}
