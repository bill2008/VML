package com.pojo;

public class WmlBrand {
	private Integer id;
	private String createDate;
	private String endDate;
	private String name;
	private String letter;
	private String keyword;
	private Integer isDel;
	
	public WmlBrand() {
	
	}

	

	public WmlBrand(Integer id, String createDate, String endDate, String name,
			String letter, String keyword, Integer isDel) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.endDate = endDate;
		this.name = name;
		this.letter = letter;
		this.keyword = keyword;
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

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}



	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
}
