package com.pojo;

public class WmlUseEvaluation {
	private Integer id;
	private Integer productId;
	private Integer authorId;
	private Integer parentId;
	private String createDate;
	private String endDate;
	private String content;
	private Integer noticeParentIdFlag;
	private Integer noticeProductOwnerFlag;
	private Integer isDel;
	
	public WmlUseEvaluation() {
	
	}

	public WmlUseEvaluation(Integer id, Integer productId, Integer authorId, Integer parentId,
			String createDate, String endDate, String content,
			Integer noticeParentIdFlag, Integer noticeProductOwnerFlag, Integer isDel) {
		super();
		this.id = id;
		this.productId = productId;
		this.authorId = authorId;
		this.parentId = parentId;
		this.createDate = createDate;
		this.endDate = endDate;
		this.content = content;
		this.noticeParentIdFlag = noticeParentIdFlag;
		this.noticeProductOwnerFlag = noticeProductOwnerFlag;
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
	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getNoticeParentIdFlag() {
		return noticeParentIdFlag;
	}
	public void setNoticeParentIdFlag(Integer noticeParentIdFlag) {
		this.noticeParentIdFlag = noticeParentIdFlag;
	}
	public Integer getNoticeProductOwnerFlag() {
		return noticeProductOwnerFlag;
	}
	public void setNoticeProductOwnerFlag(Integer noticeProductOwnerFlag) {
		this.noticeProductOwnerFlag = noticeProductOwnerFlag;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	

}
