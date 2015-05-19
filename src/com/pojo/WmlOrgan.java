package com.pojo;

public class WmlOrgan {
	private Integer id;
	private String  createDate;
	private String endDate;
	private String name;
	private String address;
	private String phone;
	private String typeId;
	private String typeName;
	private String code;
	private Integer permissions;
	private Integer userId;
	private Integer isDel;
	
	public WmlOrgan() {	}
	

	public WmlOrgan(Integer id, String createDate, String endDate, String name,
			String address, String phone, String typeId, String typeName,
			String code, Integer permissions, Integer userId, Integer isDel) {
		
		this.id = id;
		this.createDate = createDate;
		this.endDate = endDate;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.typeId = typeId;
		this.typeName = typeName;
		this.code = code;
		this.permissions = permissions;
		this.userId = userId;
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


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPermissions() {
		return permissions;
	}

	public void setPermissions(Integer permissions) {
		this.permissions = permissions;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Integer getIsDel() {
		return isDel;
	}


	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
}
