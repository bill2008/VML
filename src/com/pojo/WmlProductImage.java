package com.pojo;

public class WmlProductImage {
	private Integer id;
	private String url;
	private String name;
	private Integer width;
	private Integer height;
	private Integer productId;
	private Integer isDel;
	private Integer isFirst;
	
	
	public WmlProductImage() {
		
	}
	
	public WmlProductImage(Integer id, String url, String name, Integer width,
			Integer height, Integer productId, Integer isDel, Integer isFirst) {
		super();
		this.id = id;
		this.url = url;
		this.name = name;
		this.width = width;
		this.height = height;
		this.productId = productId;
		this.isDel = isDel;
		this.isFirst = isFirst;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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

	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public Integer getIsFirst() {
		return isFirst;
	}
	public void setIsFirst(Integer isFirst) {
		this.isFirst = isFirst;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	

	

}
