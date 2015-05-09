package com.action;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.pojo.WmlAdvertisement;
import com.service.IWmlAdvertisementService;
import com.tool.TimeUtil;

public class WmlAdvertisementAction extends BaseAction {
	
	private IWmlAdvertisementService wmlAdvertisementService;
	private WmlAdvertisement  wmlAdvertisement;
	private List<WmlAdvertisement> data;
	private String message;
	
	
	private String porductId;
	private int id;
	private  String name;
	private String createDate;
	private String description;
	private int isDel;
	private String imagePath;
	
	
	private File myFile;
	// myFileFileName属性用来封装上传文件的文件名  
    private String myFileFileName;
    
    
    
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
	public String getPorductId() {
		return porductId;
	}
	public void setPorductId(String porductId) {
		this.porductId = porductId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsDel() {
		return isDel;
	}
	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public WmlAdvertisement getWmlAdvertisement() {
		return wmlAdvertisement;
	}
	public void setWmlAdvertisement(WmlAdvertisement wmlAdvertisement) {
		this.wmlAdvertisement = wmlAdvertisement;
	}
	public List<WmlAdvertisement> getData() {
		return data;
	}
	public void setData(List<WmlAdvertisement> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setWmlAdvertisementService(
			IWmlAdvertisementService wmlAdvertisementService) {
		this.wmlAdvertisementService = wmlAdvertisementService;
	}
	
	public String queryWmlAdvertisement() throws Exception{
		data=wmlAdvertisementService.queryWmlAdvertisementList(wmlAdvertisement);
		return "success";
	}
	public String updateWmlAdvertisement() throws Exception{	
		String result = "fail";
		WmlAdvertisement item=new WmlAdvertisement();
		try {
			if (myFile != null) {
				// 以后可以根据用户需要来创建单独的文件夹保存上传文件
				String uploadPath = ServletActionContext.getServletContext()
						.getRealPath("/advertisementUpload/");
				File f1 = new File(uploadPath);
				if (!f1.exists()) {
					f1.mkdirs();
				}
				// 获取扩展名
				String extName = this.getMyFileFileName().substring(
						this.getMyFileFileName().lastIndexOf("."));
				// 重命名
				String newFilename = UUID.randomUUID().toString();
				File file = new File(uploadPath + "\\" + newFilename + extName);
				FileUtils.copyFile(myFile, file);
				item.setUrl(newFilename + extName);
			} else {
				item.setUrl(imagePath);
			}
			item.setCreateDate(createDate);
			item.setProductId(Integer.parseInt(porductId));
			item.setId(id);
			item.setName(name);
			item.setDescription(description);
			item.setIsDel(isDel);
			if (wmlAdvertisementService.updateWmlAdvertisement(item)) {
				result = "optsuccess";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String addWmlAdvertisement() throws Exception {
		String result = "fail";
		try {
			if (myFile != null) {
				// 以后可以根据用户需要来创建单独的文件夹保存上传文件
				String uploadPath = ServletActionContext.getServletContext()
						.getRealPath("/advertisementUpload/");
				File f1 = new File(uploadPath);
				if (!f1.exists()) {
					f1.mkdirs();
				}
				String extName = this.getMyFileFileName().substring(
						this.getMyFileFileName().lastIndexOf("."));
				String newFilename = UUID.randomUUID().toString();
				File file = new File(uploadPath + "\\" + newFilename + extName);
				FileUtils.copyFile(myFile, file);
				wmlAdvertisement.setProductId(Integer.parseInt(porductId));
				wmlAdvertisement.setUrl(newFilename + extName);
				wmlAdvertisement.setCreateDate(TimeUtil	.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
				if (wmlAdvertisementService
						.addWmlAdvertisement(wmlAdvertisement)) {
					result = "optsuccess";
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		wmlAdvertisement=null;
		return result;
	}

}
