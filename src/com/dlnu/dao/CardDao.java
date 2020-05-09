package com.dlnu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dlnu.pojo.Card;
import com.dlnu.pojo.Goods;
import com.dlnu.pojo.User;
import com.dlnu.util.DBUtil;

public class CardDao {
	
	/**
	 * ���»���
	 * @param card
	 * @return
	 */
	public boolean updatePoint (int uid, int point) {
		Connection connection  = DBUtil.getConnection();
		String sql = "update tab_card "
				+ "set point=point+? "
				+ "where uid=?";
		try {
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, point);
			pstmt.setInt(2, uid);
			
			pstmt.executeUpdate();
			pstmt.close();
			connection.close();
			
			return true;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * ͨ��uid��ѯ��Ա����Ϣ
	 * @param uid
	 * @return
	 */
	public Card queryByUid(int uid){
		Connection conn = DBUtil.getConnection();
		String sql = "select * from tab_card where uid = ?";
		Card card = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);

			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				//User user = new User();
				//user.setuID(rs.getInt(2));
				card = new Card(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
			}
				
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return card;
	}
	
	/**
	 * �����Ա������
	 * 2020.4.17
	 */
	public void insert(Card card) {
		Connection connection  = DBUtil.getConnection();
		String sql = "insert into tab_card(uid) values (?)";
		try {
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, card.getuID());
			
			pstmt.executeUpdate();
			pstmt.close();
			connection.close();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	/**��ѯ�����û���������Ϣ
	 * @return
	 */
	public List<Card> query(int start, int pageSize){
		Connection conn = DBUtil.getConnection();
		String sql = "select * from tab_card limit ?,?";
		List<Card> list = new ArrayList<Card>();
		Card card = null;
		try {
			//Ԥ����sql
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, pageSize);
			//��ѯ������ؽ����
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
			//	User user = new User();
			//	user.setuID(rs.getInt(2));
				card = new Card(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
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
	
	/**��ѯ�ܻ�Ա������
	 * 
	 * @return
	 */
	public int findAll() {
		Connection conn = DBUtil.getConnection();
		String sql = "select count(*) from tab_card";
		int cardCount = 0;//�û�����
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
