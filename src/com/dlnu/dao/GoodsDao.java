package com.dlnu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dlnu.pojo.Goods;
import com.dlnu.pojo.User;
import com.dlnu.util.DBUtil;

public class GoodsDao {
	
	
	/**
	 * �޸Ŀ��
	 * @param user
	 * @return
	 */
	public boolean updateStockByGid (Goods goods) {
		Connection connection  = DBUtil.getConnection();
		String sql = "update tab_goods "
				+ "set gstock=gstock+? "
				+ "where gid=?";
		try {
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setInt(1, goods.getgStock());
			pstmt.setInt(2, goods.getgID());
			
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
	 * ������Ʒ����
	 * 2020.4.7
	 */
	public int insert(Goods goods) {
		Connection connection  = DBUtil.getConnection();
		String sql = "insert into tab_goods "
				+ "(gname,gprice,gpoint,gstock) "
				+ "values (?,?,?,?)";
		try {
			PreparedStatement pstmt;
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, goods.getgName());
			pstmt.setDouble(2, goods.getgPrice());
			pstmt.setInt(3, goods.getgPoint());
			pstmt.setInt(4, goods.getgStock());
			
			
			pstmt.executeUpdate();
			pstmt.close();
			connection.close();
			
			return 0;
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
	}
	
	/**��ѯ����Ʒ����
	 * 
	 * @return
	 */
	public int findAll() {
		Connection conn = DBUtil.getConnection();
		String sql = "select count(*) from tab_goods";
		int goodsCount = 0;//�û�����
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				goodsCount = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return goodsCount;
	}
	
	/**��ѯ������Ʒ��������Ϣ
	 * @return
	 */
	public List<Goods> query(int start, int pageSize){
		Connection conn = DBUtil.getConnection();
		String sql = "select * from tab_goods limit ?,?";
		List<Goods> list = new ArrayList<Goods>();
		Goods goods = null;
		try {
			//Ԥ����sql
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, pageSize);
			//��ѯ������ؽ����
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				goods = new Goods(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4),rs.getInt(5));
				list.add(goods);
			}
				
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * ͨ��gid��ѯ
	 * @param gid
	 * @return
	 */
	public Goods queryById(int gid){
		Connection conn = DBUtil.getConnection();
		String sql = "select * from tab_goods where gid = ?";
		Goods goods = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gid);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				goods = new Goods(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4),rs.getInt(5));
			}
				
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return goods;
	}
}
