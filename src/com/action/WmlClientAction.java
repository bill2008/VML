package com.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.pojo.WmlAdvertisement;
import com.pojo.WmlAttention;
import com.pojo.WmlBrand;
import com.pojo.WmlProduct;
import com.pojo.WmlProductImage;
import com.pojo.WmlUseEvaluation;
import com.pojo.WmlUsePraise;
import com.pojo.WmlUser;
import com.service.IWmlAdvertisementService;
import com.service.IWmlAttentionService;
import com.service.IWmlBrandService;
import com.service.IWmlProductImageService;
import com.service.IWmlProductService;
import com.service.IWmlUseEvaluationService;
import com.service.IWmlUsePraiseService;
import com.service.IWmlUserService;
import com.tool.Constant;
import com.tool.FileUtil;

public class WmlClientAction extends BaseAction {
	
	private IWmlProductService wmlProductService;
	private IWmlProductImageService wmlProductImageService;
	private IWmlUserService wmlUserService;
	private IWmlBrandService wmlBrandService;
	private IWmlAdvertisementService wmlAdvertisementService;
	private IWmlUseEvaluationService wmlUseEvaluationService;
	private IWmlAttentionService wmlAttentionService;
	private IWmlUsePraiseService wmlUsePraiseService;
	
	//接受参数
	//对象参数接受方式  对象名.属性名
	//实例 product.id/product.name
	private WmlProduct product;//商品实体类
	private WmlProductImage productImage;//商品图片实体类
	private WmlUser user;//用户实体类
	private WmlBrand brand;//品牌实体类
	private WmlAdvertisement advertisement;//广告实体类
	private WmlUseEvaluation useEvaluation;//评论实体类
	private int userId;
	private int productId;
	private File uploadFile;//接受流文件
	private String fileName;//文件名称
	
	//返回参数json
	//struts2 自动转换成json数据
	//格式{"对象名":[{"属性"：值}]}
	//实例{"productList":[{"id":1,"name":***,"createDate":2014-11-12},{"id":1,"name":***,"createDate":2014-11-12}]}
	private List<WmlProduct> productList;
	private List<WmlProductImage> productImageList;
	private List<WmlUser> userList;
	private List<WmlBrand> brandList;
	private List<WmlAdvertisement> advertisementList;
	private List<WmlUseEvaluation> useEvaluationList;
	private List<WmlUsePraise> praiseList;
	private List<WmlAttention> attentionList;
	private List<WmlAttention> concernList;
	private WmlUser LoginUser;
	//对象实体
	//实例{"productList":{"id":1,"name":***,"createDate":2014-11-12}}
	private int noticeCount;
	//变量
	//实例{"outMessage":"操作成功"}
	private String outMessage;//输出参数
	
	private WmlUseEvaluation productComment;
	private List<WmlUseEvaluation> productCommentList;
	
	public WmlUseEvaluation getProductComment() {
		return productComment;
	}

	public void setProductComment(WmlUseEvaluation productComment) {
		this.productComment = productComment;
	}

	public List<WmlUseEvaluation> getProductCommentList() {
		return productCommentList;
	}

	public void setProductCommentList(List<WmlUseEvaluation> productCommentList) {
		this.productCommentList = productCommentList;
	}

	public List<WmlUsePraise> getPraiseList() {
		return praiseList;
	}

	public void setPraiseList(List<WmlUsePraise> praiseList) {
		this.praiseList = praiseList;
	}

	public List<WmlAttention> getAttentionList() {
		return attentionList;
	}

	public void setAttentionList(List<WmlAttention> attentionList) {
		this.attentionList = attentionList;
	}

	public List<WmlAttention> getConcernList() {
		return concernList;
	}

	public void setConcernList(List<WmlAttention> concernList) {
		this.concernList = concernList;
	}

	public int getNoticeCount() {
		return noticeCount;
	}

	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public WmlProduct getProduct() {
		return product;
	}

	public void setProduct(WmlProduct product) {
		this.product = product;
	}

	public WmlProductImage getProductImage() {
		return productImage;
	}

	public void setProductImage(WmlProductImage productImage) {
		this.productImage = productImage;
	}

	public WmlUser getUser() {
		return user;
	}

	public void setUser(WmlUser user) {
		this.user = user;
	}

	public WmlBrand getBrand() {
		return brand;
	}

	public void setBrand(WmlBrand brand) {
		this.brand = brand;
	}

	public WmlAdvertisement getAdvertisement() {
		return advertisement;
	}

	public void setAdvertisement(WmlAdvertisement advertisement) {
		this.advertisement = advertisement;
	}

	public WmlUseEvaluation getUseEvaluation() {
		return useEvaluation;
	}

	public void setUseEvaluation(WmlUseEvaluation useEvaluation) {
		this.useEvaluation = useEvaluation;
	}

	public String getOutMessage() {
		return outMessage;
	}

	public void setOutMessage(String outMessage) {
		this.outMessage = outMessage;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<WmlProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<WmlProduct> productList) {
		this.productList = productList;
	}

	public List<WmlProductImage> getProductImageList() {
		return productImageList;
	}

	public void setProductImageList(List<WmlProductImage> productImageList) {
		this.productImageList = productImageList;
	}

	public List<WmlUser> getUserList() {
		return userList;
	}

	public void setUserList(List<WmlUser> userList) {
		this.userList = userList;
	}

	public List<WmlBrand> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<WmlBrand> brandList) {
		this.brandList = brandList;
	}

	public List<WmlAdvertisement> getAdvertisementList() {
		return advertisementList;
	}

	public void setAdvertisementList(List<WmlAdvertisement> advertisementList) {
		this.advertisementList = advertisementList;
	}

	public List<WmlUseEvaluation> getUseEvaluationList() {
		return useEvaluationList;
	}

	public void setUseEvaluationList(List<WmlUseEvaluation> useEvaluationList) {
		this.useEvaluationList = useEvaluationList;
	}

	public WmlUser getLoginUser() {
		return LoginUser;
	}

	public void setLoginUser(WmlUser loginUser) {
		LoginUser = loginUser;
	}

	public void setWmlProductService(IWmlProductService wmlProductService) {
		this.wmlProductService = wmlProductService;
	}

	public void setWmlProductImageService(
			IWmlProductImageService wmlProductImageService) {
		this.wmlProductImageService = wmlProductImageService;
	}

	public void setWmlUserService(IWmlUserService wmlUserService) {
		this.wmlUserService = wmlUserService;
	}

	public void setWmlBrandService(IWmlBrandService wmlBrandService) {
		this.wmlBrandService = wmlBrandService;
	}

	public void setWmlAdvertisementService(
			IWmlAdvertisementService wmlAdvertisementService) {
		this.wmlAdvertisementService = wmlAdvertisementService;
	}

	public void setWmlUseEvaluationService(
			IWmlUseEvaluationService wmlUseEvaluationService) {
		this.wmlUseEvaluationService = wmlUseEvaluationService;
	}

	public void setWmlAttentionService(IWmlAttentionService wmlAttentionService) {
		this.wmlAttentionService = wmlAttentionService;
	}

	public void setWmlUsePraiseService(IWmlUsePraiseService wmlUsePraiseService) {
		this.wmlUsePraiseService = wmlUsePraiseService;
	}

	/**
	 * 查询商品信息
	 * @return 商品集合类
	 * @throws Exception
	 */
	public String queryProductList() throws Exception{
		
		productList=wmlProductService.queryWmlProductList(product);
			
		for(int i=0;i<productList.size();i++){
			WmlProductImage item=new WmlProductImage();
			item.setProductId(productList.get(i).getId());
			List<WmlProductImage> imageList=wmlProductImageService.queryWmlProductImageList(item);
			productList.get(i).setProductImageList(imageList);
		}
		return "success";
	}
	
	/**
	 * 查询商品的评论并修改通知
	 * @return
	 * @throws Exception
	 */
	public String queryProductCommentList() throws Exception{
		
		useEvaluation.setAuthorId(userId);
		useEvaluation.setProductId(productId);
		productCommentList=wmlUseEvaluationService.queryWmlUseEvaluationList(useEvaluation);
		WmlProduct item=new WmlProduct();
		item.setUid(userId);
		item.setId(productId);
		WmlProduct pro=wmlProductService.queryWmlProduct(item);
		if(pro.getName()!=null  &&  StringUtils.isNotEmpty(pro.getName())){
			for(WmlUseEvaluation eva:productCommentList){
				eva.setNoticeProductOwnerFlag(Constant.notice_false);
				wmlUseEvaluationService.updateWmlUseEvaluation(eva);
			}
		}else{
			for(WmlUseEvaluation eva:productCommentList){
				eva.setNoticeParentIdFlag(Constant.notice_false);
				wmlUseEvaluationService.updateWmlUseEvaluation(eva);
			}
		}
		
		return "success";

		
		
	}
	
	/**
	 * 对商品进行评论
	 * @return
	 * @throws Exception
	 */
	public String newProductComment()throws Exception{
		//是否通知
		useEvaluation.setNoticeParentIdFlag(Constant.notice_true);
		useEvaluation.setNoticeProductOwnerFlag(Constant.notice_true);
		String message=wmlUseEvaluationService.addWmlUseEvaluation(useEvaluation);
		if(message.equals(Constant.MSG_SUCCESS)){
			outMessage=Constant.MSG_SUCCESS;
		}else{
			outMessage=Constant.MSG_FAILURE;
		}
		productComment=wmlUseEvaluationService.queryWmlUseEvaluation(useEvaluation);
		return "success";
	}
	
	/**
	 * 商品评论通知
	 * @return
	 * @throws Exception
	 */
	public String noticeProductComment()throws Exception{
		noticeCount=0;
		WmlUseEvaluation item1= new WmlUseEvaluation();
		WmlUseEvaluation item2=new WmlUseEvaluation();
		WmlUseEvaluation item3=new WmlUseEvaluation();
		item1.setAuthorId(userId);
		item2.setAuthorId(userId);
		item3.setAuthorId(userId);
		item3.setParentId(userId);
		List<WmlUseEvaluation> list1=wmlUseEvaluationService.queryWmlUseEvaluationList(item1);
		List<WmlUseEvaluation> list2=wmlUseEvaluationService.queryWmlUseEvaluationList(item2);
		List<WmlUseEvaluation> list3=wmlUseEvaluationService.queryWmlUseEvaluationList(item3);
		if(list1.size()>0){
			for(int i=0;i<list1.size();i++){
				if(list1.get(0).getNoticeParentIdFlag()!=Constant.notice_false){
					noticeCount++;
				}
			}
		}else if(list2.size()>0){
			for(int i=0;i<list2.size();i++){
				if(list2.get(0).getNoticeParentIdFlag()!=Constant.notice_false){
					noticeCount++;
				}
			}
		}else if(list3.size()>0){
			for(int i=0;i<list3.size();i++){
				if(list3.get(0).getNoticeParentIdFlag()!=Constant.notice_false){
					noticeCount++;
				}
			}
		}else{
			noticeCount=0;
		}
		return "success";
	}
	
	/**
	 * 关注用户的商品
	 * @return
	 * @throws Exception
	 */
	public String queryConcernProductList() throws Exception{
		WmlAttention atten= new WmlAttention();
		atten.setById(userId);
		List<WmlAttention> attenList=wmlAttentionService.queryWmlAttentionList(atten);
		List<WmlProduct> proList=new ArrayList<WmlProduct>();
		//查询出所有关注用户商品ID
		for(WmlAttention item:attenList){
			WmlProduct pro= new WmlProduct();
			pro.setUid(item.getForId());
			//合并进同一个泛型集合
			proList.containsAll(wmlProductService.queryWmlProductList(pro));
		}
		for(int i=0;i<proList.size();i++){
			WmlProductImage item=new WmlProductImage();
			item.setProductId(proList.get(i).getId());
			//查询出所有商品的图片集合
			List<WmlProductImage> imageList=wmlProductImageService.queryWmlProductImageList(item);
			proList.get(i).setProductImageList(imageList);
		}
		productList=proList;
		return "success";
		
	}
	
	/**
	 * 个人中心接口
	 * @return
	 * @throws Exception
	 */
	public String queryUserDetail() throws Exception{
		//商品
		WmlProduct pro= new WmlProduct();
		pro.setUid(userId);
		//关注列表
		WmlAttention atten= new WmlAttention();
		atten.setById(userId);
		//被关注列表
		WmlAttention byatten= new WmlAttention();
		byatten.setForId(userId);
		
		concernList=wmlAttentionService.queryWmlAttentionList(byatten);
		attentionList=wmlAttentionService.queryWmlAttentionList(atten);
		
		productList=wmlProductService.queryWmlProductList(product);
		for(int i=0;i<productList.size();i++){
			WmlProductImage item=new WmlProductImage();
			item.setProductId(productList.get(i).getId());
			List<WmlProductImage> imageList=wmlProductImageService.queryWmlProductImageList(item);
			productList.get(i).setProductImageList(imageList);
			WmlUsePraise userPraise=new WmlUsePraise();
			userPraise.setProductId(productList.get(i).getId());
			praiseList=wmlUsePraiseService.queryWmlUsePraiseList(userPraise);
		}
		return "success";
		
	}
	
	/**
	 * 手机客户端更新用户信息
	 * @return
	 * @throws Exception
	 */
	public String modifyUser()throws Exception{
		
		wmlUserService.updateWmlUser(user);
		LoginUser=wmlUserService.queryWmlUser(user);
		return "success";
	}
	
	/**
	 * 用户注册接口
	 * @return
	 * @throws Exception
	 */
	public String registerUser()throws Exception{
		outMessage=wmlUserService.addWmlUser(user);
		LoginUser=wmlUserService.queryWmlUser(user);
		return "success";
	}
	
	/**
	 * 查询用户接口
	 * @return
	 * @throws Exception
	 */
	public String queryUser() throws Exception{
		LoginUser=wmlUserService.queryWmlUser(user);
		return "success";
	}
	/**
	 * 品牌信息查询
	 * @return
	 * @throws Exception
	 */
	public String queryBrandList() throws Exception{
		brandList=wmlBrandService.queryWmlBrandList(brand);
		return "success";
	}
	/**
	 * 手机上传接口
	 * @return
	 * @throws Exception
	 */
	public String uploadProduct() throws Exception{
		
		if(wmlProductService.addWmlProduct(product).equals("success")){
			String uploadmsg=FileUtil.uploadimage(uploadFile, product.getName(), fileName);
			if(uploadmsg!=null){
				WmlProduct pro=wmlProductService.queryWmlProduct(product);
				String url=uploadmsg+"\\"+fileName;
				productImage.setUrl(url);
				productImage.setProductId(pro.getId());
				if(wmlProductImageService.addWmlProductImage(productImage).equals("success")){
					outMessage=Constant.MSG_SUCCESS;
				}else{
					outMessage="添加图片信息"+Constant.MSG_FAILURE;
				}
			}else{
				outMessage="上传图片"+Constant.MSG_FAILURE;
			}
		}else{
			outMessage="添加商品信息"+Constant.MSG_FAILURE;
		}
		return "success";
	}
	/**
	 * 广告信息查询
	 * @return
	 * @throws Exception
	 */
	public String advertisementQuery() throws Exception{
		advertisementList=wmlAdvertisementService.queryWmlAdvertisementList(advertisement);
		return "success";
	}
}
