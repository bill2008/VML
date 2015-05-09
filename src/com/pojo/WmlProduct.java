package com.pojo;

import java.util.List;

public class WmlProduct {
	
	private Integer id;
	private String createDate;
	private String endDate;
	private Integer uid;
	private String userName;
	private Integer tid;
	private String productType;
	private Integer oid;
	private String organName;
	private Integer bid;
	private String brandName;
	private String property;
	private String uploadType;
	private String description;
	private Double price;
	private Double endPrice;
	private Integer forwar;
	private Integer download;
	private Integer collect;
	private Integer status;
	private String name;
	private Integer isDel;
	private Integer viewCount;//浏览次数
	private String onTime;
	
	private String productImgPath;
	private Integer productImgWidth;
	private Integer productImgHeight;
	//图片列表
	private  List<WmlProductImage> productImageList;
	
	public WmlProduct() {}

	public WmlProduct(Integer id, String createDate, String endDate,
			Integer uid, String userName, Integer tid, String productType,
			Integer oid, String organName, Integer bid, String brandName,
			String property, String uploadType, String description,
			Double price, Double endPrice, Integer forwar, Integer download,
			Integer collect, Integer status, String name, Integer isDel,
			Integer viewCount, String onTime, String productImgPath,
			Integer productImgWidth, Integer productImgHeight,
			List<WmlProductImage> productImageList) {
		this.id = id;
		this.createDate = createDate;
		this.endDate = endDate;
		this.uid = uid;
		this.userName = userName;
		this.tid = tid;
		this.productType = productType;
		this.oid = oid;
		this.organName = organName;
		this.bid = bid;
		this.brandName = brandName;
		this.property = property;
		this.uploadType = uploadType;
		this.description = description;
		this.price = price;
		this.endPrice = endPrice;
		this.forwar = forwar;
		this.download = download;
		this.collect = collect;
		this.status = status;
		this.name = name;
		this.isDel = isDel;
		this.viewCount = viewCount;
		this.onTime = onTime;
		this.productImgPath = productImgPath;
		this.productImgWidth = productImgWidth;
		this.productImgHeight = productImgHeight;
		this.productImageList = productImageList;
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

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Integer getOid() {
		return oid;
	}

	public void setOid(Integer oid) {
		this.oid = oid;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getUploadType() {
		return uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(Double endPrice) {
		this.endPrice = endPrice;
	}

	public Integer getForwar() {
		return forwar;
	}

	public void setForwar(Integer forwar) {
		this.forwar = forwar;
	}

	public Integer getDownload() {
		return download;
	}

	public void setDownload(Integer download) {
		this.download = download;
	}

	public Integer getCollect() {
		return collect;
	}

	public void setCollect(Integer collect) {
		this.collect = collect;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getProductImgPath() {
		return productImgPath;
	}

	public void setProductImgPath(String productImgPath) {
		this.productImgPath = productImgPath;
	}

	public List<WmlProductImage> getProductImageList() {
		return productImageList;
	}

	public void setProductImageList(List<WmlProductImage> productImageList) {
		this.productImageList = productImageList;
	}
	public Integer getViewCount() {
		return viewCount;
	}
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public String getOnTime() {
		return onTime;
	}

	public void setOnTime(String onTime) {
		this.onTime = onTime;
	}

	public Integer getProductImgWidth() {
		return productImgWidth;
	}

	public void setProductImgWidth(Integer productImgWidth) {
		this.productImgWidth = productImgWidth;
	}

	public Integer getProductImgHeight() {
		return productImgHeight;
	}

	public void setProductImgHeight(Integer productImgHeight) {
		this.productImgHeight = productImgHeight;
	}
	

	

}
