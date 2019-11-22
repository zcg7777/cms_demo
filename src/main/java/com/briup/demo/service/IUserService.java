package com.briup.demo.service;

import com.briup.demo.common.bean.User;
import com.briup.demo.common.exception.UserServcieException;

public interface IUserService {
	/**
	 * 处理手动登录
	 * @param account
	 * @param password
	 * @return
	 * @throws UserServcieException
	 */
	public User login(String account,String password) throws UserServcieException;
	
	/**
	 * 第三方登录，返回第三方登陆后保存入库或者更新入库的用户信息
	 * @param user
	 * @return
	 */
	public User thirdLogin(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * 网站注册功能
	 * @param user
	 */
	public void register(User user) throws UserServcieException;
	
	
	/**
	 * 用户点赞资讯
	 * @param articleId
	 * @param userId
	 * @return
	 */
	public void likeArticle(int likeState,long articleId,long userId );
	/**
	 * 用户收藏咨询
	 * @param articleId
	 * @param userId
	 * @return
	 */
	public void collectionArticle(int likeState,long articleId,long userId );
	/**
	 * 用户举报咨询
	 * @param articleId
	 * @param userId
	 * @return
	 */
	public void reportArticle(long articleId,long userId,String reportType,String reportContent );
	
}
