package com.action;

import java.util.List;

import com.pojo.WmlOrgan;
import com.pojo.WmlUser;
import com.service.IWmlOrganService;
import com.service.IWmlUserService;
import com.tool.Constant;

public class WmlOrganAction extends BaseAction {

	private IWmlOrganService wmlOrganService;
	private IWmlUserService wmlUserService;
	private WmlOrgan wmlOrgan;
	private int permissions;
	private int organId;
	private List<WmlOrgan> data;
	private String message;
	public WmlOrgan getWmlOrgan() {
		return wmlOrgan;
	}
	public void setWmlOrgan(WmlOrgan wmlOrgan) {
		this.wmlOrgan = wmlOrgan;
	}
	public List<WmlOrgan> getData() {
		return data;
	}
	public void setData(List<WmlOrgan> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getPermissions() {
		return permissions;
	}
	public void setPermissions(int permissions) {
		this.permissions = permissions;
	}
	public int getOrganId() {
		return organId;
	}
	public void setOrganId(int organId) {
		this.organId = organId;
	}
	public void setWmlOrganService(IWmlOrganService wmlOrganService) {
		this.wmlOrganService = wmlOrganService;
	}
	
	
	public void setWmlUserService(IWmlUserService wmlUserService) {
		this.wmlUserService = wmlUserService;
	}
	public String queryWmlOrgan() throws Exception{
		data=wmlOrganService.queryWmlOrganList(wmlOrgan);
		wmlOrgan=null;
		return "success";
	}
	public void updateWmlOrgan() throws Exception{
		WmlUser item=new WmlUser();
		item.setId(wmlOrgan.getUserId());
		item =  wmlUserService.queryWmlUser(item);
		if(item!=null){
			item.setOrgan(wmlOrgan.getId());
			item.setOrganName(wmlOrgan.getName());
			if(wmlOrganService.updateWmlOrgan(wmlOrgan)==Constant.MSG_SUCCESS){
				if(wmlUserService.updateWmlUser(item)==Constant.MSG_SUCCESS){
					message= "optsuccess";
				}else{
					message= "fail";
				}
			}else{
				message="orgNull";
			}
		}
		wmlOrgan=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
		
	}
	
	public void updateWmlOrganPermissions() throws Exception{
		WmlOrgan organItem= new WmlOrgan();
		organItem.setId(organId);
		organItem.setPermissions(permissions);
		if (permissions==1){
			//如果商户的免审核是设置从1为0(不能免审核），那商户里所有的客户都没有免审核权限了。
			WmlUser userItem= new WmlUser();
			userItem.setOrgan(organId);
			try {
				List<WmlUser> wmlUserList = wmlUserService.queryWmlUserList(userItem);
				if(!wmlUserList.isEmpty()){
					for(WmlUser wmlUser:wmlUserList){ 
						wmlUser.setPermissions(0);
						if (wmlUserService.updateWmlUserPermissions(wmlUser)!=Constant.MSG_SUCCESS){
							message= "fail";
						}
					} 
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		try{	
			if(wmlOrganService.updateWmlOrganPermissions(organItem)==Constant.MSG_SUCCESS){
				message= "optsuccess";
			}else{
				message= "fail";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		organItem=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
	}	
	
	public void deleteWmlOrgan()throws Exception{
		WmlOrgan organItem= new WmlOrgan();
		organItem.setId(organId);
		organItem=wmlOrganService.queryWmlOrgan(organItem);
		if(organItem!=null){
			organItem.setIsDel(0);
			if(wmlOrganService.updateWmlOrgan(organItem)==Constant.MSG_SUCCESS){
				message= "optsuccess";
			}else{
				message= "fail";
			}	
		}else{
			message="uidNull";
		}
		organItem=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
	}	
	
	public void addWmlOrgan() throws Exception{
		WmlUser item=new WmlUser();
		item.setId(wmlOrgan.getUserId());
		if(wmlUserService.queryWmlUser(item)!=null){
			if(wmlOrganService.addWmlOrgan(wmlOrgan)){
				message= "optsuccess";
			}else{
				message= "fail";
			}	
		}else{
			message="orgNull";
		}
		wmlOrgan=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
		
	}
}
