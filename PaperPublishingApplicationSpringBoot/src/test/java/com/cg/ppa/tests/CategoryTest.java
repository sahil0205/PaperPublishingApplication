package com.cg.ppa.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.ppa.entities.Category;
import com.cg.ppa.repository.ICategoryRepository;
import com.cg.ppa.service.CatgeoryService;

@SpringBootTest
public class CategoryTest {

	@Autowired
	CatgeoryService service;

	@MockBean
	ICategoryRepository repository;

	@Test
	public void viewAllCategories() {
		when(repository.findAll()).thenReturn(
				Stream.of(new Category(1, "Sports"), new Category(2, "National"), new Category(3, "International"))
						.collect(Collectors.toList()));
		assertEquals(3, service.viewAllCategories().size());
	}

	@Test
	public void addCategory() {
		Category category = new Category(1, "Sports");
		when(repository.save(category)).thenReturn(category);
		assertEquals("Sports", service.addCategory(category).getCategoryName());
	}

	@Test
	public void deleteCategory() {
		when(repository.existsById(1)).thenReturn(true);
		service.deleteCategory(1);
		verify(repository, times(1)).deleteById(1);
	}

	@Test
	public void updateCategory() {
		Category category = new Category(1, "Sports");
		when(repository.existsById(category.getCategoryId())).thenReturn(true);
		when(repository.save(category)).thenReturn(category);
		assertEquals(1, service.updateCategory(category).getCategoryId());
		assertEquals("Sports", service.updateCategory(category).getCategoryName());
	}

	@Test
	public void viewCategoryByName() {
		Category category = new Category(1, "Sports");
		when(repository.existsByCategoryName("Sports")).thenReturn(true);
		when(repository.findByCategoryName("Sports")).thenReturn(category);
		assertEquals("Sports", service.viewCategoryByName("Sports").getCategoryName());
	}

	@Test
	public void viewCategoryById() {
		Category category = new Category(1, "Sports");
		when(repository.existsById(1)).thenReturn(true);
		when(repository.findByCategoryId(1)).thenReturn(category);
		assertEquals("Sports", service.viewCategoryById(1).getCategoryName());
	}
	
}
