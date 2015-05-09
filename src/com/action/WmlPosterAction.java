package com.action;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.pojo.WmlPoster;
import com.service.IWmlPosterService;
import com.tool.TimeUtil;

public class WmlPosterAction extends BaseAction {

	private IWmlPosterService wmlPosterService;
	private WmlPoster wmlPoster;//模型驱动
	private List<WmlPoster> data;
	private String message;
	
	//修改接受参数
	private String porductName;// 关联的商品ID
	private int id;
	private String name;
	private String createDate;
	private String description;
	private int isDel;
	private String imagePath;
	
	//文件上传
	private File myFile;
	private String myFileFileName;

	public WmlPoster getWmlPoster() {
		return wmlPoster;
	}

	public void setWmlPoster(WmlPoster wmlPoster) {
		this.wmlPoster = wmlPoster;
	}

	public List<WmlPoster> getData() {
		return data;
	}

	public void setData(List<WmlPoster> data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPorductName() {
		return porductName;
	}

	public void setPorductName(String porductName) {
		this.porductName = porductName;
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

	public void setWmlPosterService(IWmlPosterService wmlPosterService) {
		this.wmlPosterService = wmlPosterService;
	}

	/**
	 * 查询海报数据
	 * @return
	 * @throws Exception
	 */
	public String queryWmlPoster() throws Exception {
		data = wmlPosterService.queryWmlPosterList(wmlPoster);
		wmlPoster = null;
		return "success";
	}

	/**
	 * 修改海报数据
	 * @return
	 * @throws Exception
	 */
	public String updateWmlPoster() throws Exception {

		WmlPoster item = new WmlPoster();
		String result = "fail";
		try {
			if (myFile != null) {
				// 以后可以根据用户需要来创建单独的文件夹保存上传文件
				String uploadPath = ServletActionContext.getServletContext()
						.getRealPath("/posterUpload/");
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
			item.setPkgId(Integer.parseInt(porductName));
			item.setId(id);
			item.setName(name);
			item.setDescription(description);
			item.setIsDel(isDel);
			if (wmlPosterService.updateWmlPoster(item)) {
				result = "optsuccess";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 添加海报数据
	 * @return
	 */
	public String addWmlPoster() {
		String result = "fail";
		try {
			if (myFile != null) {
				// 以后可以根据用户需要来创建单独的文件夹保存上传文件
				String uploadPath = ServletActionContext.getServletContext()
						.getRealPath("/posterUpload/");
				File f1 = new File(uploadPath);
				if (!f1.exists()) {
					f1.mkdirs();
				}
				String extName = this.getMyFileFileName().substring(
						this.getMyFileFileName().lastIndexOf("."));
				String newFilename = UUID.randomUUID().toString();
				File file = new File(uploadPath + "\\" + newFilename + extName);
				FileUtils.copyFile(myFile, file);
				wmlPoster.setUrl(newFilename + extName);
				wmlPoster.setCreateDate(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
				wmlPoster.setPkgId(Integer.parseInt(porductName));
				if (wmlPosterService.addWmlPoster(wmlPoster)) {
					result = "optsuccess";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		wmlPoster = null;
		return result;
	}
}
