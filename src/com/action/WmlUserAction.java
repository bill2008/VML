package com.action;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.grid.tool.GridServerHandler;
import com.pojo.WmlUser;
import com.service.IWmlUserService;
import com.tool.Constant;

public class WmlUserAction extends BaseAction {
	
	private IWmlUserService wmlUserService;
	private WmlUser wmlUser;
	private WmlUser wmlUserDetail;
	private List<WmlUser> data;
	private String message;
	private String userId;//二级域名的用户名
	
	private int useId;
	private String createDate;
	private int organ;
	private String loginName;
	private String phone;
	private String password;
	private String name;
	private int status;
	private int channel;
	private String type;
	private int permissions;
	private int uid;
	private int isDel;	
	private String signature;
	private String  uploadFlag;
	private String lastDate;
	
	public String getUploadFlag() {
		return uploadFlag;
	}
	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public int getUseId() {
		return useId;
	}
	public void setUseId(int useId) {
		this.useId = useId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public int getOrgan() {
		return organ;
	}
	public void setOrgan(int organ) {
		this.organ = organ;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Integer getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPermissions() {
		return permissions;
	}
	public void setPermissions(int permissions) {
		this.permissions = permissions;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}

	//文件上传
	private File myFile;
	private String myFileFileName;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public WmlUser getWmlUserDetail() {
		return wmlUserDetail;
	}
	public void setWmlUserDetail(WmlUser wmlUserDetail) {
		this.wmlUserDetail = wmlUserDetail;
	}
	public WmlUser getWmlUser() {
		return wmlUser;
	}
	public void setWmlUser(WmlUser wmlUser) {
		this.wmlUser = wmlUser;
	}
	public List<WmlUser> getData() {
		return data;
	}
	public void setData(List<WmlUser> data) {
		this.data = data;
	}

	public File getMyFile() {
		return myFile;
	}
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	public String getMyFileFileName() {
		return myFileFileName;
	}
	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setWmlUserService(IWmlUserService wmlUserService) {
		this.wmlUserService = wmlUserService;
	}	

	public String queryWmlUser(){
		try{
			data=wmlUserService.queryWmlUserList(wmlUser);
			wmlUser=null;
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}
	public void queryWmlUserPage() throws Exception{
		
		List<WmlUser> itemList=null;
		GridServerHandler gridServerHandler=new GridServerHandler(request,response);
		int totalRowNum=gridServerHandler.getTotalRowNum();
		if (totalRowNum<1){
			totalRowNum=wmlUserService.getWmlUserCount(wmlUser);
			gridServerHandler.setTotalRowNum(totalRowNum);
			if(totalRowNum==1){
				itemList=wmlUserService.queryWmlUserList(wmlUser);
				gridServerHandler.setData(itemList,WmlUser.class);
			}else{
				itemList=wmlUserService.queryWmlUserPage(wmlUser, gridServerHandler.getStartRowNum(),gridServerHandler.getPageSize());
				gridServerHandler.setData(itemList,WmlUser.class);
			}
			
		}else{
			itemList=wmlUserService.queryWmlUserPage(wmlUser, gridServerHandler.getStartRowNum(),gridServerHandler.getPageSize());
			gridServerHandler.setData(itemList,WmlUser.class);
		}
		
		//wmlProduct=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(gridServerHandler.getLoadResponseText());
		
	}
	
	public String updateWmlUser() throws Exception{
		WmlUser userItem= new WmlUser();
		try{
			userItem.setId(useId);
			userItem.setChannel(channel);
			userItem.setCreateDate(createDate);
			userItem.setUid(uid);
			userItem.setLoginName(loginName);
			userItem.setName(name);
			userItem.setSignature(signature);
			userItem.setPhone(phone);
			userItem.setType(type);
			userItem.setOrgan(organ);
			userItem.setPermissions(permissions);
			userItem.setStatus(status);
			userItem.setIsDel(isDel);
			userItem.setLastDate(lastDate);
			userItem.setUploadFlag(uploadFlag);
			try {
				if (myFile != null) {
					// 以后可以根据用户需要来创建单独的文件夹保存上传文件
					String uploadPath = ServletActionContext.getServletContext()
							.getRealPath("/userpic/");
					File f1 = new File(uploadPath);
					if (!f1.exists()) {
						f1.mkdirs();
					}
					String extName = this.getMyFileFileName().substring(
							this.getMyFileFileName().lastIndexOf("."));
					String newFilename = userItem.getId().toString();
					File file = new File(uploadPath + "\\" + newFilename + extName);
					FileUtils.copyFile(myFile, file);
					userItem.setHead(newFilename + extName);
				}
			} catch (Exception e) {
				message="头像上传失败";
				e.printStackTrace();
			}
		
			if(uid!=0){
				WmlUser item= new WmlUser();
				item.setId(uid);
				if(wmlUserService.queryWmlUser(item)==null){
					message="推荐人信息不存在";
				}else{
					if(wmlUserService.updateWmlUser(userItem)==Constant.MSG_SUCCESS){
						message= "添加成功";
					}else{
						message= "添加失败";
					}
				}
			}else{
				if(wmlUserService.updateWmlUser(userItem)==Constant.MSG_SUCCESS){
					message= "添加成功";
				}else{
					message= "添加失败";
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		wmlUser=null;
		return "update";
	}

	public void updateWmlUserPermissions() throws Exception{
		WmlUser userItem= new WmlUser();
		userItem.setId(useId);
		userItem.setPermissions(permissions);
		try {
			if(wmlUserService.updateWmlUserPermissions(userItem)==Constant.MSG_SUCCESS){
				message= "optsuccess";
			}else{
				message= "fail";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		wmlUser=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
	}
	
	public void addWmlUser()throws Exception{
		WmlUser item= new WmlUser();
		item.setId(wmlUser.getUid());
		
		if(wmlUserService.queryWmlUser(item)!=null){
			if(wmlUserService.addWmlUser(wmlUser)==Constant.MSG_SUCCESS){
				message= "optsuccess";
			}else{
				message= "fail";
			}	
		}else{
			message="uidNull";
		}
		wmlUser=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
	}
	
	public String queryUserDetail() throws Exception{
		wmlUserDetail=wmlUserService.queryWmlUser(wmlUser);		
		wmlUser=null;
		return "userDetail";
	}
	
	/**
	 * 个人主页数据查询方法
	 * @return
	 * @throws Exception
	 */
	public String myWmlUser() throws Exception{
		System.out.println(userId);
		return "myIndex";
	}

}
