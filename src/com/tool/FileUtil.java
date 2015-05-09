package com.tool;

import java.io.File;


import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class FileUtil {
	public static void checkFilePath(String path){
		File dir=new File(path);
		if(!dir.exists()&&!dir.isDirectory()){
			dir.mkdir();
		}
	}
	public static  String uploadimage(File upload,String fileurl,String fileName){
		try {
			String extName = fileName.substring(fileName.lastIndexOf("."));
			String uploadPath = ServletActionContext.getServletContext().getRealPath("/productUpload/");
			FileUtil.checkFilePath(uploadPath);
			File toFile = new File(uploadPath+extName, fileName);
			FileUtils.copyFile(upload, toFile);
			
			return uploadPath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
