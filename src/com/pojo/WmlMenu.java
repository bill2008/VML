package com.pojo;

/**
 * ErpMenu entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class WmlMenu implements java.io.Serializable {

	// Fields

	private Integer menuId;
	private String menuName;
	private String menuLink;
	private Integer menuAid;
	private Integer menuNo;

	// Constructors

	/** default constructor */
	public WmlMenu() {
	}

	/** full constructor */

	public WmlMenu(Integer menuId, String menuName, String menuLink,
			Integer menuAid, Integer menuNo) {
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuLink = menuLink;
		this.menuAid = menuAid;
		this.menuNo = menuNo;
	}

	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuLink() {
		return this.menuLink;
	}

	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}

	public Integer getMenuAid() {
		return this.menuAid;
	}

	public void setMenuAid(Integer menuAid) {
		this.menuAid = menuAid;
	}

	public Integer getMenuNo() {
		return this.menuNo;
	}

	public void setMenuNo(Integer menuNo) {
		this.menuNo = menuNo;
	}

}