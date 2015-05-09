package com.pojo;

public class WmlAdmin {
	private Integer id;
	private String loginName;
	private String name;
	private String password;
	private String phone;
	private Integer status;
	private Integer updatePrice;
	
	public WmlAdmin(){}
	

	public WmlAdmin(Integer id, String loginName, String name, String password,
			String phone, Integer status, Integer updatePrice) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.status = status;
		this.updatePrice = updatePrice;
	}


	public Integer getUpdatePrice() {
		return updatePrice;
	}


	public void setUpdatePrice(Integer updatePrice) {
		this.updatePrice = updatePrice;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
