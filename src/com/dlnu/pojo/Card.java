package com.dlnu.pojo;

public class Card {
	//��Ա��ID
	private Integer cID;
	
	//��Ա�ȼ�
	private Integer level;
	
	//��Ա������
	private Integer point;

	public Card(Integer cID, Integer level, Integer point) {
		super();
		this.cID = cID;
		this.level = level;
		this.point = point;
	}

	public Integer getcID() {
		return cID;
	}

	public void setcID(Integer cID) {
		this.cID = cID;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getPoint() {
		return point;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}
	
}
