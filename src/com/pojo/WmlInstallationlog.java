package com.pojo;

public class WmlInstallationlog {

	private Integer id;
	private String createDate;
	private String endDate;
	private Integer count;
	private String  mac;
	private String region;
	private String type;
	private Integer isDel;
	public WmlInstallationlog() {}
	
	

	public WmlInstallationlog(Integer id, String createDate, String endDate,
			Integer count, String mac, String region, String type, Integer isDel) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.endDate = endDate;
		this.count = count;
		this.mac = mac;
		this.region = region;
		this.type = type;
		this.isDel = isDel;
	}



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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
		
}
