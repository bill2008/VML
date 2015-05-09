package com.pojo;

public class WmlConfig {

	private Integer id;
	private String name;
	private String value;
	private String description;
	private Integer isDel;



	public WmlConfig(Integer id, String name, String value, String description,
			Integer isDel) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.description = description;
		this.isDel = isDel;
	}

	public WmlConfig() {}
	
	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
}
