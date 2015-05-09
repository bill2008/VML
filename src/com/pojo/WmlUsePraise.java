package com.pojo;

public class WmlUsePraise {

	private Integer id;
	private Integer productId;
	private Integer userId;
	private String createDate;
	private String endDate;
	private Integer isDel;	
	public WmlUsePraise() {
		
	}

	
	public WmlUsePraise(Integer id, Integer productId, Integer userId, String createDate,
			String endDate, Integer isDel) {
		super();
		this.id = id;
		this.productId = productId;
		this.userId = userId;
		this.createDate = createDate;
		this.endDate = endDate;
		this.isDel = isDel;
	}


	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
}
