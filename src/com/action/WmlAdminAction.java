package com.action;

import java.util.List;

import com.pojo.WmlAdmin;
import com.service.IWmlAdminService;
import com.tool.Constant;
import com.tool.MD5Util;

public class WmlAdminAction extends BaseAction {

	private IWmlAdminService wmlAdminService;
	private WmlAdmin wmladmin;
	private List<WmlAdmin> data;
	private String message;
	private String loginName;
	private String userPwd;
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
		if(wmlAdminService.addWmlAdmin(wmladmin)){
			message= "optsuccess";
		}else{
			message= "fail";
		}	
		wmladmin=null;
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
				if(this.session.containsKey("admin")){
					message="该用户已经登录";
					return "loginfail";
				}else{
					this.session.put("admin", item);
					item=null;
					return "login";
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
		this.session.remove("admin");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "outLogin";
	}
	
}
