package com.briup.demo.dao;

import java.util.List;

import com.briup.demo.common.bean.Article;
import com.briup.demo.common.bean.UserReport;

public interface IArticleMapper {
	
	//查询用户阅读量最多的 num个 资讯
	public List<Article> findArticlesByClickTimes(int num, long userId);
	/**
	 * 根据一级分类ID查找的资讯信息
	 * @param categoryId
	 * @param mark
	 * @return
	 */
	public List<Article> findArticlesByCategoryIdOne(long categoryId, long UserId);
	/**
	 * 根据二级分类ID查找的资讯信息
	 * @param categoryId
	 * @param mark
	 * @return
	 */
	public List<Article> findArticlesByCategoryIdTwo(long categoryId, long UserId);
	
	//获取指定咨询 被点赞的次数
	public int getArticleLikeTimesById(long articleId);
	
	//获取指定咨询 被收藏的次数
	public int getArticleCollectionTimesById(long articleId);
	
	//根据资讯编号 获取相应资讯对象【用户未举报】
	public Article findArticlesById(long articleId, long userId);
	
	//修改资讯的点击量
	public void updateArticleClickTimes(long articleId,long times);
	
	//查找对id资讯点赞的所有userId
	public List<Long> findAllUserIdLikeArticleByAid(Long artId);
	
	//根据aid uid获取 点赞状态：返回1或2
	public Integer findLikeStateByAidUid(long artId, long userId);
	//根据aid uid获取 举报状态：返回1或2或n,或者null
	public Integer findReportStateByAidUid(Long id, long userId);
	
	//根据用户id查找其发布的所有资讯
	public List<Article> findArticlesByUserId(long userId);
	
	//根据咨询id删除资讯
	public void deleteArticleById(int parseInt);
	
	//修改资讯所属类别
	public void updateArticleCategory(long articleId, long categoryId);
	
	//获取资讯对象 里面包含user和分类
	public Article findArticlesByIdWithUserAndCategory(long articleId, long userId);

	//发布资讯
	public void insertArticle(Article article, long userId, long categoryId);
	
	//根据咨询状态获取所有资讯
	public List<Article> findArticlesByState(int state);
	
	//更新资讯状态
	public void updateArticleState(long parseLong, long state);
	
	//查找所有用户举报的资讯
	public List<Article> findReportArticlesByUserId(long userId);
	
	//根据aid uid查询所有 举报信息【此处sql语句未实现】
	public List<UserReport> findUserReportsByAidUid(long articleId, long userId);
	
}
