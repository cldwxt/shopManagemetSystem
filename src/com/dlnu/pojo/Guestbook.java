package com.dlnu.pojo;

public class Guestbook {
	//�û�����ID
	private Integer nid;
	
	//�û���������
	private String content;
	
	//�û�����ʱ��
	private String addtime;

	public Guestbook(Integer nid, String content, String addtime) {
		super();
		this.nid = nid;
		this.content = content;
		this.addtime = addtime;
	}

	public Integer getNid() {
		return nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
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
	
	
}
