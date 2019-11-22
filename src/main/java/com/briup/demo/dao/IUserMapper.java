package com.briup.demo.dao;

import java.util.Date;

import com.briup.demo.common.bean.Article;
import com.briup.demo.common.bean.User;

public interface IUserMapper {
	/**
	 * 根据用户名查找用户
	 * @param account
	 * @return
	 */
	public User findUserByAccount(String account);
	
	public User findUserById(Long id);
	
	/**
	 * 通过第三方平台ID查找用户
	 * @param thirdId
	 * @return
	 */
	public User findUserByThirdId(String thirdId);
	
	/**
	 * 更新用户昵称信息
	 * @param user
	 */
	public void updateName(User user);
	
	/**
	 * 第三方登录添加用户
	 * @param user
	 * @return
	 */
	public void insertUserThird(User user);

	public void insertUser(User user);

	//用户点赞操作【往桥接表 里 添加数据】
	public void insertUserLike(long articleId, long userId, Date date);
	
	//修改用户点赞状态
	public void updateUserLike(long articleId, long userId, int i);

	//用户发布资讯
	public void insertArticle(Article article, long userId, long categoryId);

	//更新用户信息
	public void updateUser(User user);

	//往用户举报表中添加信息【举报日期 状态不需要传参】
	public void insertUserReport(long articleId, long userId, String reportType, String reportContent);

}
