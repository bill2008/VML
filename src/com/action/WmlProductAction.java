package com.action;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;







import com.grid.tool.GridServerHandler;
import com.grid.tool.GridSupport;
import com.opensymphony.xwork2.ActionContext;
import com.pojo.WmlAdmin;
import com.pojo.WmlProduct;
import com.pojo.WmlProductImage;
import com.service.IWmlProductImageService;
import com.service.IWmlProductService;
import com.tool.Constant;
import com.tool.FileUtil;
import com.tool.PicInfo;
import com.tool.TimeUtil;


public class WmlProductAction extends BaseAction {

	private IWmlProductService wmlProductService;
	private IWmlProductImageService wmlProductImageService;
	private WmlProduct wmlProduct;//接受数据对象
	private WmlProduct product;//返回界面
	private List<WmlProduct> data;
	private List<WmlProductImage> imagePathList;
	private String message;
	
	private List<File> Filedata;
	private List<String> FiledataFileName;
	private List<String> FiledataContentType;
	private List<PicInfo> FiledataContentPath=new ArrayList<PicInfo>();
	private String productId;
	private String productName;
	private String productType;
	private String timestr;
	private String operator;
	private String status;
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getTimestr() {
		return timestr;
	}
	public void setTimestr(String timestr) {
		this.timestr = timestr;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public WmlProduct getProduct() {
		return product;
	}
	public void setProduct(WmlProduct product) {
		this.product = product;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<WmlProductImage> getImagePathList() {
		return imagePathList;
	}
	public void setImagePathList(List<WmlProductImage> imagePathList) {
		this.imagePathList = imagePathList;
	}

	public List<String> getFiledataContentType() {
		return FiledataContentType;
	}
	public void setFiledataContentType(List<String> filedataContentType) {
		FiledataContentType = filedataContentType;
	}
	public List<File> getFiledata() {
		return Filedata;
	}
	public void setFiledata(List<File> filedata) {
		Filedata = filedata;
	}
	public List<String> getFiledataFileName() {
		return FiledataFileName;
	}
	public void setFiledataFileName(List<String> filedataFileName) {
		FiledataFileName = filedataFileName;
	}

	public List<PicInfo> getFiledataContentPath() {
		return FiledataContentPath;
	}
	public void setFiledataContentPath(List<PicInfo> filedataContentPath) {
		FiledataContentPath = filedataContentPath;
	}
	public WmlProduct getWmlProduct() {
		return wmlProduct;
	}
	public void setWmlProduct(WmlProduct wmlProduct) {
		this.wmlProduct = wmlProduct;
	}
	public List<WmlProduct> getData() {
		return data;
	}
	public void setData(List<WmlProduct> data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setWmlProductService(IWmlProductService wmlProductService) {
		this.wmlProductService = wmlProductService;
	}
	
	public void setWmlProductImageService(IWmlProductImageService wmlProductImageService) {
		this.wmlProductImageService = wmlProductImageService;
	}
	
	public void deleteWmlProduct()throws Exception{
		WmlProduct item = new WmlProduct();
		WmlProductImage imageItem = new WmlProductImage();
		int Id = Integer.parseInt(productId);
		imageItem.setProductId(Id);
		List<WmlProductImage> imageList = wmlProductImageService.queryWmlProductImageList(imageItem);
		item.setId(Id);
		item=wmlProductService.queryWmlProduct(item);
		if(item!=null){
			item.setIsDel(Constant.isDelete);
			item.setLastModifyDate(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
			if(wmlProductService.updateWmlProduct(item)==Constant.MSG_SUCCESS){
				for(int i=0;i<imageList.size();i++){
					imageItem=imageList.get(i);
					imageItem.setIsDel(Constant.isDelete);
					wmlProductImageService.updateWmlProductImage(imageItem);
				}
				message= "optsuccess";
			}else{
				message= "fail";
			}	
		}else{
			message="productIdNull";
		}
		wmlProduct = null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
	}
	
	public void updateWmlProductByStatus() throws Exception{
		WmlProduct item = new WmlProduct();
		int Id = Integer.parseInt(productId);
		item.setId(Id);
		item=wmlProductService.queryWmlProduct(item);
		
		int stat=Integer.parseInt(status);
		if (stat==0){
			item.setStatus(Constant.isON);
			item.setOnTime(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
			item.setLastModifyDate(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
		}else if (stat==1){
			item.setStatus(Constant.isDOWN);
			item.setOnTime(null);
			item.setLastModifyDate(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
		}else if (stat==2){
			item.setStatus(Constant.isUNREVIEW);
			item.setOnTime(null);
			item.setLastModifyDate(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
		}

		if(wmlProductService.updateWmlProduct(item)==Constant.MSG_SUCCESS){
			message= "optsuccess";
		}else{
			message= "fail";
		}	
		wmlProduct = null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
	}
	
	
	//查询所有商品信息
	public String queryWmlProduct()  {
		try{
		data=wmlProductService.queryWmlProductList(wmlProduct);
		}catch(Exception e){
			e.printStackTrace();
		}
		wmlProduct=null;
		return "success";
	}
	
	
	public String updateWmlProdutInfo() throws IOException{
		WmlAdmin wmladmin=(WmlAdmin)this.session.get("admin");
		if(wmladmin!=null){
			GridSupport grid=new GridSupport(this._gt_json);
			List<WmlProduct> productList= grid.getParamRecords(GridSupport.RECORD_UPDATE, WmlProduct.class);
			
			for(WmlProduct item:productList){
				if(item.getPrice()!=0 &&wmladmin.getUpdatePrice()==0){
				message=wmlProductService.updateWmlProduct(item);
				}else{
					message= "此帐号没有编辑价格权限";
					break;
				}
			}
		
		}else{
			message="会话过期,请重新登录!";
		}
	return "success";
	}


	public void addProductPic(File productDir, File saveDir) throws Exception{
		//添加新图片信息
		File fa[] = saveDir.listFiles();
		int fileCount=fa.length;
		if (fileCount>0){
			for (int i = 0; i < fileCount; i++) {
				File fs = fa[i];
				String filename=fs.getName();
				String obj="smaller";
				int index=filename.lastIndexOf(".");
				if (!(filename.substring(index-7,index)).equals(obj)){
					//例：20141208_H650_W650_1.jpg
					try{
						String[] str=filename.split("_");
						WmlProductImage productImage= new WmlProductImage();
						productImage.setProductId(wmlProduct.getId());
						productImage.setIsFirst(Integer.parseInt(str[3].substring(0,str[3].indexOf("."))));
						productImage.setIsDel(Constant.isDelete);
						productImage=wmlProductImageService.queryWmlProductImage(productImage);
						if (productImage!=null){
							productImage.setUrl(filename);
							productImage.setHeight(Integer.parseInt(str[1].substring(1,str[1].length())));
							productImage.setWidth(Integer.parseInt(str[2].substring(1,str[2].length())));
							productImage.setName(wmlProduct.getName()+"_"+productImage.getIsFirst());
							productImage.setIsDel(Constant.DELETE);
							wmlProductImageService.updateWmlProductImage(productImage);
						}else{
							productImage= new WmlProductImage();
							productImage.setProductId(wmlProduct.getId());
							productImage.setIsFirst(Integer.parseInt(str[3].substring(0,str[3].indexOf("."))));
							productImage.setUrl(filename);
							productImage.setHeight(Integer.parseInt(str[1].substring(1,str[1].length())));
							productImage.setWidth(Integer.parseInt(str[2].substring(1,str[2].length())));
							productImage.setName(wmlProduct.getName()+"_"+productImage.getIsFirst());
							productImage.setIsDel(Constant.DELETE);
							wmlProductImageService.addWmlProductImage(productImage);
						}
					
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
			//更改临时上传目录名为产品目名
			saveDir.renameTo(productDir);
		}
		
	}
	
	
	//修改商品信息
	public void updateWmlProduct() throws Exception{
		WmlAdmin wmladmin=(WmlAdmin)this.session.get("admin");
		if(wmladmin!=null){
			ActionContext ac=ActionContext.getContext();
			ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
			String savePath = sc.getRealPath("/") + "productUpload";
			String dirPath = savePath+"\\"+productType+"\\"+timestr+"\\"+productId+"\\";
			savePath=savePath+"\\"+productType+"\\"+timestr+"\\"+productId+"_"+operator+"\\";
			
			
			if(wmladmin.getUpdatePrice()==1){
				WmlProduct product= new WmlProduct();
				product.setId(wmlProduct.getId());
				product = wmlProductService.queryWmlProduct(product);
				product.setLastModifyDate(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
				product.setName(wmlProduct.getName());
				product.setPrice(wmlProduct.getPrice());
				product.setTid(wmlProduct.getTid());
				product.setOid(wmlProduct.getOid());
				product.setBid(wmlProduct.getBid());
				product.setProperty(wmlProduct.getProperty());
				product.setDescription(wmlProduct.getDescription());
				product.setUploadType(wmlProduct.getUploadType());				
				
				File productDir=new File(dirPath);
				File saveDir = new File(savePath);
				
				if  (productDir .exists()  && productDir .isDirectory())
				{
					//商品目录已存在
					if (saveDir .exists()  && saveDir .isDirectory()){
						//临时保存目录已存在
						if(wmlProductService.updateWmlProduct(product).equals(Constant.MSG_SUCCESS)){
							WmlProductImage ImageItem= new WmlProductImage();
							ImageItem.setProductId(wmlProduct.getId());
							//查询旧图片信息
							List<WmlProductImage> oldImageList=wmlProductImageService.queryWmlProductImageList(ImageItem);
							for(WmlProductImage img:oldImageList){
								//删除旧文件
								String filename =dirPath + "\\"+ img.getUrl();
								File targetFile = new File(filename); 
								if (targetFile.isFile()) {
			                        targetFile.delete();
				                }
								//设置旧文件的数据isDel为删除并更新
								img.setIsDel(Constant.isDelete);
								wmlProductImageService.updateWmlProductImage(img);
							}
							//删除原产品目录
							File originalDir=new File(dirPath);
							if(originalDir.isDirectory()){
								File originalfa[] = originalDir.listFiles();
								int originalFileCount=originalfa.length;
								if (originalFileCount>0){
									for (int i = 0; i < originalFileCount; i++) {
										File fs = originalfa[i];
										fs.delete();
									}
								}
								originalDir.delete();
							}
							//添加更新的商品图片
							addProductPic(productDir,saveDir);
							message="修改成功";
						}else{
							message="修改失败";
						}
					}else{
						//临时目录不存在（没有上传新图片）
						if(wmlProductService.updateWmlProduct(product).equals(Constant.MSG_SUCCESS)){
							message="修改成功";
						}else{
							message="修改失败";
						}
					}
				}else{
					//产品目录不存在（没有原产品的图片）
					if (saveDir .exists()  && saveDir .isDirectory()){
						//上传了图片，临时目录已存在
						if(wmlProductService.updateWmlProduct(product).equals(Constant.MSG_SUCCESS)){
							addProductPic(productDir,saveDir);
							message="修改成功";
						}else{
							message="修改失败";
						}
					}else{
						if(wmlProductService.updateWmlProduct(product).equals(Constant.MSG_SUCCESS)){
							message="修改成功";
						}else{
							message="修改失败";
						}
					}
				}
			}else{
				message= "此帐号没有编辑价格权限";
			}
		}else{
			message="会话过期,请重新登录!";
		}
		wmlProduct=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
	}
	
	//修改商品信息
	public void cancelUpdateWmlProduct() throws Exception{	
		ActionContext ac=ActionContext.getContext();
		ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
		String savePath = sc.getRealPath("/") + "productUpload";
		savePath=savePath+"\\"+productType+"\\"+timestr+"\\"+productId+"_"+operator+"\\";		
		File dir=new File(savePath);
		if  (dir .exists()  && dir .isDirectory()) {
			File fa[] = dir.listFiles();
			int fileCount=fa.length;
			if (fileCount>0){
				for (int i = 0; i < fileCount; i++) {
					fa[i].delete();
				}
			}
			dir.delete();
		}
	}
	
	//图片添加
	public String productUploadAdd() {
		String timePath=TimeUtil.getCurrentTime("yyyyMMdd");
		String productId="tempID";
		if(operator!=null){
			
			ActionContext ac=ActionContext.getContext();
			ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
			String savePath = sc.getRealPath("/");
			savePath=savePath+ "productUpload"+"\\"+productType+"\\"+timePath+"\\"+productId+"_"+operator+"\\";
		
			int size = Filedata.size();
			if (size == 0)
				return "ERROR";
			String extName = null;
			String name = null;
			//设置上传图片的序列号
			File dir=new File(savePath);
			int imgNo=1;
			if  (!dir .exists()  && !dir .isDirectory())      
			{
				dir.mkdirs();
			}else{
				File fa[] = dir.listFiles();
				int fileCount=fa.length;
				if (fileCount>0){
					for (int i = 0; i < fileCount; i++) {
						File fs = fa[i];
						String filename=fs.getName();
						String obj="smaller";
						int index=filename.lastIndexOf(".");
						if (!(filename.substring(index-7,index)).equals(obj)){
							imgNo=imgNo+1;
						}
					}
				}
			}
			
			for (int i = 0; i < size; i++) {
				PicInfo picInfo= new PicInfo();
				extName = FiledataFileName.get(i).substring(FiledataFileName.get(i).lastIndexOf("."));
				name = UUID.randomUUID().toString();
				File file = new File(savePath + name + extName);
				try {
					FileUtils.copyFile(Filedata.get(i), file);
					picInfo=ReNamePicture(timePath,savePath, name, extName,imgNo);	
					name=picInfo.getPicUrl();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			try {
				response.getWriter().print(name);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//取消图片添加
	public void cancelAddWmlProduct() throws Exception{
		String timePath=TimeUtil.getCurrentTime("yyyyMMdd");
		String productId="tempID";
		ActionContext ac=ActionContext.getContext();
		ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
		String savePath = sc.getRealPath("/") + "productUpload";
		savePath=savePath+"\\"+productType+"\\"+timePath+"\\"+productId+"_"+operator+"\\";		
		File dir=new File(savePath);
		if  (dir .exists()  && dir .isDirectory()) {
			File fa[] = dir.listFiles();
			int fileCount=fa.length;
			if (fileCount>0){
				for (int i = 0; i < fileCount; i++) {
					fa[i].delete();
				}
			}
			dir.delete();
		}
	}	
	/**
	 * 修改商品重新上传
	 * @return
	 */
	public String UploadProductUpdate() {
		if(operator!=null){
			ActionContext ac=ActionContext.getContext();
			ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
			String savePath = sc.getRealPath("/") + "productUpload";
			
			savePath=savePath+"\\"+productType+"\\"+timestr+"\\"+productId+"_"+operator+"\\";
			int size = Filedata.size();
			if (size == 0)
				return "ERROR";
			String extName = null;
			String name = null;
			
			//设置上传图片的序列号
			File dir=new File(savePath);
			int imgNo=1;
			if  (!dir .exists()  && !dir .isDirectory())      
			{
				dir.mkdirs();
			}else{
				File fa[] = dir.listFiles();
				int fileCount=fa.length;
				if (fileCount>0){
					for (int i = 0; i < fileCount; i++) {
						File fs = fa[i];
						String filename=fs.getName();
						String obj="smaller";
						int index=filename.lastIndexOf(".");
						if (!(filename.substring(index-7,index)).equals(obj)){
							imgNo=imgNo+1;
						}
					}
				}
			}
			//先把上传图片保存到临时目录<base>/productType/timestr/productId/operator中去
			for (int i = 0; i < size; i++) {
				PicInfo picInfo= new PicInfo();
				extName = FiledataFileName.get(i).substring(FiledataFileName.get(i).lastIndexOf("."));
				name = UUID.randomUUID().toString();
				File file = new File(savePath + name + extName);
				try {

					FileUtil.doCopyFile(Filedata.get(i), file,false);
					picInfo=ReNamePicture(getTimestr(),savePath, name, extName,imgNo);	
					name=picInfo.getPicUrl();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			HttpServletResponse response = ServletActionContext.getResponse();
			try {
				response.getWriter().print(name);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//重名称
	public PicInfo ReNamePicture(String timePath,String filePath,String pictureName,String extName,int imgNo) throws IOException{
		PicInfo pic= new PicInfo();
		File f = new File(filePath+pictureName+extName);
		Image src = javax.imageio.ImageIO.read(f);

		String picWidth=String.valueOf(src.getWidth(null));
		String picHeight=String.valueOf(src.getHeight(null));
		
		String picPath=filePath+timePath+"_H"+picHeight+"_W"+picWidth+"_"+String.valueOf(imgNo)+extName;
		String picNamePath=timePath+"_H"+picHeight+"_W"+picWidth+"_"+String.valueOf(imgNo)+extName;
		File newfile=new File(picPath);
		FileUtils.copyFile(f, newfile);
		f.delete();
		pic.setPicHeight(picHeight);
		pic.setPicWidth(picWidth);
		pic.setPicUrl(picNamePath);
		
		return pic;
	}
	//添加商品信息
	public void addWmlProduct() throws Exception{
		String productId="tempID";
		WmlAdmin wmladmin=(WmlAdmin)this.session.get("admin");
		
		if(wmladmin!=null){
			ActionContext ac=ActionContext.getContext();
			ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
			String savePath = sc.getRealPath("/") + "productUpload";
			String timestr=TimeUtil.getCurrentTime("yyyyMMdd");
			savePath=savePath+"\\"+productType+"\\"+timestr+"\\"+productId+"_"+operator+"\\";
			
			wmlProduct.setDownload(0);
			wmlProduct.setForwar(0);
			wmlProduct.setCollect(0);
			wmlProduct.setIsDel(Constant.DELETE);
			wmlProduct.setCreateDate(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
			if(wmladmin.getUpdatePrice()==1){
				wmlProduct.setOnTime(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
				wmlProduct.setStatus(Constant.isON);
			}else{
				wmlProduct.setOnTime(null);
				wmlProduct.setStatus(Constant.isUNREVIEW);
			}
			wmlProduct.setLastModifyDate(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
			wmlProduct.setUid(wmladmin.getId());
			WmlProduct pro= new WmlProduct();
	
			if(wmlProductService.addWmlProduct(wmlProduct).equals("success")){
				pro=wmlProductService.queryWmlProduct(wmlProduct);
				File dir=new File(savePath);
				File fa[] = dir.listFiles();
				int fileCount=fa.length;
				if (fileCount>0){
					for (int i = 0; i < fileCount; i++) {
						File fs = fa[i];
						String filename=fs.getName();
						String obj="smaller";
						int index=filename.lastIndexOf(".");
						if (!(filename.substring(index-7,index)).equals(obj)){
							//例：20141208_H650_W650_1.jpg
							try{
								String[] str=filename.split("_");
								WmlProductImage productImage= new WmlProductImage();
								productImage.setProductId(pro.getId());
								productImage.setIsFirst(Integer.parseInt(str[3].substring(0,str[3].indexOf("."))));
								productImage.setIsDel(Constant.DELETE);
								productImage.setUrl(filename);
								productImage.setHeight(Integer.parseInt(str[1].substring(1,str[1].length())));
								productImage.setWidth(Integer.parseInt(str[2].substring(1,str[2].length())));
								productImage.setName(wmlProduct.getName()+"_"+productImage.getIsFirst());
								wmlProductImageService.addWmlProductImage(productImage);
								//更改临时上传目录名为产品目名
							
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}
					savePath=sc.getRealPath("/") + "productUpload"+"\\"+productType+"\\"+timestr+"\\"+pro.getId()+"\\";
					dir.renameTo(new File(savePath));
				}
			
			message= "optsuccess";
			}else{
				message= "fail";
			}	
		}else{
			message="会话过期,请重新登录!";
		}
		wmlProduct=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(message);
	}
	
	//查询单个商品信息
	public String queryProduct() throws Exception {
		
		product=wmlProductService.queryWmlProduct(wmlProduct);
		WmlProductImage imagePath= new WmlProductImage();
		imagePath.setProductId(wmlProduct.getId());
		
		String timestr=product.getCreateDate().substring(0,10);
		
		String str="";
		for(int i=0;i<timestr.split("-").length;i++){
			str+=timestr.split("-")[i];
		}
		String url=product.getProductType()+"/"+str+"/"+product.getId()+"/";
		this.session.put("path",url);
		imagePathList=wmlProductImageService.queryWmlProductImageList(imagePath);
		wmlProduct=null;
		return "productListSuccess";
		
	}
	//分页查询商品信息
	public void queryPageProduct() throws Exception{
		
		List<WmlProduct> itemList=null;
		GridServerHandler gridServerHandler=new GridServerHandler(request,response);
		int totalRowNum=gridServerHandler.getTotalRowNum();
		if (totalRowNum<1){
			totalRowNum=wmlProductService.getProductCount(wmlProduct);
			gridServerHandler.setTotalRowNum(totalRowNum);
		}
		
		itemList=wmlProductService.queryPageWmlProduct(wmlProduct, gridServerHandler.getStartRowNum(),gridServerHandler.getPageSize());
		gridServerHandler.setData(itemList,WmlProduct.class);

		response.setCharacterEncoding("utf-8");
		response.getWriter().print(gridServerHandler.getLoadResponseText());
		
	}
	
	//批量修改图片位置
	public String productImageUpdateFiles(){
		try{
		
		ActionContext ac=ActionContext.getContext();
		ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
		//获取原图片存储地址
		String imagePath = sc.getRealPath("/") + "productUpload";
		//查询所有商品信息
		List<WmlProduct> productList=wmlProductService.queryWmlProductList(wmlProduct);
		
		//遍历所有商品信息
		for(WmlProduct item: productList){
			WmlProductImage imgItem=new WmlProductImage();
			//转换商品上传时间"2015-09-10" 转换为"20150910"
			String timestr=TimeUtil.parseToString(item.getCreateDate());
			//商品重新存储地址
			String saveImagePath=imagePath+"\\"+item.getProductType()+"\\"+timestr+"\\"+item.getId();
			//按商品id查询商品图片信息
			imgItem.setProductId(item.getId());
			List<WmlProductImage> imgList=wmlProductImageService.queryWmlProductImageList(imgItem);
			int i=0;
			//遍历图片信息
			for(WmlProductImage imgitem:imgList){
				i++;
				//获取原图片文件
				String pathimg=imagePath+"\\"+imgitem.getUrl();
				File f = new File(pathimg);
				//判断原商品图片是否存在
				if(f.exists()){
					//重新保存商品图片信息地址
				String imageName=saveImagePath+"\\"+timestr+"_H"+imgitem.getHeight()+"_W"+imgitem.getWidth()+"_"+i+".jpg";
				File copyFile= new File(imageName);
				//复制原商品图片信息到新路径
				FileUtils.copyFile(f, copyFile);
				//保存新图片路径信息
				imgitem.setUrl(timestr+"_H"+imgitem.getHeight()+"_W"+imgitem.getWidth()+"_"+i+".jpg");
				//更新图片路径信息
				wmlProductImageService.updateWmlProductImage(imgitem);
				}
			}
		}
		message="success";
		}catch(Exception e){
			message="fail";
			e.printStackTrace();
		}
		
		return "success";
	}
	
	
	
	
	
}
