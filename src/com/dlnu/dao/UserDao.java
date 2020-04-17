package com.dlnu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dlnu.pojo.*;
import com.dlnu.util.*;

/**
 * @author dell
 *
 */
/**
 * @author dell
 *
 */
/**
 * @author dell
 *
 */
public class UserDao {
	/**
	 * �����û�����
	 * 2020.4.7
	 */
	public int insert(User user) {
		Connection connection  = DBUtil.getConnection();
		String sql = "insert into tab_user "
				+ "(username,password,realname,sex,birthday,address,telephone,email,question,answer,signuptime) "
				+ "values (?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getRealName());
			pstmt.setString(4, user.getSex());
			pstmt.setString(5, user.getBirthday());
			pstmt.setString(6, user.getAddress());
			pstmt.setString(7, user.getTelephone());
			pstmt.setString(8, user.getEmail());
			pstmt.setString(9, user.getQuestion());
			pstmt.setString(10, user.getAnswer());
			pstmt.setString(11, user.getSignUpTime());
			
			pstmt.executeUpdate();
			pstmt.close();
			connection.close();
			
			return 0;
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * ͨ��uid��ѯ
	 * @param username
	 * @return
	 */
	public User queryById(int uid){
		Connection conn = DBUtil.getConnection();
		String sql = "select * from tab_user where uid = ?";
		User user = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
			}
				
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * ͨ���û�����ѯ
	 * 20202.4.7
	 * @param username
	 * @return User
	 */
	public User queryByName(String username){
		Connection conn = DBUtil.getConnection();
		String sql = "select * from tab_user where username = ?";
		User user = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
			}
				
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return user;
	}
	

	/**��ѯ�����û���������Ϣ
	 * @return
	 */
	public List<User> query(int start, int pageSize){
		Connection conn = DBUtil.getConnection();
		String sql = "select * from tab_user limit ?,?";
		List<User> list = new ArrayList<User>();
		User user = null;
		try {
			//Ԥ����sql
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, pageSize);
			//��ѯ������ؽ����
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12));
				list.add(user);
			}
				
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;
	}
	
	/**��ѯ���û�����
	 * 
	 * @return
	 */
	public int findAll() {
		Connection conn = DBUtil.getConnection();
		String sql = "select count(*) from tab_user";
		int userCount = 0;//�û�����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				userCount = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return userCount;
	}
	
	/**��ѯ���û�����
	 * 
	 * @return
	 */
	public int findAllByTime(String time1, String time2) {
		Connection conn = DBUtil.getConnection();
		String sql = "select count(*) from tab_user where signuptime between '"+time1+"' and '"+time2+"'";
		int userCount = 0;//�û�����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				userCount = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return userCount;
	}
	
}


