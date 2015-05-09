package com.pojo;

public class WmlProductType {
	private Integer id;
	private String name;
	private Integer isDel;
	
	public WmlProductType() {}

	public WmlProductType(Integer id, String name, Integer isDel) {
		super();
		this.id = id;
		this.name = name;
		this.isDel = isDel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
	
	
}
