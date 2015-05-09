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
	private String productName;
	private String productType;
	private String timestr;
	
	
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
	
	//修改商品信息
	public void updateWmlProduct() throws Exception{
			WmlAdmin wmladmin=(WmlAdmin)this.session.get("admin");
			if(wmladmin!=null){
			if(wmlProduct.getPrice()!=0 &&wmladmin.getUpdatePrice()==0){
				if(FiledataContentPath.size()<0){
					if(wmlProduct.getStatus()==0){
						wmlProduct.setOnTime(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
					}
					if(wmlProductService.updateWmlProduct(wmlProduct).equals(Constant.MSG_SUCCESS)){
						message="修改成功";
					}else{
						message="修改失败";
					}
				}else{
					if(wmlProduct.getStatus()==0){
						wmlProduct.setOnTime(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
					}
					if(wmlProductService.updateWmlProduct(wmlProduct).equals(Constant.MSG_SUCCESS)){
						WmlProductImage ImageItem= new WmlProductImage();
						ImageItem.setProductId(wmlProduct.getId());
						//查询旧图片信息
						List<WmlProductImage> oldImageList=wmlProductImageService.queryWmlProductImageList(ImageItem);
						for(WmlProductImage img:oldImageList){
							img.setIsDel(Constant.isDelete);
							wmlProductImageService.updateWmlProductImage(img);
						}
						//添加新图片信息
						for(PicInfo item:FiledataContentPath){
							try{
							WmlProductImage productImage= new WmlProductImage();
							productImage.setUrl(item.getPicUrl());
							productImage.setWidth(Integer.valueOf(item.getPicWidth()));
							productImage.setHeight(Integer.valueOf(item.getPicHeight()));
							productImage.setProductId(wmlProduct.getId());
							productImage.setIsFirst(0);
							wmlProductImageService.addWmlProductImage(productImage);
							}catch(Exception e){
								e.printStackTrace();
							}
						}
						message="修改成功";
					}else{
						message="修改失败";
					}
				}
			}else{
				message= "此帐号没有编辑价格权限";
			}
			}else{
				message="会话过期,请重新登录!";
			}
			wmlProduct=null;
			FiledataContentPath.clear();
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(message);
	}
	//图片保存
	public String productUpload() {
		String timePath=TimeUtil.getCurrentTime("yyyyMMdd");
		ActionContext ac=ActionContext.getContext();
		
		ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
		String savePath = sc.getRealPath("/");
		savePath = savePath + "productUpload\\"+getProductType()+"\\"+timePath+"\\"+getProductName()+"\\";
		File f1 = new File(savePath);
		if (!f1.exists()) {
			f1.mkdirs();
		}
		
		int size = Filedata.size();
		if (size == 0)
			return "ERROR";
		String extName = null;
		String name = null;
		
		for (int i = 0; i < size; i++) {
			PicInfo picInfo= new PicInfo();
			extName = FiledataFileName.get(i).substring(FiledataFileName.get(i).lastIndexOf("."));
			name = UUID.randomUUID().toString();
			File file = new File(savePath + name + extName);
			try {
				FileUtils.copyFile(Filedata.get(i), file);
				picInfo=ReNamePicture(timePath,savePath, name, extName);	
				picInfo.setPicUrl(picInfo.getPicUrl());
				FiledataContentPath.add(picInfo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().print(name + extName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 修改商品重新上传
	 * @return
	 */
	public String UploadproductUpdate() {
		
		ActionContext ac=ActionContext.getContext();
		ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
		String savePath = sc.getRealPath("/");
		savePath = savePath + "productUpload\\"+getProductType()+"\\"+getTimestr()+"\\"+getProductName()+"\\";
			
		int size = Filedata.size();
		if (size == 0)
			return "ERROR";
		String extName = null;
		String name = null;
		
		for (int i = 0; i < size; i++) {
			PicInfo picInfo= new PicInfo();
			extName = FiledataFileName.get(i).substring(FiledataFileName.get(i).lastIndexOf("."));
			name = UUID.randomUUID().toString();
			File file = new File(savePath + name + extName);
			try {
				FileUtils.copyFile(Filedata.get(i), file);
				picInfo=ReNamePicture(getTimestr(),savePath, name, extName);	
				picInfo.setPicUrl(picInfo.getPicUrl());
				FiledataContentPath.add(picInfo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.getWriter().print(name + extName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//重名称
	public PicInfo ReNamePicture(String timePath,String filePath,String pictureName,String extName) throws IOException{
		PicInfo pic= new PicInfo();
		File f = new File(filePath+pictureName+extName);
		Image src = javax.imageio.ImageIO.read(f);
		String picWidth=String.valueOf(src.getWidth(null));
		String picHeight=String.valueOf(src.getHeight(null));
		
		String picPath=filePath+timePath+"_H"+picHeight+"_W"+picWidth+extName;
		String picNamePath=timePath+"_H"+picHeight+"_W"+picWidth+extName;
		f.renameTo(new File(picPath));
		
		pic.setPicHeight(picHeight);
		pic.setPicWidth(picWidth);
		pic.setPicUrl(picNamePath);
		
		return pic;
	}
	//添加商品信息
	public void addWmlProduct() throws Exception{
		wmlProduct.setDownload(0);
		wmlProduct.setForwar(0);
		wmlProduct.setCollect(0);
		wmlProduct.setIsDel(Constant.DELETE);
		wmlProduct.setCreateDate(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
		if(wmlProduct.getStatus()==0){
			wmlProduct.setOnTime(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
		}
		WmlAdmin admin=(WmlAdmin) this.session.get("admin");
		if(admin==null){
			message="sessionFail";
		}else{
		wmlProduct.setUid(admin.getId());
		WmlProduct pro= new WmlProduct();
	
		if(wmlProductService.addWmlProduct(wmlProduct).equals("success")){
			pro=wmlProductService.queryWmlProduct(wmlProduct);
			int count=0;
			for(PicInfo item:FiledataContentPath){
				try{
				WmlProductImage productImage= new WmlProductImage();
				productImage.setUrl(item.getPicUrl());
				productImage.setWidth(Integer.valueOf(item.getPicWidth()));
				productImage.setHeight(Integer.valueOf(item.getPicHeight()));
				productImage.setProductId(pro.getId());
				productImage.setIsFirst(count);
				wmlProductImageService.addWmlProductImage(productImage);
				count++;
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			message= "optsuccess";
			}else{
				message= "fail";
			}	
		}
			wmlProduct=null;
			this.FiledataContentPath.clear();
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
			if(totalRowNum==1){
				itemList=wmlProductService.queryWmlProductList(wmlProduct);
				gridServerHandler.setData(itemList,WmlProduct.class);
			}else{
				itemList=wmlProductService.queryPageWmlProduct(wmlProduct, gridServerHandler.getStartRowNum(),gridServerHandler.getPageSize());
				gridServerHandler.setData(itemList,WmlProduct.class);
			}
			
		}else{
			itemList=wmlProductService.queryPageWmlProduct(wmlProduct, gridServerHandler.getStartRowNum(),gridServerHandler.getPageSize());
			gridServerHandler.setData(itemList,WmlProduct.class);
		}
		
		//wmlProduct=null;
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(gridServerHandler.getLoadResponseText());
		
	}
	
	
	
	
}
