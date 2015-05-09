package com.pojo;

public class WmlAttention {
	private Integer id;
	private String createDate;
	private String endDate;
	private Integer byId;
	private String byName;
	private Integer forId;
	private String forName;
	private Integer isDel;
	
	public WmlAttention() {}
	
	public WmlAttention(Integer id, String createDate, String endDate, Integer byId,
			String byName, Integer forId, String forName, Integer isDel) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.endDate = endDate;
		this.byId = byId;
		this.byName = byName;
		this.forId = forId;
		this.forName = forName;
		this.isDel = isDel;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getById() {
		return byId;
	}
	public void setById(Integer byId) {
		this.byId = byId;
	}
	public Integer getForId() {
		return forId;
	}
	public void setForId(Integer forId) {
		this.forId = forId;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getByName() {
		return byName;
	}
	public void setByName(String byName) {
		this.byName = byName;
	}
	public String getForName() {
		return forName;
	}
	public void setForName(String forName) {
		this.forName = forName;
	}
	
}
