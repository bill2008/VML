package com.pojo;

public class WmlPoster {
	private Integer id;
	private String createDate;
	private String endDate;
	private Integer pkgId;
	private String productName;
	private String description;
	private String url;
	private String name;
	private Integer isDel;
	
	public WmlPoster() {
	}
	public WmlPoster(Integer id, String createDate, String endDate,
			Integer pkgId, String productName, String description, String url,
			String name, Integer isDel) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.endDate = endDate;
		this.pkgId = pkgId;
		this.productName = productName;
		this.description = description;
		this.url = url;
		this.name = name;
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
	public Integer getPkgId() {
		return pkgId;
	}
	public void setPkgId(Integer pkgId) {
		this.pkgId = pkgId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
