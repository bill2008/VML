package com.tool;

/**
 * 通用zTree数据格式
 * @author Administrator
 *
 */
public class CommTreeInfo {
	
	
	private String id;
	private String pId;
	private String name;
	private String target;
	private boolean open;
	private boolean isParent;
	private String url;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isParent() {
		return isParent;
	}
	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
