package com.dlnu.pojo;

public class Guestbook {
	//�û�����ID
	private Integer gbid;
	
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

	public Guestbook(Integer gbid, User user, String content, String addtime, boolean flag) {
		super();
		this.gbid = gbid;
		this.user = user;
		this.content = content;
		this.addtime = addtime;
		this.flag = flag;
	}

	public Integer getGbid() {
		return gbid;
	}

	public void setGbid(Integer gbid) {
		this.gbid = gbid;
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
