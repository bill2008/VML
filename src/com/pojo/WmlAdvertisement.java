package com.pojo;

public class WmlAdvertisement {
	private Integer id;
	private String createDate;
	private String endDate;//结束时间
	private String description;
	private String name;
	private Integer productId;
	private String productName;
	private String url;
	private Integer isDel;
	public WmlAdvertisement() {}
	
	public WmlAdvertisement(Integer id, String createDate, String endDate,
			String description, String name, Integer productId,
			String productName, String url, Integer isDel) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.endDate = endDate;
		this.description = description;
		this.name = name;
		this.productId = productId;
		this.productName = productName;
		this.url = url;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
