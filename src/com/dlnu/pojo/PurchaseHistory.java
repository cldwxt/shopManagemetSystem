package com.dlnu.pojo;

public class PurchaseHistory {
	//���Ѽ�¼ID
	private Integer pID;
	
	//��������
	private Integer pCount;
	
	//����ʱ��
	private String pTime;

	public PurchaseHistory(Integer pID, Integer pCount, String pTime) {
		super();
		this.pID = pID;
		this.pCount = pCount;
		this.pTime = pTime;
	}

	public Integer getpID() {
		return pID;
	}

	public void setpID(Integer pID) {
		this.pID = pID;
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
	
	
}
