package com.dlnu.pojo;

public class Reply {
	//����Ա�ظ�ID
	private Integer ID;
	
	//����Ա�ظ�����
	private String content;
	
	//����Ա�ظ�ʱ��
	private String replytime;

	public Reply(Integer iD, String content, String replytime) {
		super();
		ID = iD;
		this.content = content;
		this.replytime = replytime;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
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
