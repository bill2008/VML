package com.action;

import java.util.List;

import com.pojo.WmlAdmin;
import com.pojo.WmlUser;
import com.service.IWmlAdminService;
import com.service.IWmlUserService;
import com.tool.Constant;
import com.tool.MD5Util;
import com.tool.SessionHandler;

public class WmlAdminAction extends BaseAction {

	private IWmlAdminService wmlAdminService;
	private IWmlUserService wmlUserService;
	private WmlAdmin wmladmin;
	private List<WmlAdmin> data;
	private String message;
	private int id;
	private String loginName;
	private String userPwd;
	private int status;
	private int updatePrice;
	public WmlAdmin getWmladmin() {
		return wmladmin;
	}
	public void setWmladmin(WmlAdmin wmladmin) {
		this.wmladmin = wmladmin;
	}
	public List<WmlAdmin> getData() {
		return data;
	}
	public void setData(List<WmlAdmin> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setWmlAdminService(IWmlAdminService wmlAdminService) {
		this.wmlAdminService = wmlAdminService;
	}
	
	
	public void setWmlUserService(IWmlUserService wmlUserService) {
		this.wmlUserService = wmlUserService;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	
	public int getUpdatePrice() {
		return updatePrice;
	}
	public void setUpdatePrice(int updatePrice) {
		this.updatePrice = updatePrice;
	}
	public String queryWmlAdmin() throws Exception {
		data=wmlAdminService.queryWmlAdminList(wmladmin);
		wmladmin=null;
		return "success";
	}
	public void updateWmlAdmin() throws Exception{
		String password=wmladmin.getPassword();
		wmladmin.setPassword(MD5Util.getMD5(password));
		if(wmlAdminService.updateWmlAdmin(wmladmin)){
			message= "optsuccess";
		}else{
			message= "fail";
		}	
		wmladmin=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);	
	}
	
	public void addWmlAdmin()throws Exception{
		//添加系统操作员的时候，首先要在User表里追加一条新记录，返回一个ID作为
		//插入Admin表的ID值。（操作员的ID和User里的记录是相同ID号）
		WmlUser userItem = new WmlUser();
		userItem.setName(wmladmin.getName());
		userItem.setLoginName(wmladmin.getLoginName());
		userItem.setPassword(wmladmin.getPassword());
		
		int id = wmlUserService.addWmlUserRetID(userItem);
		
		if (id!=0) {
			wmladmin.setId(id);
			if(wmlAdminService.addWmlAdmin(wmladmin)){
				message= "optsuccess";
			}else{
				message= "fail";
			}	
		}else{
			message= "fail";
		}	
		
		wmladmin=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
	}
	
	public void updateWmlAdminStatus() throws Exception{
		
		WmlAdmin item = new WmlAdmin();
		item.setId(id);
		item = wmlAdminService.queryWmlAdmin(item);
		item.setStatus(status);
		try {
			if(wmlAdminService.updateWmlAdmin(item)){
				message= "optsuccess";
			}else{
				message= "fail";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);		
		
	}
	
	public void updateWmlAdminUpdatePrice() throws Exception{
		
		WmlAdmin item = new WmlAdmin();
		item.setId(id);
		item = wmlAdminService.queryWmlAdmin(item);
		item.setUpdatePrice(updatePrice);
		try {
			if(wmlAdminService.updateWmlAdmin(item)){
				message= "optsuccess";
			}else{
				message= "fail";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);		
		
	}	
	
	public String loginAdmin(){
		if(loginName!=null && userPwd!=null){
			WmlAdmin wmlUser=new WmlAdmin();
			wmlUser.setLoginName(loginName);
			wmlUser.setPassword(MD5Util.getMD5(userPwd));
			wmlUser.setStatus(Constant.DELETE);
			WmlAdmin item=wmlAdminService.queryWmlAdmin(wmlUser);
			if(item!=null){
				if(SessionHandler.exitsUser(String.valueOf(item.getId()),this.request.getSession())){
					this.session.put("admin", item);
					item=null;
					return "login";
				}else{
					message="该用户已经登录";
					return "loginfail";
				}
			}else{
				message="帐号密码错误";
				return "loginfail";	
			}

		}
		return "resultlogin";			
	}
	public String outLogin() {
		try{
		WmlAdmin admin=	(WmlAdmin) this.session.get("admin");
		this.session.remove("admin");
		SessionHandler.removeUserFromSessionMap(admin.getId().toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		return "outLogin";
	}
	
}
