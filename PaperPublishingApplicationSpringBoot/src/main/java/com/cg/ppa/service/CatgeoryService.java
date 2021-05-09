package com.cg.ppa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ppa.entities.Category;
import com.cg.ppa.exception.CategoryException;
import com.cg.ppa.repository.ICategoryRepository;

@Service
@Transactional
public class CatgeoryService implements ICategoryService {

	@Autowired
	ICategoryRepository repository;

	@Override
	public Category addCategory(Category category) throws CategoryException {
		if (repository.existsByCategoryName(category.getCategoryName()))
			throw new CategoryException("Category already exists");
		else {
			return repository.save(category);
		}
	}

	@Override
	public void deleteCategory(int categoryId) throws CategoryException {
		if (repository.existsById(categoryId))
			repository.deleteById(categoryId);
		else
			throw new CategoryException("Category doesn't exist");
	}

	@Override
	public Category updateCategory(Category category) throws CategoryException {
		if (repository.existsById(category.getCategoryId()))
			return repository.save(category);
		else
			throw new CategoryException("Category doesn't exist");
	}

	@Override
	public List<Category> viewAllCategories() throws CategoryException {
		List<Category> categoryList = repository.findAll();
		if (categoryList.isEmpty())
			throw new CategoryException("No Data Found");
		else
			return categoryList;
	}

	@Override
	public Category viewCategoryByName(String name) throws CategoryException {
		if (repository.existsByCategoryName(name))
			return repository.findByCategoryName(name);
		else
			throw new CategoryException("Category doesn't exist");
	}

	@Override
	public Category viewCategoryById(int categoryId) throws CategoryException {
		if(repository.existsById(categoryId))
			return repository.findByCategoryId(categoryId);
		else
			throw new CategoryException("No category found");
	}

}
