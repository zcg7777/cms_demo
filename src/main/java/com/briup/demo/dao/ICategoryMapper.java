package com.briup.demo.dao;

import java.util.List;
import java.util.Set;

import com.briup.demo.common.bean.Category;

/*
 * 咨询分类 映射接口
 */
public interface ICategoryMapper {
	/**
	 * 查找所有的一级分类栏目列表，包含二级分类栏目
	 * @return
	 */
	//public List<Category> findAllCategory2();
	public List<Category> findAllCategory();
	public Set<Category> findCategroyByParentId(int id);
	
	public Category findCategroyById(int id);
	
}
