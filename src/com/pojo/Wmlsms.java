package com.pojo;

public class Wmlsms {

	private Integer id;
	private Integer uid;
	private String createDate;
	private String endDate;
	private String content;
	private String userName;
	private Integer isDel;
	public Wmlsms() {
		
	}
	public Wmlsms(Integer id, Integer uid, String createDate, String endDate,
			String content, String userName, Integer isDel) {
		
		this.id = id;
		this.uid = uid;
		this.createDate = createDate;
		this.endDate = endDate;
		this.content = content;
		this.userName = userName;
		this.isDel = isDel;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
	
}
