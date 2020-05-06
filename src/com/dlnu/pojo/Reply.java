package com.dlnu.pojo;

public class Reply {
	//����Ա�ظ�ID
	private Integer rID;
	
	//����ID
	private Integer gbID;
	
	//��ԱID
	private Integer uID;
	
	//����Ա�ظ�����
	private String content;
	
	//����Ա�ظ�ʱ��
	private String replytime;

	//����ID�� ���  (ʵ����������)
	private Guestbook guestbook;
	
	//��ԱID�� ���  (ʵ������Ա��)
	private User user;
		
	

	public Reply(Integer rID, Integer gbID, Integer uID, String content, String replytime) {
		super();
		this.rID = rID;
		this.gbID = gbID;
		this.uID = uID;
		this.content = content;
		this.replytime = replytime;
	}

	public Integer getrID() {
		return rID;
	}

	public void setrID(Integer rID) {
		this.rID = rID;
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

	public Guestbook getGuestbook() {
		return guestbook;
	}

	public void setGuestbook(Guestbook guestbook) {
		this.guestbook = guestbook;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReplytime() {
		return replytime;
	}

	public void setReplytime(String replytime) {
		this.replytime = replytime;
	}

	
}
