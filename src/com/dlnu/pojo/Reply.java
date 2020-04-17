package com.dlnu.pojo;

public class Reply {
	//����Ա�ظ�ID
	private Integer rID;
	
	//����ID�� ���  (ʵ����������)
	private Guestbook buestbook;
	
	//��ԱID�� ���  (ʵ������Ա��)
	private User user;
	
	//����Ա�ظ�����
	private String content;
	
	//����Ա�ظ�ʱ��
	private String replytime;

	public Reply(Integer rID, Guestbook buestbook, User user, String content, String replytime) {
		super();
		this.rID = rID;
		this.buestbook = buestbook;
		this.user = user;
		this.content = content;
		this.replytime = replytime;
	}

	public Integer getrID() {
		return rID;
	}

	public void setrID(Integer rID) {
		this.rID = rID;
	}

	public Guestbook getBuestbook() {
		return buestbook;
	}

	public void setBuestbook(Guestbook buestbook) {
		this.buestbook = buestbook;
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
