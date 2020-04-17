package com.dlnu.pojo;

/**
 * @author dell
 *
 */
public class Card {
	//��Ա��ID
	private Integer cID;
	
	//��ԱID�� ���  (ʵ������Ա��)
	private User user;
	
	//��Ա������
	private Integer point;
	
	//��Ա�ȼ�
	private Integer level;
	
	


	public Card(Integer cID, User user, Integer point, Integer level) {
		super();
		this.cID = cID;
		this.user = user;
		this.point = point;
		this.level = level;
	}

	public Card(User user) {
		super();
		this.user = user;
	}

	public Integer getcID() {
		return cID;
	}

	public void setcID(Integer cID) {
		this.cID = cID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	
}
