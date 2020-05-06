package com.dlnu.pojo;

public class PurchaseHistory {
	//���Ѽ�¼ID
	private Integer pID;
	
	//��ԱID
	private Integer uID;
	
	//��ƷID
	private Integer gID;
	
	//��������
	private Integer pCount;
	
	//����ʱ��
	private String pTime;
	
	//�û�ID�����  (ʵ������Ա��)
	private User user;
	
	//��ƷID�����  (ʵ������Ʒ��)
	private Goods goods;

	public PurchaseHistory(Integer pID, Integer uID, Integer gID, Integer pCount, String pTime) {
		super();
		this.pID = pID;
		this.uID = uID;
		this.gID = gID;
		this.pCount = pCount;
		this.pTime = pTime;
	}

	public Integer getpID() {
		return pID;
	}

	public void setpID(Integer pID) {
		this.pID = pID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Integer getpCount() {
		return pCount;
	}

	public void setpCount(Integer pCount) {
		this.pCount = pCount;
	}

	public String getpTime() {
		return pTime;
	}

	public void setpTime(String pTime) {
		this.pTime = pTime;
	}

	public Integer getuID() {
		return uID;
	}

	public void setuID(Integer uID) {
		this.uID = uID;
	}

	public Integer getgID() {
		return gID;
	}

	public void setgID(Integer gID) {
		this.gID = gID;
	}
	
	
	
}
