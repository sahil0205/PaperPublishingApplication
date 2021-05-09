package com.cg.ppa.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ppa.entities.Category;
import com.cg.ppa.exception.CategoryException;
import com.cg.ppa.service.ICategoryService;

@RestController
@RequestMapping("/ppa/category")
public class CategoryController {

	@Autowired
	ICategoryService service;
	
	Logger logger = LoggerFactory.getLogger(CategoryController.class);

	@PostMapping("/addcategory")
	public ResponseEntity<Object> addCategory(@RequestBody Category category) {
		try {
			Category categoryData = service.addCategory(category);
			logger.info(category.getCategoryName()+" added to database");
			return new ResponseEntity<Object>(categoryData, HttpStatus.OK);
		} catch (CategoryException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	@PutMapping("/updatecategory")
	public ResponseEntity<Object> updateCategory(@RequestBody Category category) {
		try {
			Category categoryData = service.updateCategory(category);
			logger.info("Category Id: "+category.getCategoryId()+" updated");
			return new ResponseEntity<Object>(categoryData, HttpStatus.OK);
		} catch (CategoryException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@GetMapping("/viewallcategories")
	public ResponseEntity<Object> viewAllCategories() {
		try {
			List<Category> categoryList = service.viewAllCategories();
			logger.info("Viewing all categories");
			return new ResponseEntity<Object>(categoryList, HttpStatus.OK);
		} catch (CategoryException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deletecategory/{categoryId}")
	public ResponseEntity<Object> removeCategory(@PathVariable int categoryId) {
		try {
			service.deleteCategory(categoryId);
			logger.info("Category Id: "+categoryId+" deleted");
			return new ResponseEntity<Object>("Category Removed", HttpStatus.OK);
		} catch (CategoryException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/viewcategorybyname/{categoryName}")
	public ResponseEntity<Object> viewCategoryByName(@PathVariable String categoryName) {
		try {
			Category categoryData = service.viewCategoryByName(categoryName);
			logger.info("Accessing category by name: "+categoryName);
			return new ResponseEntity<Object>(categoryData, HttpStatus.OK);
		} catch (CategoryException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("viewcategorybyid/{categoryId}")
	public ResponseEntity<Object> viewCategoryById(@PathVariable int categoryId){
		try {
			Category categoryData = service.viewCategoryById(categoryId);
			logger.info("Accessing category by id: "+categoryId);
			return new ResponseEntity<Object>(categoryData, HttpStatus.OK);
		}catch (CategoryException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
