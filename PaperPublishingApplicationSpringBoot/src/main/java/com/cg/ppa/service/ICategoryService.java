package com.cg.ppa.service;

import java.util.List;

import com.cg.ppa.entities.Category;
import com.cg.ppa.exception.CategoryException;

public interface ICategoryService {
	public Category addCategory(Category category) throws CategoryException;
	
	public void deleteCategory(int categoryId) throws CategoryException;
	
	public Category updateCategory(Category category) throws CategoryException;
	
	public List<Category> viewAllCategories() throws CategoryException;
	
	public Category viewCategoryByName(String name) throws CategoryException;
	
	public Category viewCategoryById(int categoryId) throws CategoryException;
}
