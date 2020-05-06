package com.dlnu.pojo;

public class ShoppingCar {
	//��ԱID
	private Integer uID;
	
	//��ƷID
	private Integer gID;
	
	//��������
	private Integer sCount;
	
	//�û�ID�����  (ʵ������Ա��)
	private User user;
	
	//��ƷID�����  (ʵ������Ʒ��)
	private Goods goods;

	public ShoppingCar(Integer uID, Integer gID) {
		super();
		this.uID = uID;
		this.gID = gID;
	}

	public ShoppingCar(Integer uID, Integer gID, Integer sCount) {
		super();
		this.uID = uID;
		this.gID = gID;
		this.sCount = sCount;
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

	public Integer getsCount() {
		return sCount;
	}

	public void setsCount(Integer sCount) {
		this.sCount = sCount;
	}
	
	
}
