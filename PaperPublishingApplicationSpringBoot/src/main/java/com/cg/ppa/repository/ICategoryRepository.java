package com.cg.ppa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.ppa.entities.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {
	public boolean existsByCategoryName(String categoryName);

	public Category findByCategoryName(String categoryName);

	public Category findByCategoryId(int categoryId);
}
