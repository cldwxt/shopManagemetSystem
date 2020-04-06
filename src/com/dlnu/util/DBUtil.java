package com.dlnu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class DBUtil {
	//��������
	
		static{
			try{
				Class.forName("com.mysql.jdbc.Driver");
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
			
		}
		
		//��ȡ���ݿ�����
		public static Connection getConnection(){
			String url = "jdbc:mysql://localhost:3306/jf?useUnicode=true&characterEncoding=utf8";
			String user = "root";
			String password = "root";
			
			
			Connection con = null;
			try {
				con = DriverManager.getConnection(url,user,password);
			} catch (SQLException e) {
				//e.printStackTrace();
				System.err.println("�����ݿ�ʱ����: " + e.getMessage());
			}
			
			
			return con;
		}
		
		public static void main(String[] args){
			DBUtil.getConnection();
		}
		
	private Connection conn;
	private Statement stmt;
  	private DataSource ds;
	
	public DBUtil(){
	}

/*
	//	�ر����ݿ⣬�����ӷ��������ӳ�
	
	public void close() {
		try{
			conn.close();
			System.out.println ("�ͷ�����");
		} 
		catch (SQLException ex) {
			System.err.println("�������ӳس���: " + ex.getMessage());
		}
	}

	
	//	ִ�в�ѯ
	public ResultSet executeQuery(String sql) throws SQLException{
		ResultSet rs = null;
		
		rs = stmt.executeQuery(sql);
		System.out.println ("ִ�в�ѯ");
		return rs;
	}


	//	ִ����ɾ��

	public int executeUpdate(String sql) throws SQLException{
		int ret = 0;
		
		ret = stmt.executeUpdate(sql);
	
		System.out.println ("ִ����ɾ��");
		return ret;
	}
*/
}
