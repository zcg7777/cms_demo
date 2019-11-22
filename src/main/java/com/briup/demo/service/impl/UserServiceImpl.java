package com.briup.demo.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.common.bean.User;
import com.briup.demo.common.exception.UserServcieException;
import com.briup.demo.dao.IUserMapper;
import com.briup.demo.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserMapper userMapperDao;
	
	@Override
	public User login(String account, String password) throws UserServcieException {
		if(account == null || password == null)
			throw new UserServcieException("用户名或密码为空，请重新输入");
		
		User user = userMapperDao.findUserByAccount(account);
		
		if(user == null) {
			throw new UserServcieException("用户不存在，请重新输入");
		}
		
		if(password.equals(user.getPassword()) == false) {
			throw new UserServcieException("密码有误，请重新输入");
		}
		
		return user;
	}
	
	@Override
	public User thirdLogin(User user) {
		//判断用户是否曾经登录过
		User findUser = userMapperDao.findUserByThirdId(user.getThirdId());
		if(findUser != null) {
			//用户曾经登陆过，昵称信息可能更改过，更新
			findUser.setNickname(user.getNickname());
			userMapperDao.updateName(findUser);
		}else {
			//用户首次登录，做添加，相当于注册
			userMapperDao.insertUserThird(user);
		}
		
		//将刚写入的查询出来 返回
		if(findUser == null)
			findUser = userMapperDao.findUserByThirdId(user.getThirdId());
		
		return findUser;
	}
	
	@Override
	public void updateUser(User user) {
		userMapperDao.updateUser(user);
		
	}
	
	@Override
	public void register(User user) throws UserServcieException {
		User u = userMapperDao.findUserByAccount(user.getAccount());
		if(u != null) {
			throw new UserServcieException("当前用户已存在，请重新注册");
		}
		//将用户插入数据库
		userMapperDao.insertUser(user);
		
	}
	
	//用户点赞操作
	@Override
	public void likeArticle(int likeState, long articleId, long userId) {
		//当前点赞状态如果是1，则取消点赞 【点赞状态 修改为 2】 
		if(likeState == 1) {
			userMapperDao.updateUserLike(articleId,userId,2);
		}else if(likeState == 0){
			//如果未点赞，则点赞【点赞状态设置为1，insert操作】
			userMapperDao.insertUserLike(articleId,userId,new Date());
		}else if(likeState == 2) {
			//当前未取消点赞，则修改 表中状态为 1
			userMapperDao.updateUserLike(articleId,userId,1);
		}
	}
	
	@Override
	public void collectionArticle(int likeState, long articleId, long userId) {
		
	}
	
	@Override
	public void reportArticle(long articleId, long userId, String reportType, String reportContent) {
		userMapperDao.insertUserReport(articleId,userId,reportType,reportContent);
	}
	
	
	
}
