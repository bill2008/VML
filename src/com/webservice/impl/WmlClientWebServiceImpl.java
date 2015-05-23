package com.webservice.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.tool.MD5Util;
import com.tool.TimeUtil;
import com.webservice.IWmlClientWebService;

public class WmlClientWebServiceImpl implements IWmlClientWebService{
	
	
	/**
	 *访问 http://localhost:8080/项目名称/webService/doClientWebService?wsdl
	 */
	
	
	private IWmlProductService wmlProductService;
	private IWmlProductImageService wmlProductImageService;
	private IWmlUserService wmlUserService;
	private IWmlBrandService wmlBrandService;
	private IWmlAdvertisementService wmlAdvertisementService;
	private IWmlUseEvaluationService wmlUseEvaluationService;
	private IWmlAttentionService wmlAttentionService;
	private IWmlUsePraiseService wmlUsePraiseService;

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
	 * 手机客户端商品搜索
	 * @param item 商品实体类
	 * @return
	 */
	public String queryProductList(Integer id,String name,String createDate,String endDate,String description,String property,Double price,Integer uid,Integer bid,
			Integer oid,String uploadType,Integer forword,Integer download,Integer viewcount,Integer collect,Integer status,Integer isdel) {
		
/*		WmlProduct product=new WmlProduct();
		product.setId(id);
		product.setName(name);
		product.setCreateDate(createDate);
		product.setEndDate(endDate);
		product.setDescription(description);
		product.setProperty(property);
		product.setPrice(price);
		product.setUid(uid);
		product.setBid(bid);
		product.setOid(oid);
		product.setUploadType(uploadType);
		product.setStatus(status);
		product.setForwar(forword);
		product.setDownload(download);
		product.setViewCount(viewcount);
		product.setCollect(collect);
		product.setIsDel(isdel);
		// TODO Auto-generated method stub
		List<WmlProduct> proList=wmlProductService.queryWmlProductList(product);
		
		for(int i=0;i<proList.size();i++){
			WmlProductImage item=new WmlProductImage();
			item.setProductId(proList.get(i).getId());
			List<WmlProductImage> imageList=wmlProductImageService.queryWmlProductImageList(item);
			proList.get(i).setProductImageList(imageList);
		}
		JSONArray json = JSONArray.fromObject(proList);
		String productjson="{\"productList\":"+json.toString() + "}";
		
		return productjson;*/
		return null;
	}

	/**
	 * 查询商品的评论
	 * @param productId 商品序列Id
	 * @param userId 用户ID
	 * @return
	 */
	public String queryProductCommentList(int productId, int userId) {
		
		WmlUseEvaluation userEvaluation= new WmlUseEvaluation();
		userEvaluation.setAuthorId(userId);
		userEvaluation.setProductId(productId);
		List<WmlUseEvaluation> productCommentList=wmlUseEvaluationService.queryWmlUseEvaluationList(userEvaluation);
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
		JSONArray json = JSONArray.fromObject(productCommentList);
		String productCommentListjson="{\"productCommentList\":"+json.toString() + "}";
		return productCommentListjson;
	}

	/**
	 * 商品评论
	 * @param productId 商品序列Id
	 * @param authorId   评论者
	 * @param parentId	  受评论回复人
	 * @return
	 */
	public String newProductComment(int productId, int authorId, int parentId) {
		WmlUseEvaluation useEvaluation= new WmlUseEvaluation();
		useEvaluation.setAuthorId(authorId);
		useEvaluation.setParentId(parentId);
		useEvaluation.setProductId(productId);
		useEvaluation.setNoticeParentIdFlag(Constant.notice_true);
		useEvaluation.setNoticeProductOwnerFlag(Constant.notice_true);
		String outMessage=null;
		String message=wmlUseEvaluationService.addWmlUseEvaluation(useEvaluation);
		if(message.equals(Constant.MSG_SUCCESS)){
			outMessage=Constant.MSG_SUCCESS;
		}else{
			outMessage=Constant.MSG_FAILURE;
		}
		WmlUseEvaluation productComment=wmlUseEvaluationService.queryWmlUseEvaluation(useEvaluation);
		
		JSONObject json=JSONObject.fromObject(productComment);
		
		String productCommentListjson="{\"productComment\":"+json.toString() + ",\"outMessage\":\""+outMessage+"\"}";
		
		return productCommentListjson;
	}

	/**
	 * 商品评论通知
	 * @param userId 用户ID
	 * @return
	 */
	public String noticeProductComment(int userId) {
		int noticeCount=0;
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
		 String count= "{\"noticeCount\":"+noticeCount+ "}";
		return count;
	}

	/**
	 * 关注用户的商品列表
	 * @param userId 用户ID
	 * @return
	 */
	public String queryConcernProductList(int userId) {
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
		
		JSONArray json = JSONArray.fromObject(proList);
		String productListjson="{\"productList\":"+json.toString() + "}";
		return productListjson;
	}

	/**
	 * 个人用户中心数据
	 * @param userId 用户ID
	 * @return
	 */
	public String queryUserDetail(int userId) {
		//商品
		WmlProduct pro= new WmlProduct();
		pro.setUid(userId);
		//关注列表
		WmlAttention atten= new WmlAttention();
		atten.setById(userId);
		//被关注列表
		WmlAttention byatten= new WmlAttention();
		byatten.setForId(userId);
		
		List<WmlAttention> concernList=wmlAttentionService.queryWmlAttentionList(byatten);
		List<WmlAttention> attentionList=wmlAttentionService.queryWmlAttentionList(atten);
		WmlProduct product= new WmlProduct();
		/**
		 * 商品信息过滤为个人商品信息
		 */
		product.setUid(userId);
		List<WmlProduct> productList=wmlProductService.queryWmlProductList(product);
		List<WmlUsePraise> praiseList= new ArrayList<WmlUsePraise>();
		for(int i=0;i<productList.size();i++){
			WmlProductImage item=new WmlProductImage();
			item.setProductId(productList.get(i).getId());
			List<WmlProductImage> imageList=wmlProductImageService.queryWmlProductImageList(item);
			productList.get(i).setProductImageList(imageList);
			WmlUsePraise userPraise=new WmlUsePraise();
			userPraise.setProductId(productList.get(i).getId());
			praiseList=wmlUsePraiseService.queryWmlUsePraiseList(userPraise);
		}
		JSONArray productListJson=JSONArray.fromObject(productList);
		JSONArray concernListJson=JSONArray.fromObject(concernList);
		JSONArray attentionListJson=JSONArray.fromObject(attentionList);
		JSONArray praiseListJson=JSONArray.fromObject(praiseList);
				
		StringBuffer userDetailJson= new StringBuffer();
		userDetailJson.append("{");
		userDetailJson.append("\"productList\":"+productListJson.toString() );
		userDetailJson.append(",");
		userDetailJson.append("\"concernList\":"+concernListJson.toString() );
		userDetailJson.append(",");
		userDetailJson.append("\"attentionList\":"+attentionListJson.toString() );
		userDetailJson.append(",");
		userDetailJson.append("\"praiseList\":"+praiseListJson.toString() );
		userDetailJson.append("}");
		return userDetailJson.toString();
	}

	/**
	 * 更新用户信息
	 * @param item 用户实体类
	 * @return
	 */
	public String modifyUser(Integer id,String userName,String password,Integer uid,String loginName,String phone,String type,String signature,Integer organ,
			Integer permissions,Integer channel,String createDate,String endDate,String uploadFlag,Integer status,Integer isDel) {
		WmlUser item=new WmlUser();
		item.setId(id);
		item.setName(userName);
		item.setPassword(MD5Util.getMD5(password));
		item.setUid(uid);
		item.setLoginName(loginName);
		item.setPhone(phone);
		item.setType(type);
		item.setSignature(signature);
		item.setOrgan(organ);
		item.setPermissions(permissions);
		item.setChannel(channel);
		item.setCreateDate(createDate);
		item.setEndDate(endDate);
		item.setUploadFlag(uploadFlag);
		item.setStatus(status);
		item.setIsDel(isDel);
		
		String outMessage=wmlUserService.updateWmlUser(item);
		WmlUser LoginUser=wmlUserService.queryWmlUser(item);
		
		JSONObject json=JSONObject.fromObject(LoginUser);
		String LoginUserjson="{\"LoginUser\":"+json.toString() + ",\"outMessage\":\""+outMessage+"\"}";
		return LoginUserjson;
	}

	/**
	 * 手机端用户注册
	 * @param item  用户实体类
	 * @return
	 */
	public String registerUser(Integer id,String userName,String password,Integer uid,String loginName,String phone,String type,String signature,Integer organ,
			Integer permissions,Integer channel,String createDate,String endDate,String uploadFlag,Integer status,Integer isDel) {
		WmlUser item=new WmlUser();
		item.setId(id);
		item.setName(userName);
		item.setPassword(MD5Util.getMD5(password));
		item.setUid(uid);
		item.setLoginName(loginName);
		item.setPhone(phone);
		item.setType(type);
		item.setSignature(signature);
		item.setOrgan(organ);
		item.setPermissions(permissions);
		item.setChannel(channel);
		item.setCreateDate(createDate);
		item.setEndDate(endDate);
		item.setUploadFlag(uploadFlag);
		item.setStatus(status);
		item.setIsDel(isDel);
		String outMessage=wmlUserService.addWmlUser(item);
		WmlUser LoginUser=wmlUserService.queryWmlUser(item);
		
		JSONObject json=JSONObject.fromObject(LoginUser);
		String LoginUserjson="{\"LoginUser\":"+json.toString() + ",\"outMessage\":\""+outMessage+"\"}";
		return LoginUserjson;
	}

	/**
	 * 手机客户端查询登录用户
	 * @param name 注册名
	 * @param channel 登录类型
	 * @return
	 */
	public String queryUser(String name, int channel) {
		WmlUser user= new WmlUser();
		user.setName(name);
		user.setChannel(channel);
		WmlUser LoginUser=wmlUserService.queryWmlUser(user);
		
		JSONObject json=JSONObject.fromObject(LoginUser);
		String LoginUserjson="{\"LoginUser\":"+json.toString() + "}";
		return LoginUserjson;
	}

	/**
	 * 品牌查询
	 * @param name 品牌名称
	 * @param letter 首字母
	 * @param keyword 关键字
	 * @return
	 */
	public String brandQuery(String name, String letter, String keyword) {
		WmlBrand brand= new WmlBrand();
		brand.setName(name);
		brand.setLetter(letter);
		brand.setKeyword(keyword);
		
		List<WmlBrand> brandList=wmlBrandService.queryWmlBrandList(brand);
		JSONArray json = JSONArray.fromObject(brandList);
		String brandListjson="{\"brandList\":"+json.toString() + "}";
		return brandListjson;
	}

	/**
	 * 商品上传
	 * @param 商品实体类
	 * @param 上传文件名
	 * @param 文件IO流
	 * @return
	 */
	public String uploadProduct(String productname,String description,String property,Double price,Integer uid,Integer bid,
			Integer oid,String uploadType, String fileName, File uploadfile) {
		
/*		WmlUser userItem= new WmlUser();
		userItem.setId(uid);
		WmlUser user=wmlUserService.queryWmlUser(userItem);
		WmlProduct item=new WmlProduct();
	
		//判断用户权限，设置商品上架状态
		if(user.getPermissions()==0){
			item.setName(productname);
			item.setDescription(description);
			item.setProperty(property);
			item.setPrice(price);
			item.setUid(uid);
			item.setBid(bid);
			item.setOid(oid);
			item.setUploadType(uploadType);
			item.setStatus(0);
		}else{
			item.setName(productname);
			item.setDescription(description);
			item.setProperty(property);
			item.setPrice(price);
			item.setUid(uid);
			item.setBid(bid);
			item.setOid(oid);
			item.setUploadType(uploadType);
			item.setStatus(2);
		}
		
		
		
		String outMessage=null;
		WmlProduct pro= new WmlProduct();
		WmlProductImage productImage= new WmlProductImage();
		
		if(status==0){
			item.setOnTime(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
		}
		item.setIsDel(Constant.DELETE);
		item.setCreateDate(TimeUtil.getCurrentTime("yyyy-MM-dd HH:mm:ss"));
		if(wmlProductService.addWmlProduct(item).equals("success")){
			String name = UUID.randomUUID().toString();
			String uploadmsg=FileUtil.uploadimage(uploadfile, name, fileName);
			if(uploadmsg!=null){
				pro=wmlProductService.queryWmlProduct(item);
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
		
		List<WmlProductImage> proImageList=wmlProductImageService.queryWmlProductImageList(productImage);
		pro.setProductImageList(proImageList);
		JSONObject json=JSONObject.fromObject(pro);
		String productjson="{\"product\":"+json.toString() + ",\"outMessage\":\""+outMessage+"\",\"}";
		return productjson;*/
		return null;
	}

	/**
	 * 广告接口
	 * @return
	 */
	public String advertisementQuery() {
		List<WmlAdvertisement> advertisementList=wmlAdvertisementService.queryWmlAdvertisementList(null);
		JSONArray json = JSONArray.fromObject(advertisementList);
		String advertisementListjson="{\"advertisementList\":"+json.toString() + "}";
		return advertisementListjson;
	}

}
