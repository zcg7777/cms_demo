package com.briup.demo.service;

import java.util.List;

import com.briup.demo.common.bean.Article;
import com.briup.demo.common.bean.UserReport;

public interface IArticleService {
	/**
	 * 查找点击量最高的num篇文章
	 * @param num
	 * @return
	 */
	public List<Article> findArticlesByClickTimes(int num,long userId);
	
	/**
	 * 根据分类ID与级别查找对应的资讯信息
	 * @param categoryId
	 * @param mark
	 * @return
	 */
	public List<Article> findArticlesByCategoryId(long categoryId,String mark,long userId);
	/**
	 * 根据咨询ID查找咨询信息
	 * @param articleId
	 * @return
	 */
	public Article findArticlesById(long articleId,long userId);
	/**
	 * 查询用户发布的所有文章，包括所有状态，按照时间倒序排序
	 * @param userId
	 */
	public List<Article> findArticlesByUserId(long userId);
	
	/**
	 * 查询用户点赞的的所有文章，按照时间倒序排序
	 * @param userId
	 */
	public List<Article> findLikeArticlesByUserId(long userId);
	
	/**
	 * 查询用户收藏的的所有文章，按照时间倒序排序
	 * @param userId
	 */
	public List<Article> findCollectionArticlesByUserId(long userId);
	
	/**
	 * 查询用户举报的的所有文章，按照时间倒序排序
	 * @param userId
	 */
	public List<Article> findReportArticlesByUserId(long userId);

	/**
	 * 查询用户的浏览记录，
	 * @param historyValue，articleId列表，如：1,2,3,4,5
	 * @return
	 */
	public List<Article> findHistoryArticles(String historyValue,long userId);
	
	/**
	 * 按照资讯状态查找资讯列表
	 * @param state
	 * @return
	 */
	public List<Article> findArticlesByState(int state);
	/**
	 * 更新资讯所属栏目
	 * @param articleId
	 * @param categoryId
	 */
	public void updateArticleCategory(long articleId, long categoryId);
	/**
	 * 审核文章
	 * @param articleId
	 * @param state
	 */
	public void examineArticle(String articleIds, long state);
	
	/**
	 * 删除资讯
	 * @param articleId
	 */
	public void deleteArticleById(String articleIds);
	
	/**
	 * 根据举报状态查询资讯
	 * @param reportState
	 * @return
	 */
	public List<Article> findReportArticles(int reportState);
	
	/**
	 * 处理被举报的资讯
	 * @param processState
	 * @param processContent
	 */
	public void processArticle(long articleId,int processState, String processContent);

	/**
	 * 查找某资讯的所有举报信息
	 * @param articleId
	 * @return
	 */
	public List<UserReport> findUserReportsByArticleId(long articleId);
	
	/**
	 * 查找某资讯的个人举报信息
	 * @param articleId
	 * @return
	 */
	public List<UserReport> findUserReportsByArticleIdUserId(long articleId,long userId);
	
	/**
	 * 查询所有已处理过的资讯
	 * @return
	 */
	public List<Article> findProcessedArticles();

	//用户发布资讯
	void releaseArticle(Article article, long userId, long categoryId);
}
