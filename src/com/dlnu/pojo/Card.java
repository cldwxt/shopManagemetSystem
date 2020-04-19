package com.dlnu.pojo;

/**
 * @author dell
 *
 */
public class Card {
	//��Ա��ID
	private Integer cID;
	
	//��Աid
	private Integer uID;
	
	//��Ա������
	private Integer point;
	
	//��Ա�ȼ�
	private Integer level;
	
	///��ԱID�� ���  (ʵ������Ա��)
	private User user;


	public Card() {
		super();
	}


	public Card(Integer uID) {
		super();
		this.uID = uID;
	}


	public Card(Integer cID, Integer uID, Integer point, Integer level) {
		super();
		this.cID = cID;
		this.uID = uID;
		this.point = point;
		this.level = level;
	}


	public Integer getcID() {
		return cID;
	}


	public void setcID(Integer cID) {
		this.cID = cID;
	}


	public Integer getuID() {
		return uID;
	}


	public void setuID(Integer uID) {
		this.uID = uID;
	}


	public Integer getPoint() {
		return point;
	}


	public void setPoint(Integer point) {
		this.point = point;
	}


	public Integer getLevel() {
		return level;
	}


	public void setLevel(Integer level) {
		this.level = level;
	}


	@Override
	public String toString() {
		return "Card [cID=" + cID + ", uID=" + uID + ", point=" + point + ", level=" + level + "]";
	}

	

	
}
