package com.webservice;

import java.io.File;


public interface IWmlClientWebService {
	
	/**
	 * 手机客户端商品搜索
	 * @param item 商品实体类
	 * @return
	 */
	public String queryProductList(Integer id,String name,String createDate,String endDate,String description,String property,Double price,Integer uid,Integer bid,
			Integer oid,String uploadType,Integer forword,Integer download,Integer viewcount,Integer collect,Integer status,Integer isdel);
	
	/**
	 * 查询商品的评论
	 * @param productId 商品序列Id
	 * @param userId 用户ID
	 * @return
	 */
	public String queryProductCommentList(int productId,int userId);
	
	/**
	 * 商品评论
	 * @param productId 商品序列Id
	 * @param authorId   评论者
	 * @param parentId	  受评论回复人
	 * @return
	 */
	public String newProductComment(int productId,int authorId,int parentId);
	
	/**
	 * 商品评论通知
	 * @param userId 用户ID
	 * @return
	 */
	public String noticeProductComment(int userId);
	
	/**
	 * 关注用户的商品列表
	 * @param userId 用户ID
	 * @return
	 */
	public String queryConcernProductList(int userId);
	
	/**
	 * 个人用户中心数据
	 * @param userId 用户ID
	 * @return
	 */
	public String queryUserDetail(int userId);
	
	/**
	 * 更新用户信息
	 * @param item 用户实体类
	 * @return
	 */
	public String modifyUser(Integer id,String userName,String password,Integer uid,String loginName,String phone,String type,String signature,Integer organ,
			Integer permissions,Integer channel,String createDate,String endDate,String uploadFlag,Integer status,Integer isDel);
	
	/**
	 * 手机端用户注册
	 * @param item  用户实体类
	 * @return
	 */
	public String registerUser(Integer id,String userName,String password,Integer uid,String loginName,String phone,String type,String signature,Integer organ,
			Integer permissions,Integer channel,String createDate,String endDate,String uploadFlag,Integer status,Integer isDel);
	
	/**
	 * 手机客户端查询登录用户
	 * @param name 注册名
	 * @param channel 登录类型
	 * @return
	 */
	public String queryUser(String name,int channel);
	
	/**
	 * 品牌查询
	 * @param name 品牌名称
	 * @param letter 首字母
	 * @param keyword 关键字
	 * @return
	 */
	public String brandQuery(String name,String letter,String keyword);
	
	/**
	 * 商品上传
	 * @param 商品实体类
	 * @param 上传文件名
	 * @param 文件IO流
	 * @return
	 */
	public String uploadProduct(String productname,String description,String property,Double price,Integer uid,Integer bid,
			Integer oid,String uploadType, String fileName, File uploadfile);
	
	/**
	 * 广告接口
	 * @return
	 */
	public String advertisementQuery();
	

}
