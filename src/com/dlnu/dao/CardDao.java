package com.dlnu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dlnu.pojo.Card;
import com.dlnu.pojo.User;
import com.dlnu.util.DBUtil;

public class CardDao {
	/**
	 * 插入会员卡数据
	 * 2020.4.17
	 */
	public void insert(Card card) {
		Connection connection  = DBUtil.getConnection();
		String sql = "insert into tab_card(uid) values (?)";
		try {
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, card.getUser().getuID());
			
			pstmt.executeUpdate();
			pstmt.close();
			connection.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**查询所有用户的所有信息
	 * @return
	 */
	public List<Card> query(int start, int pageSize){
		Connection conn = DBUtil.getConnection();
		String sql = "select * from tab_card limit ?,?";
		List<Card> list = new ArrayList<Card>();
		Card card = null;
		try {
			//预处理sql
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, pageSize);
			//查询结果返回结果集
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setuID(rs.getInt(2));
				card = new Card(rs.getInt(1),user,rs.getInt(3),rs.getInt(4));
				list.add(card);
			}
				
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;
	}
	
	/**查询总会员卡数量
	 * 
	 * @return
	 */
	public int findAll() {
		Connection conn = DBUtil.getConnection();
		String sql = "select count(*) from tab_card";
		int cardCount = 0;//用户总数
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				cardCount = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return cardCount;
	}
}
