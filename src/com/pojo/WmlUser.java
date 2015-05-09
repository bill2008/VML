package com.pojo;

public class WmlUser {

	private Integer id;
	private String createDate;//创建时间
	private String endDate;//用户查询创建时间的结束时间
	private Integer uid;
	private String uidName;
	private String lastDate;//最近登录时间
	private String lastTime;//查询登录时间的结束时间
	private String loginName;
	private String name;
	private String password;
	private String phone;
	private String head;
	private String type;
	private String signature;//个性签名
	private Integer organ;
	private String organName;
	private Integer permissions;
	private Integer channel;
	private String uploadFlag;
	private Integer status;
	private Integer isDel;
	public WmlUser() {
	}
	public WmlUser(Integer id, String createDate, String endDate, Integer uid,
			String lastDate, String lastTime, String loginName, String name,
			String password, String phone, String head, String type,
			String signature, Integer organ, Integer permissions,
			Integer channel, String uploadFlag, Integer status, Integer isDel) {
		this.id = id;
		this.createDate = createDate;
		this.endDate = endDate;
		this.uid = uid;
		this.lastDate = lastDate;
		this.lastTime = lastTime;
		this.loginName = loginName;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.head = head;
		this.type = type;
		this.signature = signature;
		this.organ = organ;
		this.permissions = permissions;
		this.channel = channel;
		this.uploadFlag = uploadFlag;
		this.status = status;
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
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public Integer getOrgan() {
		return organ;
	}
	public void setOrgan(Integer organ) {
		this.organ = organ;
	}
	public Integer getPermissions() {
		return permissions;
	}
	public void setPermissions(Integer permissions) {
		this.permissions = permissions;
	}
	public Integer getChannel() {
		return channel;
	}
	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	public String getUploadFlag() {
		return uploadFlag;
	}
	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	public String getUidName() {
		return uidName;
	}
	public void setUidName(String uidName) {
		this.uidName = uidName;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	
}
