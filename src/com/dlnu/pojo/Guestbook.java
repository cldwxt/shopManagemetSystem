package com.dlnu.pojo;

public class Guestbook {
	//�û�����ID
	private Integer gbID;
	
	//��Աid
	private Integer uID;
	
	//�û���������
	private String content;
	
	//�û�����ʱ��
	private String addtime;
	
	//�Ƿ��ѱ��ظ�
	private boolean flag;
	
	///��ԱID�� ���  (ʵ������Ա��)
	private User user;

	


	public Guestbook(Integer uID, String content, String addtime) {
		super();
		this.uID = uID;
		this.content = content;
		this.addtime = addtime;
	}

	public Integer getGbID() {
		return gbID;
	}

	public void setGbID(Integer gbID) {
		this.gbID = gbID;
	}

	public Integer getuID() {
		return uID;
	}

	public void setuID(Integer uID) {
		this.uID = uID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	
	
	
}
