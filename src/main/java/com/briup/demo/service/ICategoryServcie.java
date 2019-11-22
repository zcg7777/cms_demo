package com.briup.demo.service;

import java.util.List;

import com.briup.demo.common.bean.Category;

public interface ICategoryServcie {
	/**
	 * 查找所有的一级分类栏目列表，包含二级分类栏目
	 * @return
	 */
	public List<Category> findAllCategory();
	
	public Category findCategoryById(int id);
	
	/**
	 * 新增栏目
	 * @param category
	 */
	public void insertCategory(Category category);
	
	/**
	 * 更新栏目
	 * @param category
	 */
	public void updateCategory(Category category);
	
	/**
	 * 删除栏目
	 * @param categoryIds
	 */
	public void deleteCategorys(String categoryIds);
}
