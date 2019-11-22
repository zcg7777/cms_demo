package com.briup.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.common.bean.Article;
import com.briup.demo.common.bean.UserReport;
import com.briup.demo.dao.IArticleMapper;
import com.briup.demo.service.IArticleService;
@Service
public class ArticleServiceImpl implements IArticleService{
	@Autowired
	private IArticleMapper iArticleMapper;
	
	@Override
	public List<Article> findArticlesByClickTimes(int num, long userId) {
		
		//查询资讯 基本信息
		List<Article> list = iArticleMapper.findArticlesByClickTimes(num,userId);
		for (Article art : list) {
			//添加点赞量 收藏量
			addLikeAndCollectionNums(art);
		}
		
		return list;
	}
	
	private void addLikeAndCollectionNums(Article art) {
		Long artId = art.getId();
		//查询得到该id咨询 点赞数量 收藏数量
		int likeTimes = iArticleMapper.getArticleLikeTimesById(artId);
		int collectionTimes = iArticleMapper.getArticleCollectionTimesById(artId);
		art.setLikeTimes(likeTimes);
		art.setCollectionTimes(collectionTimes);
	}
	
	//根据资讯分类编号 栏目等级 和 当前用户 获取相关资讯【用户未举报】
	@Override
	public List<Article> findArticlesByCategoryId(long categoryId, String mark, long userId) {
		List<Article> list = null;
		//1.查询资讯表 获取 资讯基本信息
		//如果栏目等级为1 则查询所属类别中所有资讯
		if(mark.equals("1")) {
			list = iArticleMapper.findArticlesByCategoryIdOne(categoryId, userId);
		}else {
			//如果是二级标题资讯 则查询指定id的资讯
			list = iArticleMapper.findArticlesByCategoryIdTwo(categoryId, userId);
		}
		
		//2.查询其他表 获取 资讯被点赞、收藏数量，添加到资讯对象中
		for (Article art : list) {
			addLikeAndCollectionNums(art);
		}
		
		return list;
	}

	private void addUserIsLike(Article article, long userId) {
		Long artId = article.getId();
		
		//查找 所有对该资讯点赞的 用户id
		List<Long> list = iArticleMapper.findAllUserIdLikeArticleByAid(artId);
		//如果没有人点赞该资讯，直接设置0 返回
		if(list == null) {
			article.setIsLike(0);
			return;
		}
		
		//如果有人点赞，则看是否包含 当前用户
		boolean isLike = list.contains(userId);

		//如果点赞表中没有该用户，则设置0  未点赞
		if(isLike == false) {
			article.setIsLike(0);
		}else {
			//再进一步判断该用户点赞状态
			int state = iArticleMapper.findLikeStateByAidUid(artId, userId);
			if(state == 1) {
				article.setIsLike(1);
			}else if(state == 2) {
				article.setIsLike(2);
			}
		}
	}
	
	private void addUserIsLike2(Article article, long userId) {
		Integer state = iArticleMapper.findLikeStateByAidUid(article.getId(), userId);
		System.out.println("in service,likeState: " + state);
		if(state == null) {
			article.setIsLike(0);
		}else {
			article.setIsLike(state);
		}
	}

	private void addUserIsReport(Article article, long userId) {
		Integer state = iArticleMapper.findReportStateByAidUid(article.getId(), userId);
		
		if(state == null) {
			//设置未举报
			article.setIsReport(0); 
		}else {
			//其他值表示 已经举报
			article.setIsReport(state);
		}
	}
	
	@Override
	public Article findArticlesById(long articleId, long userId) {
		Article article = iArticleMapper.findArticlesById(articleId,userId);
		//添加点赞 收藏数量
		addLikeAndCollectionNums(article);
		//添加当前用户是否点赞
		//addUserIsLike(article,userId);
		addUserIsLike2(article,userId);
		//添加当前用户是否举报该资讯
		addUserIsReport(article,userId);
		
		//修改资讯的点击量
		iArticleMapper.updateArticleClickTimes(articleId, 1);
		
		
		return article;
	}

	//查询得到用户所发布的所有资讯
	@Override
	public List<Article> findArticlesByUserId(long userId) {
		//查询资讯表，根据发布用户id进行筛选
		List<Article> list = iArticleMapper.findArticlesByUserId(userId);
		if(list != null) {
			//给每个资讯 添加点赞和收藏数目
			for (Article art : list) {
				addLikeAndCollectionNums(art);
			}
		}
		
		return list;
	}
	
	//发布资讯: 入库资讯信息
	@Override
	public void releaseArticle(Article article, long userId, long categoryId) {
		article.setReleaseDate(new Date());
		article.setState(0);
		iArticleMapper.insertArticle(article,userId,categoryId);
		
	}
	
	@Override
	public List<Article> findLikeArticlesByUserId(long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Article> findCollectionArticlesByUserId(long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Article> findReportArticlesByUserId(long userId) {
		List<Article> list = iArticleMapper.findReportArticlesByUserId(userId);
		String aid="";
		for (Article article : list) {
			addLikeAndCollectionNums(article);
			aid = aid + article.getId() + " ";
		}
		System.out.println(userId+" 用户举报的资讯有 " + list.size()+"个,aid值为: "+aid);
		return list;
	}
	
	//根据历史记录articleId找出所有资讯，注意用户举报的不显示
	@Override
	public List<Article> findHistoryArticles(String historyValue, long userId) {
		String[] articleIds = historyValue.split(",");
		List<Article> list = new ArrayList<>();
		for (String aId : articleIds) {
			System.out.println("aId: " + aId);
			//获取资讯基本信息
//			Article article = mapper.findArticlesById(Long.parseLong(aId), userId);
			Article article = iArticleMapper.findArticlesByIdWithUserAndCategory(Long.parseLong(aId), userId);
			if(article != null) {
				//添加点赞、收藏量
				addLikeAndCollectionNums(article);
				list.add(article);
			}
		}
		
		return list;
	}
	
	/*
	 * 代码到此处，需要往下实现 补全
	 */
	@Override
	public List<Article> findArticlesByState(int state) {
		List<Article> list = iArticleMapper.findArticlesByState(state);
		for (Article article : list) {
			addLikeAndCollectionNums(article);
		}
		return list;
	}

	//审核资讯
	@Override
	public void examineArticle(String articleIds, long state) {
		String[] arr = articleIds.split(",");
		for (String aId : arr) {
			iArticleMapper.updateArticleState(Long.parseLong(aId),state);
		}
		
	}
	
	//根据咨询id[33 | 33,35,40] 删除所有相关资讯
	@Override
	public void deleteArticleById(String articleIds) {
		//1.解析资讯id
		String[] artArr = articleIds.split(",");
		//2.逐个删除
		for (String art : artArr) {
			iArticleMapper.deleteArticleById(Integer.parseInt(art));
		}
	}
	@Override
	public List<Article> findReportArticles(int reportState) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void processArticle(long articleId, int processState, String processContent) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<UserReport> findUserReportsByArticleId(long articleId) {

		return null;
	}
	
	//根据咨询id、用户id 找出资讯被举报信息
	@Override
	public List<UserReport> findUserReportsByArticleIdUserId(long articleId, long userId) {
		List<UserReport> list = iArticleMapper.findUserReportsByAidUid(articleId,userId);
		
		return list;
	}
	@Override
	public List<Article> findProcessedArticles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateArticleCategory(long articleId, long categoryId) {
		iArticleMapper.updateArticleCategory(articleId,categoryId);

	}
	
	
}
