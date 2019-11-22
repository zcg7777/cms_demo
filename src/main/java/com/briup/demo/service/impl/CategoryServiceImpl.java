package com.briup.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.demo.common.bean.Category;
import com.briup.demo.dao.ICategoryMapper;
import com.briup.demo.service.ICategoryServcie;
@Service
public class CategoryServiceImpl implements ICategoryServcie {
	@Autowired
	private ICategoryMapper cateMapper;
	
	@Override
	public List<Category> findAllCategory() {
		List<Category> list = cateMapper.findAllCategory();
		return list;
	}
	
	@Override
	public Category findCategoryById(int id) {
		
		return cateMapper.findCategroyById(0);
	}
	
	@Override
	public void insertCategory(Category category) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateCategory(Category category) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteCategorys(String categoryIds) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
